package Ball;

import Brick.Brick;
import Media.*;
import PowerUp.PowerUp;
import PowerUp.PowerUpManager;
import LogicGamePlay.Render;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class InfinityBall extends Ball {
    /**
     * khoi tao bong bat tu.
     * @param x vi tri theo phuong x.
     * @param y vi tri theo phuong y.
     */
    public InfinityBall(int x, int y) {
        super(x, y);
        this.image = mainImage.getInfinityBall();
        this.type = "infinity";
        colorTail(233 / 255.0,1,129 / 255.0);
    }

    @Override
    public int checkBrickCollision(MainMedia media, Brick[][] brick, Render render,
                                   List<PowerUp> powerUps, PowerUpManager powerUpManager) {
        super.checkBrickCollision(media,brick,render,powerUps,powerUpManager);
        return 0;
    }

    @Override
    public int checkWallCollision() {
        int k = super.checkWallCollision();
        if (k == -1) {
            k = 3;
        }
        return k;
    }
}