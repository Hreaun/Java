import java.awt.*;

public class Paddle {
    public int paddleNumber;
    public int x, y, width = 20, height = 70;
    public int score = 0;


    public Paddle(Pong pong, int paddleNumber) {
        this.paddleNumber = paddleNumber;

        if (paddleNumber == 1) {
            this.x = width;
        } else {
            this.x = pong.width - width * 3;
        }
        this.y = pong.height / 2 - this.height / 2;
    }


    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, width, height);
    }

    public void move(boolean up) {
        int speed = 11;

        if (up) {
            if (y > 0) {
                y -= speed;
            }
        } else {
            if (y + height < Pong.pong.height) {
                y += speed;
            }
        }
    }
}
