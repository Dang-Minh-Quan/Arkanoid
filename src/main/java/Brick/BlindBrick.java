package Brick;

import Ball.Ball;
import Media.*;
import PowerUp.PowerUp;
import PowerUp.PowerUpManager;
import LogicGamePlay.Render;

import java.util.List;

public class BlindBrick extends  Brick {
    public BlindBrick(int x, int y) {
        super(x, y);
        this.image = mainImage.getBlindBrick();
        this.type = "blind";
    }
    @Override
    public void BallHit(Ball ball, Render render, MainMedia media, List<PowerUp> powerUps,
                        Brick[][] brick, PowerUpManager powerUpManager) {
        switch (ball.type) {
            case "normal", "infinity":
                destroyBrick(render, media, powerUps);
                break;
            case "explosive":
                boom(render, media, powerUps, brick, powerUpManager);
        }
    }
}

