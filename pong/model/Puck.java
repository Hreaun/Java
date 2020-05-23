package model;

import java.util.Random;

public class Puck {
    public int x, y, width = 20, height = 20;
    public int speedX, speedY;
    public int fieldHeight, fieldWidth;
    public Random random;

    public Puck(int fieldHeight, int fieldWidth) {
        this.fieldHeight = fieldHeight;
        this.fieldWidth = fieldWidth;
        this.random = new Random();
    }

    public void reset(Paddle playerOne, Paddle playerTwo) {
        this.x = ((playerTwo.x + (playerOne.x + playerOne.width)) - this.width) / 2;
        this.y = fieldHeight / 2 - this.height / 2;
        this.speedY = -2 + random.nextInt(4);
        if (random.nextBoolean()) {
            this.speedX = 3;
        } else {
            this.speedX = -3;
        }
    }

    public void changeSpeedX(){
        switch (this.random.nextInt(20)) {
            case 0: {
                this.speedX *= 3;
                break;
            }
            case 1: {
                this.speedX *= 2;
                break;
            }
            case 2: {
                this.speedX *= 1.5;
                break;
            }
        }
    }

    public void changeSpeedY(Paddle paddle){
        if (paddle.down) {
            this.speedY = 6 - this.random.nextInt(3);
        } else if (paddle.up) {
            this.speedY = -6 + this.random.nextInt(3);
        } else {
            this.speedY += -2 + this.random.nextInt(4);
        }
    }


}
