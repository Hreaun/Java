package view;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Observable;
import java.util.Observer;

public class Renderer extends JPanel implements Observer {
    private final View view;
    private final int fieldWidth;

    public Renderer(View view, int fieldWidth) {
        this.view = view;
        this.fieldWidth = fieldWidth;
    }


    @Override
    protected void paintComponent(Graphics g) {
        double k = (double) view.frame.getWidth() / fieldWidth;
        AffineTransform Scale = AffineTransform.getScaleInstance(k, k);
        Graphics2D g2 = (Graphics2D) g;
        g2.setTransform(Scale);
        super.paintComponent(g2);

        view.render((Graphics2D) g);
    }

    @Override
    public void update(Observable o, Object arg) {
        this.repaint();
    }
}
