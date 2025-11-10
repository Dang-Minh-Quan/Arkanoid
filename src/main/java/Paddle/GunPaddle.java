package Paddle;

import Ball.Bullet;

import java.util.List;

import static LogicGamePlay.Specifications.ballRadiusOriginal;
import static LogicGamePlay.Specifications.timeButter;

public class GunPaddle extends Paddle {
    /**
     * khoi tao paddle ban.
     * @param x vi tri theo phuong x.
     * @param y vi tri theo phuong y.
     */
    public GunPaddle(int x, int y) {
        super(x, y);
        this.image = mainImage.getGunPaddle();
        this.type = "gun";
    }

    public void paddleBullet(List<Bullet> bullets){
        if (checkBullet <= 0) {
            Bullet bullet1 = new Bullet(x + ballRadiusOriginal, y);
            Bullet bullet2 = new Bullet(x + width - ballRadiusOriginal, y);
            bullets.add(bullet1);
            bullets.add(bullet2);
            checkBullet = timeButter;
        } else {
            checkBullet--;
        }
    }
}
