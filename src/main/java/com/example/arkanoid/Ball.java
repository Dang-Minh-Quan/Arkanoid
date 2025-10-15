package com.example.arkanoid;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import static com.example.arkanoid.Specifications.*;

public class Ball extends BaseClass {
    private Circle ball;
    private boolean collidedWithPaddle = false;

    public Ball(){
        super(null, 0, 500, 400, spvxOriginal, spvxOriginal, 10, 10);
        ball = new Circle(x, y, width, Color.BLUE);
    }
    public void setBall(double dx, double dy) {
        x = dx;
        y = dy;
        ball.setCenterX(x);
        ball.setCenterY(y);
    }

    @Override
    public void Update() {

    }

    public Circle getBall() {
        return ball;
    }

    public int checkWallCollision() {
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

    public int checkPaddleCollision(Paddle paddle) {
        double ballX = x;
        double ballY = y;
        double radius = width;
        double paddleLeft = paddle.x;
        double paddleRight = paddleLeft + paddle.width;
        double paddleTop = paddle.y;
        double paddleBottom = paddleTop + paddle.height;

        boolean collisionX = ballX + radius >= paddleLeft && ballX - radius <= paddleRight;
        boolean collisionY = ballY + radius >= paddleTop && ballY - radius <= paddleBottom;

        if (!collisionX || !collisionY) {
            return -1;
        }
        if (ballX <= paddleLeft) {
            return 2;
        } else if (ballX >= paddleRight) {
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
}
