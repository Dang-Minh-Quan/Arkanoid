package LogicGamePlay;

import Interface.GamePlayController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

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
                           Paddle paddle, Brick[][] brick,
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
            }
            if (bullets.get(i).checkBrickCollision(media, brick, render, powerUps,powerUpManager) != 0) {
                bullets.remove(i);
            }
        }
    }

    private void updatePowerUp(List<Ball> balls, Paddle paddle, List<PowerUp> powerUps) {
        for (int i = 0; i < powerUps.size(); i++) {
            switch (powerUps.get(i).UpdatePU(balls, paddle, powerUps)) {
                case 1:
                    powerUpManager.applyPowerUp(powerUps.get(i), paddle, balls);
                    powerUps.remove(i);
                    break;
                case 2:
                    powerUps.remove(i);
                    break;
            }
        }
    }

    private void updateBrick(List<Ball> balls, Paddle paddle, AtomicInteger Level, Brick[][] brick) {
        if (numBrick <= 0) {
            if (winLevel == false) {
                winLevel = true;
                Platform.runLater(() -> controller.Win());
            }
        } else {
            winLevel = false;
        }
    }

    public void initializeLevel(Paddle paddle, List<Ball> balls, Brick[][] brick) {
//        System.out.println(Level);
        heartCount.set(3);
        numBrick = 0;
        builderLevel(balls, brick, paddle, Level);
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
                    if (brick[i][j].type == "basic") {
                        numBrick = numBrick + 1;
                    }
                    if (brick[i][j].type == "solid") {
                        numBrick = numBrick + 1;
                    }
                    if (brick[i][j].type == "broken") {
                        numBrick = numBrick + 1;
                    }
                    if (brick[i][j].type == "boom") {
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

    private void updatePaddle(MainMedia media, Paddle paddle,
                              Brick[][] brick, Ball ball, AtomicBoolean gameRestarted,
                              List<PowerUp> powerUps, List<Bullet> bullets, Render render) {
        paddle.paddleBullet(bullets);
        if (heartCount.get() == 0) {
            ball.setBall(paddle.x + paddle.width / 2, HEIGHT - 60);
            Platform.runLater(() -> controller.GameOver());
            return;
        }

        if (gameRestarted.get()) {
            int nextPaddleX = (int) paddle.getPaddle().getX();
            if (paddle.isMoveLeft()) {
                nextPaddleX -= paddle.vx;
            }
            if (paddle.isMoveRight()) {
                nextPaddleX += paddle.vx;
            }
            nextPaddleX = paddle.ClampPosition(nextPaddleX);
            ball.setBall(paddle.x + paddle.width / 2, HEIGHT - 70);
            paddle.setPaddle(paddle.width, nextPaddleX);
            return;
        }

        updateBall(media, ball, brick, paddle, gameRestarted, powerUps, render);

        int nextPaddleX = (int) paddle.getPaddle().getX();
        if (paddle.isMoveLeft()) {
            nextPaddleX -= paddle.vx;
        }
        if (paddle.isMoveRight()) {
            nextPaddleX += paddle.vx;
        }
        nextPaddleX = paddle.ClampPosition(nextPaddleX);
        paddle.setPaddle(paddle.width, nextPaddleX);

    }


    private boolean updateBall(MainMedia media, Ball ball,
                                      Brick[][] brick, Paddle paddle,
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
                    double paddleCenter = paddle.getPaddle().getX() + paddle.getPaddle().getWidth() / 2.0;
                    double offset = Math.abs(paddleCenter - ball.x) / (paddleCenter - paddle.x);
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
        int collisionResult = ball.checkBrickCollision(media, brick, render, powerUps,powerUpManager);
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
                             Brick[][] brick, Paddle paddle, AtomicBoolean gameRestarted,
                             List<PowerUp> powerUps, Render render) {
        for (int i = 0; i < balls.size(); i++) {
            if (i == 0) updatePaddle(media, paddle, brick, balls.get(i), gameRestarted, powerUps, bullets, render);
            if (!updateBall(media, balls.get(i), brick, paddle, gameRestarted, powerUps, render)) {
                balls.remove(i);
            }
            if (balls.size() == 0) {
                Ball ball = gameObject.createBall("normal ball");
                balls.add(ball);
                gameRestarted.set(true);
                heartCount.set(heartCount.get() - 1);
                powerUpManager.stop(paddle,balls);
                bullets.clear();
                blind = false;
                powerUps.clear();
            }
        }
    }
}

