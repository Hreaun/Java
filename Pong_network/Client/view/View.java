package Client.view;

import Client.controller.Controller;
import Server.model.GameStatus;
import Server.model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class View {
    public JFrame frame;
    JMenuBar menuBar;
    JMenuItem[] resolutions;
    public int width = 800, height = 670; // разрешение игры, относительно которого считается масштаб
    private final Controller controller;
    Model model;
    private Renderer renderer;


    public View(Controller controller) {
        model = controller.model;
        renderer = new Renderer(this, this.width);
        model.addObserver(renderer);
        this.controller = controller;
        frame = new JFrame("Pong");
        menuBar = new JMenuBar();
        JMenu game = new JMenu("Game");
        game.setForeground(Color.WHITE);
        JMenuItem exit = new JMenuItem("Exit");
        exit.setForeground(Color.WHITE);
        exit.setBackground(Color.BLACK);
        exit.addActionListener((event) -> System.exit(0));
        game.add(exit);
        menuBar.add(game);

        JMenu res = new JMenu("Resolution");
        res.setForeground(Color.WHITE);
        menuBar.add(res);
        resolutions = new JMenuItem[5];
        int resWidth = width;
        int resHeight = height;
        for (JMenuItem resolution : resolutions) {
            resolution = new JMenuItem(resWidth + "*" + resHeight);
            resolution.setForeground(Color.WHITE);
            resolution.setBackground(Color.BLACK);
            int finalResWidth = resWidth;
            int finalResHeight = resHeight;
            resolution.addActionListener((event) -> frame.setSize(finalResWidth, finalResHeight + 60));
            res.add(resolution);
            resWidth *= 1.2;
            resHeight *= 1.2;
        }

        UIManager.put("MenuBar.background", Color.BLACK);
        frame.add(menuBar);
        frame.setJMenuBar(menuBar);

        frame.setSize(width, height + 60);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.add(renderer);
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                View.this.controller.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                View.this.controller.keyReleased(e);
            }
        });
    }

    public void render(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height + 60);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (model.gameStatus == GameStatus.STOPPED) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 50));
            g.drawString("PONG", width / 2 - width / 10, 100);

            g.setFont(new Font("Arial", Font.PLAIN, 30));
            g.drawString("Press space to play", width / 2 - width / 6, height / 2);
        }

        if (model.gameStatus == GameStatus.PLAYING || model.gameStatus == GameStatus.PAUSED) {
            g.setColor(Color.WHITE);
            g.setStroke(new BasicStroke(5));
            int middle = (model.playerTwo.x + (model.playerOne.x + model.playerOne.width)) / 2;
            g.drawLine(middle, 0, middle, height);
            g.setFont(new Font("Arial", Font.BOLD, 50));
            g.setColor(Color.GRAY);
            g.drawString(Integer.toString(model.playerOne.score), middle / 2, height / 8);
            g.drawString(Integer.toString(model.playerTwo.score), middle + middle / 2, height / 8);

            g.setColor(Color.WHITE);
            g.fillOval(model.puck.x, model.puck.y, model.puck.width, model.puck.height); //шар
            g.fillRect(model.playerOne.x, model.playerOne.y, model.playerOne.width, model.playerOne.height); //игроки
            g.fillRect(model.playerTwo.x, model.playerTwo.y, model.playerTwo.width, model.playerTwo.height);
        }

        if (model.gameStatus == GameStatus.PAUSED) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 50));
            g.drawString("PAUSED", width / 2 - width / 8, height / 2);
        }

        if (model.gameStatus == GameStatus.END) {
            String winner = (model.playerOne.score == 11) ? "Player One" : "Player Two";
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.drawString("Winner is " + winner, width / 2 - width / 5, 50);

            g.setFont(new Font("Arial", Font.PLAIN, 30));
            g.drawString("Press space to play", width / 2 - width / 6, height / 2);
        }

        if (model.gameStatus == GameStatus.DISCONNECT){
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.drawString("Disconnected", width / 2 - width / 6, 100);
        }
    }

}
