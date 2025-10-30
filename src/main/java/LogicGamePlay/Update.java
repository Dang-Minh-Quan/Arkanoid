package LogicGamePlay;

import javax.print.attribute.standard.Media;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static LogicGamePlay.Specifications.*;

public class Update {

    public void updateGame(MainMedia media, List<Ball> balls, Ball ball, Paddle paddle, Brick[][] brick, AtomicInteger Level, ArrayList<PowerUp> powerUps) {
        updatePowerUp(balls,ball,paddle,powerUps);
        updateBall(media,ball,brick,paddle,powerUps);
        updateBalls(media,balls,brick,paddle,powerUps);
        updateBrick(balls,ball,paddle,Level, brick);
        updatePaddle(paddle);
    }

    private void updatePowerUp(List<Ball>balls,Ball ball, Paddle paddle, ArrayList<PowerUp> powerUps){
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

    private void updateBrick(List<Ball> balls, Ball ball, Paddle paddle, AtomicInteger Level, Brick[][] brick) {
        if (numBrick <= 0) {
            Level.getAndIncrement();
            if (Level.get() <= LevelMax) {
//                System.out.println(winLevel + " " + Level);
                if (winLevel == false) {
                    builderLevel(balls, ball, brick, paddle, Level);
                    winLevel = true;
                } else {
                    winLevel = false;
                    Platform.runLater(() -> controller.Win());
                }
            } else {
                Level.set(0);
                //WIN
            }
        } else {
            //numBrick--;
        }
    }

    private void builderLevel(List<Ball>balls,Ball ball ,Brick[][] brick, Paddle paddle,AtomicInteger Level) {
        Map map = new Map();
        if (Level.get() > LevelMax) {
            //checkPlay = false;
        } else {
            resert(ball,paddle,balls);
            int[][] a = map.builderMap(Level.get());
            for (int i = 0; i < ROW; i++) {
                for (int j = 0; j < COL; j++) {
                    brick[i][j] = new Brick(i,j);
                    brick[i][j].type=a[i][j];
                    if (brick[i][j].type > 0) {
                        numBrick = numBrick + brick[i][j].type;
                    }
                    brick[i][j].Update();
                }
            }
        }
    }
    private static void updatePaddle(Paddle paddle) {
        paddle.Update();
    }

    private void resert(Ball ball,Paddle paddle,List<Ball>balls){
        ball.x=WIDTH/2;
        ball.y=HEIGHT-70;
        paddle.width=paddleWidthOriginal;
        balls.clear();
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


    private static void updateBall(MainMedia media, Ball ball, Brick[][] brick, Paddle paddle, AtomicBoolean gameRestarted, List<PowerUp> powerUps, Render render) {
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

        switch (ball.checkPaddleCollision(paddle)) {
            case 1:
                ball.vy = -ball.vy;
                break;
            case 2:
                ball.vx = -ball.vx;
                break;
            case 3:
                ball.vx = -ball.vx;
                break;
        }

        switch (ball.checkBrickCollision(media,brick,powerUps)){
            case 1:
                ball.vx = -ball.vx;
                break;
            case 2:
                ball.vy = -ball.vy;
                break;
        }
    }


    private static void updateBalls(MainMedia media,List<Ball>balls,Brick[][] brick,Paddle paddle,ArrayList<PowerUp>powerUps) {
        for (int i = 0; i < balls.size(); i++) {
            balls.get(i).setBall(balls.get(i).x + balls.get(i).vx, balls.get(i).y + balls.get(i).vy);
            switch (balls.get(i).checkWallCollision()) {
                case 1:
                    balls.get(i).vx = -balls.get(i).vx;
                    balls.get(i).vy = -balls.get(i).vy;
                    break;
                case 2:
                    balls.get(i).vx = -balls.get(i).vx;
                    break;
                case 3:
                    balls.get(i).vy = -balls.get(i).vy;
                    break;
            }

            switch (balls.get(i).checkPaddleCollision(paddle)) {
                case 1:
                    balls.get(i).vy = -balls.get(i).vy;
                    break;
                case 2:
                    balls.get(i).vx = -balls.get(i).vx;
                    break;
                case 3:
                    balls.get(i).vx = -balls.get(i).vx;
                    break;
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
