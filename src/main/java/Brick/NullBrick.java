package Brick;

import Ball.Ball;
import LogicGamePlay.MainMedia;
import LogicGamePlay.PowerUp;
import LogicGamePlay.PowerUpManager;
import LogicGamePlay.Render;

import java.util.List;

public class NullBrick extends Brick {
    public NullBrick(int x, int y) {
        super(x, y);
        this.type = "null";
        this.image = null;
    }

    @Override
    public void BallHit(Ball ball, Render render, MainMedia media, List<PowerUp> powerUps,
                        Brick[][] brick, PowerUpManager powerUpManager) {
    }
}

