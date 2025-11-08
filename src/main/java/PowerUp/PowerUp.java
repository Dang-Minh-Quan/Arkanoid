package PowerUp;

import LogicGamePlay.*;
import Ball.*;
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

    private Image image;
    private Circle HitBoxPowerUp;
    private GameObject gameObject = new GameObject();
    int TimePowerUp = TimePowerUpOriginal;
    public boolean checkActivate = false;

    public PowerUp(String type) {
        super(type);
        gameObject = new GameObject();
    }

    public PowerUp(Image spriteSheet, int x, int y) {
        super(spriteSheet, x, y, 0, speedPU, RADIUSPU, RADIUSPU, 4, 4, 5);
        this.image = spriteSheet;
        int randomType = (int) (Math.random() * PU) % PU;
        switch (randomType) {
            case 0:
                type = "ball_immortal";
                break;
            case 1:
                type = "paddle_shoot";
                break;
            case 2:
                type = "ball_add";
                break;
            case 3:
                type = "ball_boom";
                break;
            case 4:
                type = "bonus_point";
                break;
            case 5:
                type = "paddle_long";
                break;
        }
        HitBoxPowerUp = new Circle(x, y, width, Color.BLACK);
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
            case "ball_immortal", "ball_boom":
                for (int i = 0; i < balls.size(); i++) {
                    Ball newball = gameObject.replaceBall(balls.get(i),"normal");
                    balls.set(i, newball);
                }
                break;
            case "paddle_shoot", "paddle_long":
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
                Activate(balls, paddle);
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
            case "ball_immortal":
                for (int i = 0; i < balls.size(); i++) {
                    Ball newball = gameObject.replaceBall(balls.get(i), "infinity");
                    balls.set(i, newball);
                }
                break;
            case "paddle_long":
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
            case "ball_add":
                Ball newBall = gameObject.createBall(paddle.get().x + paddleWidthOriginal / 2, HEIGHT - paddleHeightOriginal, "normal ball");
                balls.add(newBall);
                break;
            case "ball_boom":
                for (int i = 0; i < balls.size(); i++) {
                    Ball newball = gameObject.replaceBall(balls.get(i),"explosive");
                    balls.set(i, newball);
                }
                break;
            case "bonus_point":
                score.addAndGet(10);
                break;
            case "paddle_shoot":
                paddle.set(gameObject.createPaddle(paddle.get().x, paddle.get().y, "gun"));
                break;
        }
    }

//    private void removePowerUp(List<Ball> balls, Paddle paddle, List<PowerUp> powerUps, int Type) {
//        for (int i = 0; i < powerUps.size(); i++) {
//            PowerUp p = powerUps.get(i);
//            if (p.type == Type && p.checkActivate == true) {
//                powerUps.get(i).checkTimePowerUp = 0;
//                powerUps.get(i).checkStopPowerUp(balls, paddle);
//                powerUps.remove(i);
//                break;
//            }
//        }
//    }

    public void render(GraphicsContext gc) {
        if (!active || image == null) return;
        super.draw(gc);
    }

    public boolean isActive() {
        return active;
    }
}

