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
        updateBrick(ball, brick);
        updatePaddle(paddle, brick, ball, gameRestarted, render);
    }

    private void updateBrick(Ball ball, Brick[][] brick) {
        if (numBrick <= 0) {
            if (Level.get() <= LevelMax) {
//                System.out.println(winLevel + " " + Level);
                if (winLevel == false) {
                    builderLevel(ball, brick, Level);
                    winLevel = true;
                } else {
                    winLevel = false;
                    Platform.runLater(() -> controller.Win());
                }
            } else {
                Platform.runLater(() -> controller.Win());
            }
        } else {
            //numBrick--;
        }
    }

    public void initializeLevel(Ball ball, Brick[][] brick) {
//        System.out.println(Level);
        heartCount.set(3);
        numBrick = 0;
        // Level.set(0);
        updateBrick(ball, brick);
    }

    private void builderLevel(Ball ball, Brick[][] brick, AtomicInteger Level) {
        Map map = new Map();
        if (Level.get() > LevelMax) {
        } else {
            //ball.resert();
            int[][] a = map.builderMap(Level.get());
            for (int i = 0; i < ROW; i++) {
                for (int j = 0; j < COL; j++) {
                    brick[i][j] = new Brick(i, j);
                    brick[i][j].type = a[i][j];
                    if (brick[i][j].type > 0) {
                        numBrick = numBrick + 1;
                    }
                    brick[i][j].Update();
                }
            }
        }
    }

    private void updatePaddle(Paddle paddle, Brick[][] brick, Ball ball, AtomicBoolean gameRestarted, Render render) {

        if (heartCount.get() == 0) {
            ball.setBall(paddle.x + paddle.width / 2, HEIGHT - 60);
            Platform.runLater(() -> controller.GameOver());
            return;
        }

        if (gameRestarted.get()) {
            double nextPaddleX = paddle.getPaddle().getX();
            if (paddle.isMoveLeft()) {
                nextPaddleX -= paddle.vx;
            }
            if (paddle.isMoveRight()) {
                nextPaddleX += paddle.vx;
            }
            nextPaddleX = paddle.ClampPosition(nextPaddleX);
            ball.setBall(paddle.x + paddle.width / 2, HEIGHT - 70);
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
                ball.vx = 0;
                ball.vy = spvxOriginal;
                gameRestarted.set(true);
                heartCount.set(heartCount.get() - 1);
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
                    double paddleCenter = paddle.getPaddle().getX() + paddle.getPaddle().getWidth() / 2.0;
                    double offset = Math.abs(paddleCenter - ball.x) / (paddleCenter - paddle.x);
                    double baseAngle = Math.toRadians(45) * offset + Math.toRadians(5);
                    if (ball.vx >= 0) {
                        ball.vx = spvxOriginal * Math.sin(baseAngle);
                    } else {
                        ball.vx = -spvxOriginal * Math.sin(baseAngle);
                    }
                    ball.vy = -Math.abs(spvxOriginal * Math.cos(baseAngle));
                    break;
                case 2: case 3:
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
