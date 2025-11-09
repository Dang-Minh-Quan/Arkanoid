package Brick;

import Ball.Ball;
import Media.*;
import PowerUp.PowerUp;
import PowerUp.PowerUpManager;
import LogicGamePlay.Render;

import java.util.List;

public class NormalBrick extends Brick {
    public NormalBrick(int x, int y) {
        super(x, y);
        this.type = "normal";
        this.image = mainImage.getNormalBrick();
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
        }
    }
}