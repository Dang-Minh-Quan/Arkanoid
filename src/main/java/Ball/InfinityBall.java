package Ball;

import Brick.Brick;
import Media.*;
import PowerUp.PowerUp;
import PowerUp.PowerUpManager;
import LogicGamePlay.Render;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class InfinityBall extends Ball {

    public InfinityBall(int x, int y) {
        super(x, y);
        this.image = mainImage.getInfinityBall();
        this.type = "infinity";
        colorTail(0,0,1);
    }

    @Override
    public int checkBrickCollision(MainMedia media, Brick[][] brick, Render render,
                                   List<PowerUp> powerUps, PowerUpManager powerUpManager) {
        super.checkBrickCollision(media,brick,render,powerUps,powerUpManager);
        return 0;
    }

    @Override
    public int checkWallCollision(AtomicBoolean gameRestarted) {
        int k = super.checkWallCollision(gameRestarted);
        if(k == -1){
            k = 3;
        }
        return k;
    }
}