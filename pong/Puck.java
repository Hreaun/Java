import java.awt.*;
import java.util.Random;

enum Collision {NO_COLLISION, SCORE, HIT}

public class Puck {
    public int x, y, width = 20, height = 20;
    public int speedX, speedY;
    public Random random;

    public Puck() {
        this.random = new Random();
        this.reset();
    }

    public void reset() {
        this.x = ((Pong.pong.playerTwo.x + (Pong.pong.playerOne.x + Pong.pong.playerOne.width)) - this.width) / 2;
        this.y = Pong.pong.height / 2 - this.height / 2;
        this.speedY = -2 + random.nextInt(4);
        if (random.nextBoolean()) {
            this.speedX = 3;
        } else {
            this.speedX = -3;
        }
    }

    public void update(Paddle one, Paddle two) {
        if (checkCollision(one) == Collision.NO_COLLISION && checkCollision(two) == Collision.NO_COLLISION) {
            if (this.y + speedY < 0) {
                this.y = 0;
                this.speedY = -this.speedY;
            } else if (this.y + speedY > Pong.pong.height - this.height) {
                this.y = Pong.pong.height - this.height;
                this.speedY = -this.speedY;
            }
        }
        this.x += speedX;
        this.y += speedY;


        if (checkCollision(one) == Collision.HIT) {
            this.speedX = 5;

            switch (random.nextInt(20)) {
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

            if (Pong.pong.s) {
                this.speedY = 6 - random.nextInt(3);
            } else if (Pong.pong.w) {
                this.speedY = -6 + random.nextInt(3);
            } else {
                this.speedY += -2 + random.nextInt(4);
            }
        } else if (checkCollision(two) == Collision.HIT) {
            this.speedX = -5;
            if (random.nextInt(20) == 0)
                this.speedX *= 3;
            if (Pong.pong.down) {
                this.speedY = 6 - random.nextInt(2);
            } else if (Pong.pong.up) {
                this.speedY = -6 + random.nextInt(2);
            } else {
                this.speedY += -2 + random.nextInt(4);
            }
        }

        if (checkCollision(one) == Collision.SCORE) {
            two.score++;
            if (two.score == 11) {
                Pong.pong.gameStatus = GameStatus.END;
            }
            this.reset();
        } else if (checkCollision(two) == Collision.SCORE) {
            one.score++;
            if (one.score == 11) {
                Pong.pong.gameStatus = GameStatus.END;
            }
            this.reset();
        }
    }

    public Collision checkCollision(Paddle paddle) {
        if ((this.x + this.width / 4 < paddle.x + paddle.width && this.x + this.width > paddle.x) &&
                (this.y < paddle.y + paddle.height && this.y + this.height > paddle.y)) {
            return Collision.HIT;
        } else if ((this.x + this.width < 0 && paddle.paddleNumber == 1)
                || (this.x > Pong.pong.width && paddle.paddleNumber == 2)) {
            return Collision.SCORE;
        }
        return Collision.NO_COLLISION;
    }


    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(x, y, width, height);
    }
}
