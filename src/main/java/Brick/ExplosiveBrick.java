package Brick;

import Ball.Ball;
import Media.*;
import PowerUp.PowerUp;
import PowerUp.PowerUpManager;
import LogicGamePlay.Render;

import java.util.List;

/**
 * Lớp {@code ExplosiveBrick} đại diện cho loại gạch nổ trong game.
 * Khi bị bóng chạm gạch nổ sẽ phát nổ và gây ảnh hưởng đến các viên gạch xung quanh
 */
public class ExplosiveBrick extends Brick {
    boolean detonation;

    /**
     * khoi tao brick no.
     * @param x vi tri theo phuong x.
     * @param y vi tri theo phuong y.
     */
    public ExplosiveBrick(int x, int y) {
        super(x, y);
        detonation = false;
        this.image = mainImage.getExplosionBrick();
        this.type = "explosive";
    }

    @Override
    public void BallHit(Ball ball, Render render, MainMedia media, List<PowerUp> powerUps,
                        Brick[][] brick, PowerUpManager powerUpManager) {
        if(type != "null") {
            if (detonation == true) {
                destroyBrick(render, media, powerUps);
            } else {
                detonation = true;
                boom(render, media, powerUps, brick, powerUpManager);
            }
        }
    }
}
