package LogicGamePlay;

import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Rectangle;

import java.util.concurrent.atomic.AtomicBoolean;

import static LogicGamePlay.Specifications.*;

public class Paddle extends BaseClass {
    private Rectangle paddle;
    private boolean moveLeft;
    private boolean moveRight;
    private int checkBullet = timeButter;

    public Paddle() {
        super(null, 0, WIDTH / 2 - paddleWidthOriginal / 2, HEIGHT - 20, vxOriginal, 0, paddleWidthOriginal, paddleHeightOriginal);
        MainImage newImage = new MainImage();
        image = newImage.getPaddle();
        paddle = new Rectangle(x, y, width, height);
        moveLeft = false;
        moveRight = false;
    }

    public Rectangle getPaddle() {
        return paddle;
    }

    public void setPaddle(double dx) {
        x = (int)dx;
        paddle.setX(dx);
    }

    public void setPaddle(int width,int x){
        this.width = width;
        this.x = x;
        paddle.setWidth(width);
        paddle.setX(x);
    }

    public void paddleBullet(List<Bullet> bullets){
        if(type == 2) {
            if (checkBullet <= 0) {
                Bullet bullet1 = new Bullet(x + ballRadiusOriginal, y);
                Bullet bullet2 = new Bullet(x + width - ballRadiusOriginal, y);
                bullets.add(bullet1);
                bullets.add(bullet2);
                checkBullet = timeButter;
            } else {
                checkBullet--;
            }
        }
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

    public int ClampPosition(int next) {
        if (next < 0) {
            return 0;
        } else if (next + paddle.getWidth() > WIDTH) {
            return WIDTH - (int)paddle.getWidth();
        }
        return next;
    }

    public void Update() {
        MainImage newImage = new MainImage();
        if (type == 0) {
            image = newImage.getPaddle();
        }
        if (type == 1) {
            image = newImage.getPaddle1();
        }
    }

    public void controllerPaddle(Scene scene, AtomicBoolean gameRestarted) {
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case LEFT -> setMoveLeft(true);
                case A -> setMoveLeft(true);
                case RIGHT -> setMoveRight(true);
                case D -> setMoveRight(true);
                case SPACE -> {
                    if (gameRestarted.get()) {
                        gameRestarted.set(false);
                    }
                }
            }
        });

        scene.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case LEFT -> setMoveLeft(false);
                case A -> setMoveLeft(false);
                case RIGHT -> setMoveRight(false);
                case D -> setMoveRight(false);
            }
        });
    }
}
