package Brick;

import Ball.Ball;
import Media.*;
import PowerUp.PowerUp;
import PowerUp.PowerUpManager;
import LogicGamePlay.Render;

import java.util.List;

public class ExplosiveBrick extends Brick {
    boolean detonation;

    public ExplosiveBrick(int x, int y) {
        super(x, y);
        detonation = false;
        this.image = mainImage.getExplosionBrick();
        this.type = "explosive";
    }

    @Override
    public void BallHit(Ball ball, Render render, MainMedia media, List<PowerUp> powerUps,
                        Brick[][] brick, PowerUpManager powerUpManager) {
        if(detonation == true){
            destroyBrick(render, media, powerUps);
        } else {
            detonation = true;
            boom(render, media, powerUps, brick, powerUpManager);
        }
    }
}
