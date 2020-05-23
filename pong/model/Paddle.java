package model;

public class Paddle {
    public int paddleNumber;
    public int x, y, width = 20, height = 70;
    public boolean up, down;
    public int score = 0;
    public int fieldHeight, fieldWidth;

    public Paddle(int paddleNumber, int fieldHeight, int fieldWidth) {
        this.paddleNumber = paddleNumber;
        this.fieldHeight = fieldHeight;
        this.fieldWidth = fieldWidth;
        if (paddleNumber == 1) {
            this.x = width;
        } else {
            this.x = fieldWidth - width * 3;
        }
        this.y = fieldHeight / 2 - this.height / 2;

    }

    public void move(boolean up) {
        int speed = 11;

        if (up) {
            if (y > 0) {
                y -= speed;
            }

        } else {
            if (y + this.height < fieldHeight) {
                y += speed;
            }
        }
    }
}
