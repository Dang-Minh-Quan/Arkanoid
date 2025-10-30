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

    public void updateGame(MainMedia media, ArrayList<Ball>balls, Ball ball, Paddle paddle, Brick[][] brick, AtomicInteger Level, ArrayList<PowerUp> powerUps) {
        updatePowerUp(balls,ball,paddle,powerUps);
        updateBall(media,ball,brick,paddle,powerUps);
        updateBalls(media,balls,brick,paddle,powerUps);
        updateBrick(balls,ball,paddle,Level, brick);
        updatePaddle(paddle, brick, ball, gameRestarted, render);
    }

    private void updatePowerUp(ArrayList<Ball>balls,Ball ball, Paddle paddle, ArrayList<PowerUp> powerUps){
        for(int i=0;i<powerUps.size();i++){
            if(powerUps.get(i).checkActivate==true){
                if (powerUps.get(i).checkTimePowerUp==-1){
                    powerUps.remove(i);
                }
                else {
                    powerUps.get(i).checkStopPowerUp(balls, paddle, ball);
                }
            }
            else {
                switch (powerUps.get(i).UpdatePU(balls, paddle, ball)) {
                    case 1:
                        powerUps.get(i).checkActivate = true;
                        break;
                    case 2:
                        powerUps.remove(i);
                        break;
                }
            }
        }
    }

    private void updateBrick(ArrayList<Ball>balls,Ball ball,Paddle paddle,AtomicInteger Level, Brick[][] brick) {
        if (numBrick <= 0) {
            Level.getAndIncrement();
            if (Level.get() <= LevelMax) {
//                System.out.println(winLevel + " " + Level);
                if (winLevel == false) {
                    builderLevel(balls,ball,brick,paddle, Level);
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
        updateBrick(balls,ball,paddle,Level, brick);
    }

    private void builderLevel(ArrayList<Ball>balls,Ball ball ,Brick[][] brick, Paddle paddle,AtomicInteger Level) {
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


    private static void updateBall(MainMedia media,Ball ball,Brick[][] brick,Paddle paddle,ArrayList<PowerUp>powerUps) {
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

            switch (balls.get(i).checkBrickCollision(media,brick, powerUps)) {
                case 1:
                    balls.get(i).vx = -balls.get(i).vx;
                    break;
                case 2:
                    balls.get(i).vy = -balls.get(i).vy;
                    break;
            }
        }
    }

}
