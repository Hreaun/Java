import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

enum GameStatus {STOPPED, PAUSED, PLAYING, END}

public class Pong implements ActionListener, KeyListener {
    public static Pong pong;
    public int width = 840, height = 700;
    public Renderer renderer;

    public Paddle playerOne;
    public Paddle playerTwo;
    public Puck puck;

    public boolean w, s, up, down;
    public GameStatus gameStatus = GameStatus.STOPPED;

    public Pong() {
        Timer timer = new Timer(12, this);
        JFrame frame = new JFrame("Pong");
        renderer = new Renderer();

        frame.setSize(width, height + 80);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.add(renderer);
        frame.addKeyListener(this);

        timer.start();
    }

    public static void main(String[] args) {
        pong = new Pong();
    }

    public void start() {
        gameStatus = GameStatus.PLAYING;
        playerOne = new Paddle(this, 1);
        playerTwo = new Paddle(this, 2);
        puck = new Puck();
    }

    public void render(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (gameStatus == GameStatus.STOPPED) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 50));
            g.drawString("PONG", width / 2 - width / 10, 50);

            g.setFont(new Font("Arial", Font.PLAIN, 30));
            g.drawString("Press space to play", width / 2 - width / 6, height / 2);
        }

        if (gameStatus == GameStatus.PLAYING || gameStatus == GameStatus.PAUSED) {
            g.setColor(Color.WHITE);
            g.setStroke(new BasicStroke(5));
            int middle = (playerTwo.x + (playerOne.x + playerOne.width)) / 2;
            g.drawLine(middle, 0, middle, height);
            g.setFont(new Font("Arial", Font.BOLD, 50));
            g.setColor(Color.GRAY);
            g.drawString(Integer.toString(playerOne.score), middle / 2, height / 8);
            g.drawString(Integer.toString(playerTwo.score), middle + middle / 2, height / 8);

            playerOne.render(g);
            playerTwo.render(g);
            puck.render(g);
        }
        if (gameStatus == GameStatus.PAUSED) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 50));
            g.drawString("PAUSED", width / 2 - width / 8, height / 2);
        }

        if (gameStatus == GameStatus.END) {
            String winner = (playerOne.score == 11) ? "Player One" : "Player Two";
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.drawString("Winner is " + winner, width / 2 - width / 5, 50);

            g.setFont(new Font("Arial", Font.PLAIN, 30));
            g.drawString("Press space to play", width / 2 - width / 6, height / 2);
        }
    }

    public void update() {
        if (w) {
            playerOne.move(true);
        }
        if (s) {
            playerOne.move(false);
        }
        if (up) {
            playerTwo.move(true);
        }
        if (down) {
            playerTwo.move(false);
        }
        puck.update(playerOne, playerTwo);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameStatus == GameStatus.PLAYING) {
            update();
        }

        renderer.repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int id = e.getKeyCode();

        switch (id) {
            case (KeyEvent.VK_W): {
                w = true;
                break;
            }
            case (KeyEvent.VK_S): {
                s = true;
                break;
            }
            case (KeyEvent.VK_UP): {
                up = true;
                break;
            }
            case (KeyEvent.VK_DOWN): {
                down = true;
                break;
            }
            case (KeyEvent.VK_SPACE): {
                if (gameStatus == GameStatus.STOPPED || gameStatus == GameStatus.END) {
                    start();
                } else if (gameStatus == GameStatus.PAUSED) {
                    gameStatus = GameStatus.PLAYING;
                } else if (gameStatus == GameStatus.PLAYING) {
                    gameStatus = GameStatus.PAUSED;
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
                w = false;
                break;
            }
            case (KeyEvent.VK_S): {
                s = false;
                break;
            }
            case (KeyEvent.VK_UP): {
                up = false;
                break;
            }
            case (KeyEvent.VK_DOWN): {
                down = false;
                break;
            }
        }
    }
}