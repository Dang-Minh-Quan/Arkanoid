package Brick;

import Ball.Ball;
import LogicGamePlay.MainMedia;
import LogicGamePlay.PowerUp;
import LogicGamePlay.PowerUpManager;
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
        switch (ball.type) {
            case "normal", "infinity", "bullet":
                switch (type) {
                    case "broken":
                        destroyBrick(render, media, powerUps);
                    case "strong":
                        BrokenBrick();
                        type = "broken";
                }
                break;
            case "explosive":
                type = "broken";
                boom(render, media, powerUps, brick, powerUpManager);
        }
    }
}
