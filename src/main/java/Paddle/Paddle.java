package Paddle;
import Image.MainImage;
import LogicGamePlay.*;
import Ball.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;

import static LogicGamePlay.Specifications.*;

public abstract class Paddle extends BaseClass {
    protected Rectangle paddle;
    protected boolean moveLeft;
    protected boolean moveRight;
    protected int checkBullet = timeButter;
    protected MainImage mainImage;

    public Paddle(int x, int y) {
        super(null, "normal", x, y, vxOriginal, 0, paddleWidthOriginal, paddleHeightOriginal);
        mainImage = MainImage.getInstance();
        paddle = new Rectangle(x, y, width, height);
        moveLeft = false;
        moveRight = false;
    }

    public Rectangle getPaddle() {
        return paddle;
    }

    public void setPaddle(int x) {
        this.x = x;
        paddle.setX(x);
    }

    public void setPaddle(int width, int x) {
        this.width = width;
        this.x = x;
        paddle.setWidth(width);
        paddle.setX(x);
    }

    public void paddleBullet(List<Bullet> bullets){}

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

    public int ClampPosition(int next) {
        if (next < 0) {
            return 0;
        } else if (next + paddle.getWidth() > WIDTH) {
            return WIDTH - (int) paddle.getWidth();
        }
        return next;
    }

    @Override
    public void Update() {
    }
}
