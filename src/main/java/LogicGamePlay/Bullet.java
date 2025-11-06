package LogicGamePlay;

import javafx.scene.shape.Rectangle;

import java.util.List;

public class Bullet extends Ball{

    public Bullet(int x, int y){
        super(x,y);
        vy = -vy;
    }

    @Override
    public int checkBrickCollision(MainMedia media, Brick[][] brick, Render render,
                                   List<PowerUp> powerUps,PowerUpManager powerUpManager) {
        return super.checkBrickCollision(media, brick, render, powerUps,powerUpManager);
    }

    public boolean checkBullet(){
        return y<0;
    }

    public void setBullet(int dy) {
        y = dy;
        ball.setCenterY(y);
    }


}
