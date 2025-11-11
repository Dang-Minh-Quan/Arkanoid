package PowerUp;

import LogicGamePlay.*;
import Ball.*;
import Media.MainMedia;
import Paddle.*;

import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

import java.util.concurrent.atomic.AtomicReference;

import static LogicGamePlay.Specifications.*;

public class PowerUp extends AnimationClass {
    private boolean active = true;
    private MainMedia media;
    private Image image;
    private Circle HitBoxPowerUp;
    private GameObject gameObject = new GameObject();
    int TimePowerUp = TimePowerUpOriginal;
    public boolean checkActivate;

    public PowerUp(String type) {
        super(type);
        checkTimePowerUp(type);
        gameObject = new GameObject();
    }

    public PowerUp(Image spriteSheet, int x, int y) {
        super(spriteSheet, x, y, 0, speedPU, RADIUSPU, RADIUSPU, 4, 4, 5);
        this.image = spriteSheet;
        checkActivate = false;
        int randomType = (int) (Math.random() * PU) % PU;
        switch (randomType) {
            case 0:
                type = "infinity ball";
                break;
            case 1:
                type = "long paddle";
                break;
            case 2:
                type = "multi ball";
                break;
            case 3:
                type = "explosive ball";
                break;
            case 4:
                type = "bonus point";
                break;
            case 5:
                type = "gun paddle";
                break;
        }
        checkTimePowerUp(type);
        HitBoxPowerUp = new Circle(x, y, width, Color.BLACK);
    }

    private void checkTimePowerUp(String type) {
        switch (type) {
            case "blind":
                TimePowerUp = TimePowerUpOriginal/2;
                break;
            case "infinity ball":
                TimePowerUp = TimePowerUpOriginal/2;
                break;
            case "gun paddle":
                TimePowerUp = TimePowerUpOriginal;
                break;
            case "multi ball":
                TimePowerUp = 0;
                break;
            case "explosive ball":
                TimePowerUp = TimePowerUpOriginal;
                break;
            case "bonus point":
                TimePowerUp = 0;
                break;
            case "long paddle":
                TimePowerUp = TimePowerUpOriginal;
                break;
        }
    }

    public void update() {
        if (!active) return;
        y += vy;
        if (y > 720) active = false;
        Update();
    }

    public void StopPowerUp(List<Ball> balls, AtomicReference<Paddle> paddle) {
        switch (type) {
            case "blind":
                blind = false;
                break;
            case "infinity ball", "explosive ball":
                for (int i = 0; i < balls.size(); i++) {
                    Ball newball = gameObject.replaceBall(balls.get(i), "normal");
                    balls.set(i, newball);
                }
                break;
            case "gun paddle", "long paddle":
                paddle.set(gameObject.createPaddle(paddle.get().x, paddle.get().y, "normal"));
                break;
        }
        TimePowerUp--;
    }

    public int UpdatePU(List<Ball> balls, AtomicReference<Paddle> paddle, List<PowerUp> powerUps) {
        y = y + vy;
        HitBoxPowerUp.setCenterY(y);
        if (checkActivate == false) {
            if (Shape.intersect(HitBoxPowerUp, paddle.get().getPaddle()).getBoundsInLocal().getWidth() > 0) {
                return 1;
            }
            if (y == HEIGHT + RADIUSPU) {
                return 2;
            }
        }
        return 0;
    }


    public void Activate(List<Ball> balls, AtomicReference<Paddle> paddle) {
        switch (type) {
            case "blind":
                blind = true;
                break;
            case "infinity ball":
              media = MainMedia.getInstance();
              media.playPowerUp();
                for (int i = 0; i < balls.size(); i++) {
                    Ball newball = gameObject.replaceBall(balls.get(i), "infinity");
                    balls.set(i, newball);
                }
                break;
            case "long paddle":
              media = MainMedia.getInstance();
              media.playPowerUp();
                if (paddle.get().type != "long") {
                    int xx = paddleWidthOriginal / 2;
                    if (paddle.get().width + paddle.get().x + xx > WIDTH) {
                        xx = xx + (paddle.get().width + paddle.get().x + paddleWidthOriginal / 2 - WIDTH);
                    } else {
                        if (paddle.get().x - xx < 0) {
                            xx = xx - (paddle.get().x - paddleWidthOriginal / 2);
                        }
                    }
                    paddle.set(gameObject.createPaddle(paddle.get().x - xx, paddle.get().y, "long"));
                }
                break;
            case "multi ball":
              media = MainMedia.getInstance();
              media.playPowerUp();
                Ball newBall = gameObject.createBall(paddle.get().x + paddleWidthOriginal / 2, HEIGHT - paddleHeightOriginal, "normal ball");
                balls.add(newBall);
                break;
            case "explosive ball":
              media = MainMedia.getInstance();
              media.playPowerUp();
                for (int i = 0; i < balls.size(); i++) {
                    Ball newball = gameObject.replaceBall(balls.get(i), "explosive");
                    balls.set(i, newball);
                }
                break;
            case "bonus point":
                media = MainMedia.getInstance();
                media.playPowerUp();
                score.addAndGet(50);
                break;
            case "gun paddle":
              media = MainMedia.getInstance();
              media.playPowerUp();
                paddle.set(gameObject.createPaddle(paddle.get().x, paddle.get().y, "gun"));
                break;
        }
    }

    public void render(GraphicsContext gc) {
        if (!active || image == null) return;
        super.draw(gc);
    }

    public boolean isActive() {
        return active;
    }
}

