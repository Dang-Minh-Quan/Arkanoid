package com.example.arkanoid;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import static com.example.arkanoid.Specifications.*;

public class Paddle extends BaseClass {
    private Rectangle paddle;
    private boolean moveLeft;
    private boolean moveRight;

    public Paddle() {
        super(null, 0, (WIDTH - 100) / 2, HEIGHT - 50, 7, 0, paddleWidthOriginal, paddleHeightOriginal);
        paddle = new Rectangle(x, y, width, height);
        moveLeft = false;
        moveRight = false;
    }

    public Rectangle getPaddle() {
        return paddle;
    }

    public void setPaddle(double dx) {
        x = dx;
        paddle.setX(dx);
    }

    public boolean isMoveLeft() {
        return moveLeft;
    }

    public void setMoveLeft(boolean moveLeft) {
        this.moveLeft = moveLeft;
    }

    public boolean isMoveRight() {
        return moveRight;
    }

    public void setMoveRight(boolean moveRight) {
        this.moveRight = moveRight;
    }

    public double ClampPosition(double next) {
        if (next < 0) {
            return 0;
        } else if (next + paddle.getWidth() > WIDTH) {
            return WIDTH - paddle.getWidth();
        }
        return next;
    }

    @Override
    public void Update() {

    }
}
