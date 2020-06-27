package Server.model;

import java.io.Serializable;
import java.util.*;

enum Collision {NO_COLLISION, SCORE, HIT}

public class Model extends Observable implements Serializable {
    public Puck puck;
    public GameStatus gameStatus = GameStatus.STOPPED;
    private final int fieldHeight = 670;
    private final int fieldWidth = 800;
    public Paddle[] player = new Paddle[2];
    public Paddle playerOne;
    public Paddle playerTwo;



    public void changed(){
        setChanged();
    }

    public Model() {
        playerOne = new Paddle(1, fieldHeight, fieldWidth);
        playerTwo = new Paddle(2, fieldHeight, fieldWidth);
        player[0] = playerOne;
        player[1] = playerTwo;
    }

    public Collision checkCollision(Paddle paddle) {
        if ((puck.x + puck.width / 4 < paddle.x + paddle.width && puck.x + puck.width > paddle.x) &&
                (puck.y < paddle.y + paddle.height && puck.y + puck.height > paddle.y)) {
            return Collision.HIT;
        } else if ((puck.x + puck.width < 0 && paddle.paddleNumber == 1)
                || (puck.x > paddle.fieldWidth && paddle.paddleNumber == 2)) {
            return Collision.SCORE;
        }
        return Collision.NO_COLLISION;
    }

    private void puckUpdate() {
        if (checkCollision(playerOne) == Collision.NO_COLLISION && checkCollision(playerTwo) == Collision.NO_COLLISION) {
            if (puck.y + puck.speedY < 0) {
                puck.y = 0;
                puck.speedY = -puck.speedY;
            } else if (puck.y + puck.speedY > puck.fieldHeight - puck.height) {
                puck.y = puck.fieldHeight - puck.height;
                puck.speedY = -puck.speedY;
            }
        }
        puck.x += puck.speedX;
        puck.y += puck.speedY;

        if (checkCollision(playerOne) == Collision.HIT) {
            puck.speedX = 5;
            puck.changeSpeedX();
            puck.changeSpeedY(playerOne);

        } else if (checkCollision(playerTwo) == Collision.HIT) {
            puck.speedX = -5;
            puck.changeSpeedX();
            puck.changeSpeedY(playerTwo);
        }

        if (checkCollision(playerOne) == Collision.SCORE) {
            playerTwo.score++;
            if (playerTwo.score == 11) {
                this.gameStatus = GameStatus.END;
            }
            puck.reset(playerOne, playerTwo);
        } else if (checkCollision(playerTwo) == Collision.SCORE) {
            playerOne.score++;
            if (playerOne.score == 11) {
                this.gameStatus = GameStatus.END;
            }
            puck.reset(playerOne, playerTwo);
        }
    }

    private void playersUpdate() {
        if (playerOne.up) {
            playerOne.move(true);
        }
        if (playerOne.down) {
            playerOne.move(false);
        }
        if (playerTwo.up) {
            playerTwo.move(true);
        }
        if (playerTwo.down) {
            playerTwo.move(false);
        }
    }

    public void start() {
        gameStatus = GameStatus.PLAYING;
        puck = new Puck(fieldHeight, fieldWidth);
        playerOne.reset();
        playerTwo.reset();
        puck.reset(playerOne, playerTwo);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                playerOne = player[0];
                playerTwo = player[1];
                if (playerOne.pause || playerTwo.pause){
                    gameStatus = GameStatus.PAUSED;
                    setChanged();
                    notifyObservers();
                }
                if (gameStatus == GameStatus.PAUSED){
                    if (!playerOne.pause && !playerTwo.pause){
                        gameStatus = GameStatus.PLAYING;
                        setChanged();
                        notifyObservers();
                    }
                }
                if (gameStatus == GameStatus.END) {
                    setChanged();
                    notifyObservers();
                    timer.cancel();
                }
                if (gameStatus == GameStatus.PLAYING) {
                    puckUpdate();
                    playersUpdate();
                    setChanged();
                    notifyObservers();
                }
            }
        }, 0, 10);
    }
}
