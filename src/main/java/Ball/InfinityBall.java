package Ball;
// gio sao lam cai Design Pattern. doc duoc khong
// duoc, de di hoi may nhom khac xem

import Brick.Brick;
import LogicGamePlay.MainMedia;
import LogicGamePlay.PowerUp;
import LogicGamePlay.PowerUpManager;
import LogicGamePlay.Render;

import java.util.List;

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
}