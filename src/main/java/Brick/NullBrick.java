package Brick;

import Ball.Ball;
import Media.*;
import PowerUp.PowerUp;
import PowerUp.PowerUpManager;
import LogicGamePlay.Render;

import java.util.List;

/**
 * Lớp {@code NullBrick} đại diện cho không có gạch.
 */
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

