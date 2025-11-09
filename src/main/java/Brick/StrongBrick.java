package Brick;

import Ball.Ball;
import Media.*;
import PowerUp.PowerUp;
import PowerUp.PowerUpManager;
import LogicGamePlay.Render;

import java.util.List;

public class StrongBrick extends Brick {
    public StrongBrick(int x, int y) {
        super(x, y);
        this.type = "strong";
        this.image = mainImage.getStrongBrick();
    }

    private void BrokenBrick() {
        this.image = mainImage.getBrokenBrick();
    }

    @Override
    public void BallHit(Ball ball, Render render, MainMedia media, List<PowerUp> powerUps,
                        Brick[][] brick, PowerUpManager powerUpManager) {
        if(type != "null") {
            switch (ball.type) {
                case "normal", "bullet":
                    switch (type) {
                        case "broken":
                            destroyBrick(render, media, powerUps);
                            break;
                        case "strong":
                            media.playImPact();
                            BrokenBrick();
                            type = "broken";
                            break;
                    }
                    break;
                case "infinity":
                    destroyBrick(render, media, powerUps);
                    break;
                case "explosive":
                    type = "broken";
                    boom(render, media, powerUps, brick, powerUpManager);
            }
        }
    }
}
