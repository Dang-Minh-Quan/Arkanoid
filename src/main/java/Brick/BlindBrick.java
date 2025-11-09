package Brick;

import Ball.Ball;
import Media.*;
import Paddle.Paddle;
import PowerUp.PowerUp;
import PowerUp.PowerUpManager;
import LogicGamePlay.Render;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class BlindBrick extends  Brick {
    public BlindBrick(int x, int y) {
        super(x, y);
        this.image = mainImage.getBlindBrick();
        this.type = "blind";
    }
    @Override
    public void BallHit(Ball ball, Render render, MainMedia media, List<PowerUp> powerUps,
                        Brick[][] brick, PowerUpManager powerUpManager) {
        if(type != "null") {
            switch (ball.type) {
                case "normal", "infinity", "bullet":
                    destroyBrick(render, media, powerUps);
                    break;
                case "explosive":
                    boom(render, media, powerUps, brick, powerUpManager);
            }
            PowerUp powerUp = new PowerUp("blind");
            AtomicReference<Paddle> paddle = new AtomicReference<>();
            List<Ball> balls = new ArrayList<>();
            powerUpManager.applyPowerUp(powerUp, paddle, balls);
        }
    }
}

