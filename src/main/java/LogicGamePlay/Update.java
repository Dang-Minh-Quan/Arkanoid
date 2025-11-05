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

  public void updateGame(MainMedia media, List<Ball> balls, Paddle paddle, Brick[][] brick, AtomicInteger Level, AtomicBoolean gameRestarted, List<PowerUp> powerUps, Render render) {
      updatePowerUp(balls, paddle, powerUps);
      updateBalls(media, balls, brick, paddle, gameRestarted, powerUps, render);
      updateBrick(balls, paddle, Level, brick);
  }

  private void updatePowerUp(List<Ball> balls, Paddle paddle, List<PowerUp> powerUps) {
      for (int i = 0; i < powerUps.size(); i++) {
      if (powerUps.get(i).checkActivate == true) {
        if (powerUps.get(i).checkTimePowerUp == -1) {
          powerUps.remove(i);
        } else {
          powerUps.get(i).checkStopPowerUp(balls, paddle);
        }
      } else {
        switch (powerUps.get(i).UpdatePU(balls, paddle, powerUps)) {
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

  public void initializeLevel( Paddle paddle, List<Ball> balls, Brick[][] brick) {
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
      int[][] a = map.builderMap(Level.get());
      for (int i = 0; i < ROW; i++) {
        for (int j = 0; j < COL; j++) {
          brick[i][j] = new Brick(i, j);
          brick[i][j].type = a[i][j];
          if (brick[i][j].type == 1) {
              numBrick = numBrick + 1;
          }
          if (brick[i][j].type == 2) {
              numBrick = numBrick + 1;
          }
          if (brick[i][j].type == 3) {
              numBrick = numBrick + 1;
          }
          if (brick[i][j].type == 4) {
              numBrick = numBrick + 1;
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


  private static boolean updateBall(MainMedia media, Ball ball,
                                 Brick[][] brick, Paddle paddle,
                                 AtomicBoolean gameRestarted, List<PowerUp> powerUps,
                                 Render render) {
    int nextBallX = (int) ball.getBall().getCenterX() + (int) ball.vx;
    int nextBallY = (int) ball.getBall().getCenterY() + (int) ball.vy;
    ball.setBall(nextBallX, nextBallY);
    switch (ball.checkWallCollision( gameRestarted)) {
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
          double baseAngle = Math.toRadians(45) * offset + ((Math.random() % 1) == 1 ? Math.toRadians(10) : -Math.toRadians(10));
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
    int collisionResult = ball.checkBrickCollision(media, brick, render, powerUps);
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

  private void updateBalls(MainMedia media, List<Ball> balls, Brick[][] brick, Paddle paddle, AtomicBoolean gameRestarted, List<PowerUp> powerUps, Render render) {
    for(int i=0;i<balls.size();i++){
        updatePaddle(media, paddle, brick, balls.get(i), gameRestarted, powerUps, render);
        if(!updateBall(media, balls.get(i), brick, paddle, gameRestarted, powerUps, render)){
            balls.remove(i);
        }
        if(balls.size()==0){
            Ball ball=new Ball();
            balls.add(ball);
            gameRestarted.set(true);
            heartCount.set(heartCount.get() - 1);
            for(PowerUp p: powerUps){
                if(p.checkActivate){
                    p.checkTimePowerUp = 0;
                    p.checkStopPowerUp(balls,paddle);
                }
            }
            powerUps.clear();
        }
    }
  }

}

