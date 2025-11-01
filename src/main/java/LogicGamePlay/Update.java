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

  public Update(GamePlayController controller) {
    this.controller = controller;
  }

  public void updateGame(MainMedia media, List<Ball> balls, Ball ball, Paddle paddle, Brick[][] brick, AtomicInteger Level, AtomicBoolean gameRestarted, List<PowerUp> powerUps, Render render) {
    updatePowerUp(balls, ball, paddle, powerUps);
    updateBall(media, ball, brick, paddle, gameRestarted, powerUps, render);
    updatePaddle(media, paddle, brick, ball, gameRestarted, powerUps, render);
    updateBalls(media, balls, brick, paddle, gameRestarted, powerUps, render);
    updateBrick(balls, ball, paddle, Level, brick);
  }

  private void updatePowerUp(List<Ball> balls, Ball ball, Paddle paddle, List<PowerUp> powerUps) {
      for (int i = 0; i < powerUps.size(); i++) {
      if (powerUps.get(i).checkActivate == true) {
        if (powerUps.get(i).checkTimePowerUp == -1) {
          powerUps.remove(i);
        } else {
          powerUps.get(i).checkStopPowerUp(balls, paddle, ball);
        }
      } else {
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

  private void updateBrick(List<Ball> balls, Ball ball, Paddle paddle, AtomicInteger Level, Brick[][] brick) {
    if (numBrick <= 0) {
      if (winLevel == false) {
        winLevel = true;
        Platform.runLater(() -> controller.Win());
      }
    } else {
      winLevel = false;
    }
  }

  public void initializeLevel(Ball ball, Paddle paddle, List<Ball> balls, Brick[][] brick) {
//        System.out.println(Level);
    heartCount.set(3);
    numBrick = 0;
    builderLevel(balls, ball, brick, paddle, Level);
  }

  private void builderLevel(List<Ball> balls, Ball ball, Brick[][] brick, Paddle paddle, AtomicInteger Level) {
    Map map = new Map();
    if (Level.get() > LevelMax) {
//            checkPlay = false;
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

  private void updatePaddle(MainMedia media, Paddle paddle,
                            Brick[][] brick,Ball ball, AtomicBoolean gameRestarted,
                            List<PowerUp> powerUps, Render render) {
    if (heartCount.get() == 0) {
      ball.setBall(paddle.x + paddle.width / 2, HEIGHT - 60);
      Platform.runLater(() -> controller.GameOver());
      return;
    }

    if (gameRestarted.get()) {
      int nextPaddleX = (int)paddle.getPaddle().getX();
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

    updateBall(media, ball, brick, paddle, gameRestarted, powerUps, render);

    int nextPaddleX = (int) paddle.getPaddle().getX();
    if (paddle.isMoveLeft()) {
      nextPaddleX -= paddle.vx;
    }
    if (paddle.isMoveRight()) {
      nextPaddleX += paddle.vx;
    }
    nextPaddleX = paddle.ClampPosition(nextPaddleX);
    paddle.setPaddle(nextPaddleX);

  }


  private static void updateBall(MainMedia media, Ball ball,
                                 Brick[][] brick, Paddle paddle,
                                 AtomicBoolean gameRestarted, List<PowerUp> powerUps,
                                 Render render) {
    int nextBallX = (int) ball.getBall().getCenterX() + (int) ball.vx;
    int nextBallY = (int) ball.getBall().getCenterY() + (int) ball.vy;
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
          double baseAngle = Math.toRadians(45) * offset + Math.toRadians(10);
          if (ball.vx >= 0) {
            ball.vx = (int)(spvxOriginal * Math.sin(baseAngle));
          } else {
            ball.vx = -(int)(spvxOriginal * Math.sin(baseAngle));
          }
          ball.vy = -(int)Math.abs(spvxOriginal * Math.cos(baseAngle));
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

  private void updateBalls(MainMedia media, List<Ball> balls, Brick[][] brick, Paddle paddle, AtomicBoolean gameRestarted, List<PowerUp> powerUps, Render render) {
    for(Ball b : balls){
        updatePaddle(media, paddle, brick, b, gameRestarted, powerUps, render);
        updateBall(media, b, brick, paddle, gameRestarted, powerUps, render);
    }
  }

}

