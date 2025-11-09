package LogicGamePlay;

import Interface.GamePlayController;
import Ball.*;
import Media.MainMedia;
import Paddle.*;
import Brick.*;
import PowerUp.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import PowerUp.PowerUp;
import javafx.application.Platform;

import static LogicGamePlay.Specifications.*;

public class Update {

    private GamePlayController controller;
    private PowerUpManager powerUpManager;
    private GameObject gameObject;

    public Update(GamePlayController controller) {
        this.controller = controller;
        gameObject = new GameObject();
        powerUpManager = new PowerUpManager();
    }

    public void updateGame(MainMedia media, List<Ball> balls,
                           AtomicReference<Paddle> paddle, Brick[][] brick,
                           AtomicInteger Level, AtomicBoolean gameRestarted,
                           List<PowerUp> powerUps, List<Bullet> bullets, Render render) {
        updatePowerUp(balls, paddle, powerUps);
        updateBullet(media, bullets, brick, powerUps, render);
        updateBalls(media, balls, bullets, brick, paddle, gameRestarted, powerUps, render);
        updateBrick(balls, paddle, Level, brick);
    }

    private void updateBullet(MainMedia media, List<Bullet> bullets,
                              Brick[][] brick, List<PowerUp> powerUps,
                              Render render) {
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).setBullet(bullets.get(i).y + bullets.get(i).vy);
            if (bullets.get(i).checkBullet()) {
                bullets.remove(i);
                i--;
            }
            if (bullets.get(i).checkBrickCollision(media, brick, render, powerUps, powerUpManager) != 0) {
                bullets.remove(i);
                i--;
            }
        }
    }

    private void updatePowerUp(List<Ball> balls, AtomicReference<Paddle> paddle, List<PowerUp> powerUps) {
        for (int i = 0; i < powerUps.size(); i++) {
            switch (powerUps.get(i).UpdatePU(balls, paddle, powerUps)) {
                case 1:
                    powerUpManager.applyPowerUp(powerUps.get(i), paddle, balls);
                    powerUps.remove(i);
                    i--;
                    break;
                case 2:
                    powerUps.remove(i);
                    i--;
                    break;
            }
        }
    }

    private void updateBrick(List<Ball> balls, AtomicReference<Paddle> paddle, AtomicInteger Level, Brick[][] brick) {
        if (numBrick <= 0) {
            if (winLevel == false) {
                winLevel = true;
                Platform.runLater(() -> controller.Win());
            }
        } else {
            winLevel = false;
        }
    }

    public void initializeLevel(AtomicReference<Paddle> paddle, List<Ball> balls, Brick[][] brick) {
//        System.out.println(Level);
        heartCount.set(3);
        numBrick = 0;
        builderLevel(balls, brick, paddle.get(), Level);
    }

    private void builderLevel(List<Ball> balls, Brick[][] brick, Paddle paddle, AtomicInteger Level) {
        Map map = new Map();
        if (Level.get() > LevelMax) {
//            checkPlay = false;
        } else {
            //ball.resert();
            String[][] a = map.builderMap(Level.get());
            for (int i = 0; i < ROW; i++) {
                for (int j = 0; j < COL; j++) {
                    brick[i][j] = gameObject.createBrick(i, j, a[i][j]);
                    if (brick[i][j] == null) {
                        continue;
                    }
                    brick[i][j].type = a[i][j];
                    if (brick[i][j].type == "normal") {
                        numBrick = numBrick + 1;
                    }
                    if (brick[i][j].type == "strong") {
                        numBrick = numBrick + 1;
                    }
                    if (brick[i][j].type == "broken") {
                        numBrick = numBrick + 1;
                    }
                    if (brick[i][j].type == "explosive") {
                        numBrick = numBrick + 1;
                    }
                    if (brick[i][j].type == "blind") {
                        numBrick = numBrick + 1;
                    }
                    brick[i][j].Update();
                }
            }
        }
    }

    private void updatePaddle(MainMedia media, AtomicReference<Paddle> paddle,
                              Brick[][] brick, Ball ball, AtomicBoolean gameRestarted,
                              List<PowerUp> powerUps, List<Bullet> bullets, Render render) {
        paddle.get().paddleBullet(bullets);
        if (heartCount.get() == 0) {
            ball.setBall(paddle.get().x + paddle.get().width / 2, HEIGHT - paddleHeightOriginal - 1);
            Platform.runLater(() -> controller.GameOver());
            return;
        }

        if (gameRestarted.get()) {
            int nextPaddleX = (int) paddle.get().getPaddle().getX();
            if (paddle.get().isMoveLeft()) {
                nextPaddleX -= paddle.get().vx;
            }
            if (paddle.get().isMoveRight()) {
                nextPaddleX += paddle.get().vx;
            }
            nextPaddleX = paddle.get().ClampPosition(nextPaddleX);
            ball.setBall(paddle.get().x + paddle.get().width / 2, HEIGHT - paddleHeightOriginal - 1);
            paddle.get().setPaddle(paddle.get().width, nextPaddleX);
            return;
        }


        int nextPaddleX = (int) paddle.get().getPaddle().getX();
        if (paddle.get().isMoveLeft()) {
            nextPaddleX -= paddle.get().vx;
        }
        if (paddle.get().isMoveRight()) {
            nextPaddleX += paddle.get().vx;
        }
        nextPaddleX = paddle.get().ClampPosition(nextPaddleX);
        paddle.get().setPaddle(paddle.get().width, nextPaddleX);

    }


    private boolean updateBall(MainMedia media, Ball ball,
                               Brick[][] brick, AtomicReference<Paddle> paddle,
                               AtomicBoolean gameRestarted, List<PowerUp> powerUps,
                               Render render) {
        int nextBallX = (int) ball.getBall().getCenterX() + (int) ball.vx;
        int nextBallY = (int) ball.getBall().getCenterY() + (int) ball.vy;
        ball.setBall(nextBallX, nextBallY);
        switch (ball.checkWallCollision(gameRestarted)) {
            case -1:
                return false;
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
                    double paddleCenter = paddle.get().getPaddle().getX() + paddle.get().getPaddle().getWidth() / 2.0;
                    double offset = Math.abs(paddleCenter - ball.x) / (paddleCenter - paddle.get().x);
                    double baseAngle = Math.toRadians(45) * offset;
                    if (baseAngle <= Math.toRadians(20)) {
                        baseAngle = Math.toRadians(20);
                    }
                    if (ball.vx >= 0) {
                        ball.vx = (int) (spvxOriginal * Math.sin(baseAngle));
                    } else {
                        ball.vx = -(int) (spvxOriginal * Math.sin(baseAngle));
                    }
                    ball.vy = -(int) Math.abs(spvxOriginal * Math.cos(baseAngle));
                    break;
                case 2:
                case 3:
                    ball.vx = -ball.vx;
                    break;
            }
        }
        int collisionResult = ball.checkBrickCollision(media, brick, render, powerUps, powerUpManager);
        if (collisionResult != 0) {
            if (collisionResult == 1) {
                ball.vx = -ball.vx;
            } else if (collisionResult == 2) {
                ball.vy = -ball.vy;
            }
            //Brick b = ball.getLastHitBrick();
            // if (b != null) b.BallHit(ball, render);
        }
        return true;
    }

    private void updateBalls(MainMedia media, List<Ball> balls, List<Bullet> bullets,
                             Brick[][] brick, AtomicReference<Paddle> paddle, AtomicBoolean gameRestarted,
                             List<PowerUp> powerUps, Render render) {
        for (int i = 0; i < balls.size(); i++) {
            if (i == 0) updatePaddle(media, paddle, brick, balls.get(i), gameRestarted, powerUps, bullets, render);
            if (!updateBall(media, balls.get(i), brick, paddle, gameRestarted, powerUps, render)) {
                balls.remove(i);
                i--;
            }
            if (balls.size() == 0) {
                Ball ball = gameObject.createBall(paddle.get().x + paddleWidthOriginal / 2, HEIGHT - paddleHeightOriginal, "normal");
                balls.add(ball);
                gameRestarted.set(true);
                heartCount.set(heartCount.get() - 1);
                powerUpManager.stop(paddle, balls);
                bullets.clear();
                blind = false;
                powerUps.clear();
            }
        }
    }
}

