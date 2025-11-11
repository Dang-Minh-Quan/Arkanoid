package Brick;

import Ball.Ball;
import Media.*;
import PowerUp.PowerUp;
import PowerUp.PowerUpManager;
import LogicGamePlay.Render;

import java.util.List;

/**
 * Lớp {@code StrongBrick} đại diện cho loại gạch khó phá.
 * Khi bị bóng chạm gạch lần đầu gạch sẽ chuyển sang trạng thái broken, lần 2 sẽ nổ
 */
public class StrongBrick extends Brick {
    /**
     * khoi tao brick cung.
     * @param x vi tri theo phuong x.
     * @param y vi tri theo phuong y.
     */
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
