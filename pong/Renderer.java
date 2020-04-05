import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class Renderer extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        double k = (double) Pong.pong.frame.getWidth() / Pong.pong.width;
        AffineTransform Scale = AffineTransform.getScaleInstance(k, k);
        Graphics2D g2 = (Graphics2D) g;
        g2.setTransform(Scale);
        super.paintComponent(g2);

        Pong.pong.render((Graphics2D) g);
    }
}
