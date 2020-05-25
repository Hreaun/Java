package controller;

import game.Game;
import model.GameStatus;
import model.Model;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller implements KeyListener {
    private final Model model;
    private final Game game;

    public Controller(Model model, Game game) {
        this.model = model;
        this.game = game;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int id = e.getKeyCode();

        switch (id) {
            case (KeyEvent.VK_W): {
                model.playerOne.up = true;
                break;
            }
            case (KeyEvent.VK_S): {
                model.playerOne.down = true;
                break;
            }
            case (KeyEvent.VK_UP): {
                model.playerTwo.up = true;
                break;
            }
            case (KeyEvent.VK_DOWN): {
                model.playerTwo.down = true;
                break;
            }

            case (KeyEvent.VK_SPACE): {
                if (model.gameStatus == GameStatus.STOPPED || model.gameStatus == GameStatus.END) {
                    game.start();
                } else if (model.gameStatus == GameStatus.PAUSED) {
                    synchronized (model.pause) {
                        model.pause.notify();
                        model.gameStatus = GameStatus.PLAYING;
                    }
                } else if (model.gameStatus == GameStatus.PLAYING) {
                    model.gameStatus = GameStatus.PAUSED;
                }
                break;
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int id = e.getKeyCode();

        switch (id) {
            case (KeyEvent.VK_W): {
                model.playerOne.up = false;
                break;
            }
            case (KeyEvent.VK_S): {
                model.playerOne.down = false;
                break;
            }
            case (KeyEvent.VK_UP): {
                model.playerTwo.up = false;
                break;
            }
            case (KeyEvent.VK_DOWN): {
                model.playerTwo.down = false;
                break;
            }
        }
    }

}
