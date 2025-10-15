package com.example.arkanoid;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import static com.example.arkanoid.Specifications.*;

public class Ball extends BaseClass {
    private Circle ball;
    private boolean collidedWithPaddle = false;

    public Ball(){
        super(null, 0, 450, HEIGHT - 70, spvxOriginal, spvxOriginal, 10, 10);
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

    public int checkBrickCollision(Brick[][] brick) {
        int brickCol = (int)x / (int)WIDTHBrick ;
        int brickRow = (int)y / (int)HEIGHTBrick ;

        boolean checkX = false, checkY = false, top = false, below = false, left = false, right = false;

        if (vy < 0 && brickRow > 0 && brickRow < ROW && (brickRow) * HEIGHTBrick + width >= y) {
            top = true;
        }
        if (vy > 0 && brickRow < ROW - 1 && (brickRow + 1) * HEIGHTBrick - width <= y) {
            below = true;
        }
        if (vx < 0 && brickCol > 0 && brickRow < ROW && (brickCol) * WIDTHBrick + width >= x) {
            left = true;
        }
        if (vx > 0 && brickCol < COL - 1 && brickRow < ROW && (brickCol + 1) * WIDTHBrick - width <= x) {
            right = true;
        }

        if (top == true) {
            if (brick[brickRow - 1][brickCol].type != 0) {
                brick[brickRow - 1][brickCol].UpdateBrick(this);
                checkY = true;
            }
        }
        if (below == true) {
            if (brick[brickRow + 1][brickCol].type != 0) {
                brick[brickRow + 1][brickCol].UpdateBrick(this);
                checkY = true;
            }
        }
        if (left == true){
            if (brick[brickRow][brickCol - 1].type != 0) {
                brick[brickRow][brickCol - 1].UpdateBrick(this);
                checkX = true;
            }
        }
        if (right == true){
            if(brick[brickRow][brickCol + 1].type != 0) {
                brick[brickRow][brickCol + 1].UpdateBrick(this);
                checkX = true;
            }
        }
        if (checkX == true && checkY == false) {
            return 1;
        }
        if (checkX == false && checkY == true) {
            return 2;
        }
        if (checkX == true && checkY == true) {
            return 3;
        }
        if (top == true && left == true) {
            if (brick[brickRow - 1][brickCol - 1].type != 0) {
                brick[brickRow - 1][brickCol - 1].UpdateBrick(this);
                if (Math.abs((brickRow) * HEIGHTBrick - (int)y) > Math.abs((brickCol) * WIDTHBrick - (int)x)) {
                    return 2;
                } else {
                    return 1;
                }
            }
        }
        if (below == true && left == true) {
            if (brick[brickRow + 1][brickCol - 1].type != 0) {
                brick[brickRow + 1][brickCol - 1].UpdateBrick(this);
                if (Math.abs((brickRow + 1) * HEIGHTBrick - (int)y) > Math.abs((brickCol) * WIDTHBrick - (int)x)) {
                    return 2;
                } else {
                    return 1;
                }
            }
        }
        if (top == true && right == true) {
            if (brick[brickRow - 1][brickCol + 1].type != 0) {
                brick[brickRow - 1][brickCol + 1].UpdateBrick(this);
                if (Math.abs((brickRow) * HEIGHTBrick - (int)y) > Math.abs((brickCol + 1) * WIDTHBrick - (int)x)) {
                    return 2;
                } else {
                    return 1;
                }
            }
        }
        if (below == true && right == true) {
            if (brick[brickRow + 1][brickCol + 1].type != 0) {
                brick[brickRow + 1][brickCol + 1].UpdateBrick(this);
                if (Math.abs((brickRow + 1) * HEIGHTBrick - (int)y) > Math.abs((brickCol + 1) * WIDTHBrick - (int)x)) {
                    return 2;
                } else {
                    return 1;
                }
            }
        }
        return 0;
    }
}
