package LogicGamePlay;

import Ball.*;
import Brick.*;
import Paddle.*;

public class GameObject {
    public Ball createBall(int x, int y, String type) {
        if (type == null) {
            return new NormalBall(x, y);
        }
        if (type.equals("infinity")) {
            return new InfinityBall(x, y);
        }
        if (type.equals("explosive")) {
            return new ExplosiveBall(x, y);
        }
        return new NormalBall(x, y);
    }

    public Paddle createPaddle(int x, int y, String type) {
        if (type == null) {
            return new NormalPaddle(x, y);
        }
        if (type.equals("long")) {
            return new LongPaddle(x, y);
        }
        if (type.equals("gun")) {
            return new GunPaddle(x, y);
        }
        return new NormalPaddle(x, y);
    }

    public Brick createBrick(int row, int col, String type) {
        if (type == null) {
            return new NormalBrick(row, col);
        }
        if (type.equals("null")) {
            return new NullBrick(row, col);
        }
        if (type.equals("strong")) {
            return new StrongBrick(row, col);
        }
        if (type.equals("unbreakable")) {
            return new UnbreakableBrick(row, col);
        }
        if (type.equals("explosive")) {
            return new ExplosiveBrick(row, col);
        }
        if (type.equals("blind")) {
            return new BlindBrick(row, col);
        }
        return new NormalBrick(row, col);
    }

    public Ball replaceBall(Ball ball,String type){
        Ball newball = createBall(ball.x, ball.y, type);
        newball.vx = ball.vx;
        newball.vy = ball.vy;
        newball.setTail(ball);
        return newball;
    }
}
