package Brick;
import Ball.Ball;
import Media.*;
import PowerUp.PowerUp;
import PowerUp.PowerUpManager;
import LogicGamePlay.Render;

import java.util.List;

import static LogicGamePlay.Specifications.numBrick;

/**
 * Lớp {@code UnbreakableBrick} đại diện cho loại gạch không thể phá hủy.
 */
public class UnbreakableBrick extends Brick {
    /**
     * khoi tao brick khong the pha.
     * @param x vi tri theo phuong x.
     * @param y vi tri theo phuong y.
     */
    public UnbreakableBrick (int x, int y) {
        super(x, y);
        this.image = mainImage.getUnbreakableBrick();
        this.type = "unbreakable";
    }

    @Override
    public void BallHit(Ball ball, Render render, MainMedia media, List<PowerUp> powerUps,
                        Brick[][] brick, PowerUpManager powerUpManager) {
        if(type != "null") {
            switch (ball.type) {
                case "explosive":
                    numBrick++;
                    destroyBrick(render, media, powerUps);
                    boom(render, media, powerUps, brick, powerUpManager);
                    break;
                case "infinity":
                    numBrick++;
                    destroyBrick(render, media, powerUps);
                    break;
                default:
                    media.playImPact();
            }
        }
    }
}
