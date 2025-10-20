package LogicGamePlay;

import Interface.GamePlayController;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import javafx.application.Platform;

import static LogicGamePlay.Specifications.*;

public class Update {

    private GamePlayController controller;

    public Update(GamePlayController controller) {
        this.controller = controller;
    }

    public void updateGame(Ball ball, Paddle paddle, Brick[][] brick, AtomicInteger Level, AtomicBoolean gameRestarted, Render render) {
        updateBrick(ball, Level, brick);
        updatePaddle(paddle, brick, ball, gameRestarted, render);
    }

    private void updateBrick(Ball ball, AtomicInteger Level, Brick[][] brick) {
        if (numBrick <= 0) {
            Level.getAndIncrement();
            if (Level.get() <= LevelMax) {
                builderLevel(ball, brick, Level);
            } else {
                //Level.set(0);
                Platform.runLater(() -> controller.Win());
                //WIN
            }
        } else {
            //numBrick--;
        }
    }

    public void initializeLevel(Ball ball, Brick[][] brick, AtomicInteger Level) {
        numBrick = 0;
        Level.set(0);
        updateBrick(ball, Level, brick);
    }

    private void builderLevel(Ball ball, Brick[][] brick, AtomicInteger Level) {
        Map map = new Map();
        if (Level.get() > LevelMax) {
            checkPlay = false;
        } else {
            //ball.resert();
            int[][] a = map.builderMap(Level.get());
            for (int i = 0; i < ROW; i++) {
                for (int j = 0; j < COL; j++) {
                    brick[i][j] = new Brick(i, j);
                    brick[i][j].type = a[i][j];
                    if (brick[i][j].type > 0) {
                        numBrick = numBrick + brick[i][j].type;
                    }
                    brick[i][j].Update();
                }
            }
        }
    }

    private static void updatePaddle(Paddle paddle, Brick[][] brick, Ball ball, AtomicBoolean gameRestarted, Render render) {
        //paddle.Update();
        if (gameRestarted.get()) {
            double nextPaddleX = paddle.getPaddle().getX();
            double nextBallX = ball.getBall().getCenterX();
            if (paddle.isMoveLeft()) {
                nextPaddleX -= paddle.vx;
                nextBallX -= paddle.vx;
            }
            if (paddle.isMoveRight()) {
                nextPaddleX += paddle.vx;
                nextBallX += paddle.vx;
            }
            nextPaddleX = paddle.ClampPosition(nextPaddleX);
            if (nextPaddleX != paddle.getPaddle().getX()) {
                ball.setBall(nextBallX, HEIGHT - 60);
            }
            paddle.setPaddle(nextPaddleX);

            return;
        }

        updateBall(ball, brick, paddle, gameRestarted, render);

        double nextPaddleX = paddle.getPaddle().getX();
        if (paddle.isMoveLeft()) {
            nextPaddleX -= paddle.vx;
        }
        if (paddle.isMoveRight()) {
            nextPaddleX += paddle.vx;
        }
        nextPaddleX = paddle.ClampPosition(nextPaddleX);
        paddle.setPaddle(nextPaddleX);

    }


    private static void updateBall(Ball ball, Brick[][] brick, Paddle paddle, AtomicBoolean gameRestarted, Render render) {
        double nextBallX = ball.getBall().getCenterX() + ball.vx;
        double nextBallY = ball.getBall().getCenterY() + ball.vy;
        ball.setBall(nextBallX, nextBallY);
        switch (ball.checkWallCollision(paddle, gameRestarted)) {
            case -1:
                ball.setBall(WIDTH / 2, HEIGHT - 60);
                paddle.setPaddle((WIDTH - paddle.width) / 2);
                gameRestarted.set(true);
                break;
            case 1:
                ball.vx = -ball.vx;
                ball.vy = -ball.vy;
                break;
            case 2:
                ball.vx = -ball.vx;
                break;
            case 3:
                ball.vy = -ball.vy;
                break;
        }
        int collisionState = ball.checkPaddleCollision(paddle);
        if (ball.isReadyForPaddleCollision(collisionState)) {
            switch (collisionState) {
                case 1:
                    ball.vy = -ball.vy;
                    break;
                case 2:
                case 3:
                    ball.vx = -ball.vx;
                    break;
            }
        }
        int collisionResult = ball.checkBrickCollision(brick, render);
        if (collisionResult != 0) {
            if (collisionResult == 1) {
                ball.vx = -ball.vx;
            } else if (collisionResult == 2) {
                ball.vy = -ball.vy;
            }
            //Brick b = ball.getLastHitBrick();
            // if (b != null) b.BallHit(ball, render);
        }

    }
}
