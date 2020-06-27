package Client.controller;

import Server.model.GameStatus;
import Server.model.Model;
import Server.model.Paddle;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;

public class Controller extends Observable implements KeyListener {
    public Model model;
    public Paddle paddle;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int id = e.getKeyCode();

        switch (id) {
            case (KeyEvent.VK_SPACE): {
                if (model.gameStatus == GameStatus.STOPPED || model.gameStatus == GameStatus.END) {
                    paddle.ready = true;
                } else if (model.gameStatus == GameStatus.PAUSED) {
                    paddle.pause = false;
                } else if (model.gameStatus == GameStatus.PLAYING) {
                    paddle.pause = true;
                }
                setChanged();
                notifyObservers();
                break;
            }

            case (KeyEvent.VK_W):
            case (KeyEvent.VK_UP): {
                if (model.gameStatus != GameStatus.PLAYING) {
                    break;
                }
                paddle.up = true;
                setChanged();
                notifyObservers();
                break;
            }
            case (KeyEvent.VK_S):
            case (KeyEvent.VK_DOWN): {
                if (model.gameStatus != GameStatus.PLAYING) {
                    break;
                }
                paddle.down = true;
                setChanged();
                notifyObservers();
                break;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int id = e.getKeyCode();

        switch (id) {
            case (KeyEvent.VK_W):
            case (KeyEvent.VK_UP): {
                paddle.up = false;
                break;
            }
            case (KeyEvent.VK_S):
            case (KeyEvent.VK_DOWN): {
                paddle.down = false;
                break;
            }
        }

        if (model.gameStatus == GameStatus.PLAYING) {
            setChanged();
            notifyObservers();
        }
    }

}
