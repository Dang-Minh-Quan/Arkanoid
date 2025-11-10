package Ball;

import Image.MainImage;
import LogicGamePlay.*;
import Brick.*;
import Media.MainMedia;
import Paddle.*;
import PowerUp.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import LogicGamePlay.BaseClass;
import PowerUp.PowerUp;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.canvas.GraphicsContext;

import static LogicGamePlay.Specifications.*;

public abstract class Ball extends BaseClass {
    protected MainImage mainImage;
    protected Circle ball;
    protected Circle[] Tail = new Circle[TailLength];
    protected int[] TailX = new int[TailLength];
    protected int[] TailY = new int[TailLength];
    protected boolean collidedWithPaddle = false;

    public Ball(int x, int y) {
        super(null, "normal", x, y, 0, - spvxOriginal, ballRadiusOriginal, ballRadiusOriginal);
        ball = new Circle(x, y, width, Color.BLUE);
        mainImage = MainImage.getInstance();
        for (int i = 0; i < TailLength; i++) {
            TailX[i] = x;
            TailY[i] = y;
        }
    }

    public void colorTail(double x, double y, double z) {
        for (int i = 0; i < TailLength; i++) {
            double density = Math.max(0, 1 - 0.5 - (double) i / (double) TailLength / 2);
            Color ColorTail = new Color(x, y, z, density);
            Tail[i] = new Circle(x, y, width - i / 4, ColorTail);
        }
    }

    public void setBall(int dx, int dy) {
        x = dx;
        y = dy;
        ball.setCenterX(x);
        ball.setCenterY(y);
    }

    public void setTail(Ball ball) {
        for (int i = 0; i < TailLength; i++) {
            this.TailX[i] = ball.TailX[i];
            this.TailY[i] = ball.TailY[i];
            Tail[i].setCenterX(TailX[i]);
            Tail[i].setCenterY(TailY[i]);
        }
    }

    public void UpdateTail() {
        int TailX0 = TailX[0];
        int TailY0 = TailY[0];
        int TailX1 = TailX[0];
        int TailY1 = TailY[0];
        int SPTailX = (x - TailX0) / 2;
        int SPTailY = (y - TailY0) / 2;
        TailX0 = TailX0 + SPTailX * 2;
        TailY0 = TailY0 + SPTailY * 2;
        TailX1 = TailX1 + SPTailX;
        TailY1 = TailY1 + SPTailY;
        UpdateNodeTail(TailX0, TailY0, TailX1, TailY1);
    }

    protected void UpdateNodeTail(int TailX0, int TailY0, int TailX1, int TailY1) {
        for (int i = TailLength - 1; i > 1; i--) {
            TailX[i] = TailX[i - 2];
            TailY[i] = TailY[i - 2];
            Tail[i].setCenterX(TailX[i]);
            Tail[i].setCenterY(TailY[i]);
        }
        TailX[0] = TailX0;
        TailY[0] = TailY0;
        Tail[0].setCenterX(TailX[0]);
        Tail[0].setCenterY(TailY[0]);
        TailX[1] = TailX1;
        TailY[1] = TailY1;
        Tail[1].setCenterX(TailX[1]);
        Tail[1].setCenterY(TailY[1]);
    }

    public void RenderTail(GraphicsContext gc) {
        for (int i = 0; i < TailLength; i++) {
            Color c = (Color) Tail[i].getFill();
            gc.setFill(c);
            gc.fillOval(TailX[i] - Tail[i].getRadius(),
                    TailY[i] - Tail[i].getRadius(),
                    Tail[i].getRadius() * 2,
                    Tail[i].getRadius() * 2);
        }
    }

    public Circle getBall() {
        return ball;
    }

    public int checkWallCollision(AtomicBoolean gameRestarted) {
        if (y >= HEIGHT - height) {
            return -1;
        }
        boolean check1 = x <= width || x >= WIDTH - width;
        boolean check2 = y <= height;
        if (check1 && check2) {
            return 1;
        } else if (check1) {
            return 2;
        } else if (check2) {
            return 3;
        }
        return 0;
    }

    public int checkPaddleCollision(AtomicReference<Paddle> paddle) {
        double ballX = x;
        double ballY = y;
        double radius = width;
        double paddleLeft = paddle.get().x;
        double paddleRight = paddleLeft + paddle.get().width;
        double paddleTop = paddle.get().y;
        double paddleBottom = paddleTop + paddle.get().height;

        boolean collisionX = ballX + radius >= paddleLeft && ballX - radius <= paddleRight;
        boolean collisionY = ballY + radius >= paddleTop && ballY - radius <= paddleBottom;

        if (!collisionX || !collisionY) {
            return -1;
        }
        if (ballX <= paddleLeft && !collisionY) {
            return 2;
        } else if (ballX >= paddleRight && !collisionY) {
            return 3;
        }
        return 1;
    }

    public boolean isReadyForPaddleCollision(int collisionState) {
        if (collisionState != -1 && !collidedWithPaddle) {
            collidedWithPaddle = true;
            return true;
        }
        if (collisionState == -1) {
            collidedWithPaddle = false;
        }
        return false;
    }

    public int checkBrickCollision(MainMedia media, Brick[][] brick, Render render,
                                   List<PowerUp> powerUps, PowerUpManager powerUpManager) {
        int brickCol = x / WIDTHBrick;
        int brickRow = y / HEIGHTBrick;
        int reboundAngle = 0;

        int[] Row = {0,0,1,0,-1,-1,1,1,-1};
        int[] Col = {0,1,0,-1,0,1,-1,1,-1};

        for (int k = 0 ; k < 9 ;k++){
            if(k >= 4 && reboundAngle !=0){
                break;
            }
            int i = brickRow + Row[k];
            int j = brickCol + Col[k];
            if(i<0||i>=ROW||j<0||j>=COL){
                continue;
            }
            if("null".equals(brick[i][j].type)){
                continue;
            }
            if(BallCollidingWithBrick(brick[i][j])){
                reboundAngle = reboundAngle+getCollisionDirection(brick[i][j]);
                brick[i][j].BallHit(this, render, media, powerUps, brick, powerUpManager);
            }
        }
        return reboundAngle;
    }

    private boolean BallCollidingWithBrick(Brick brick){
        double closestX = Math.max( brick.x, Math.min(x,brick.x + brick.width));
        double closestY = Math.max( brick.y, Math.min(y,brick.y + brick.height));

        double distanceX = x - closestX;
        double distanceY = y - closestY;
        double distanceSquared = distanceX * distanceX + distanceY * distanceY;

        return distanceSquared < (width * width);
    }

    private int getCollisionDirection(Brick brick) {
        double ballCenterX = x;
        double ballCenterY = y;
        double brickCenterX = brick.x + brick.width / 2;
        double brickCenterY = brick.y + brick.height / 2;

        double dx = Math.abs(ballCenterX - brickCenterX);
        double dy = Math.abs(ballCenterY - brickCenterY);

        if (dx / brick.width > dy / brick.height) {
            return 1;
        } else {
            return 2;
        }
    }
}