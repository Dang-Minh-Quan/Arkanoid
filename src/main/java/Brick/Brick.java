package Brick;


import java.util.List;

import static LogicGamePlay.Specifications.*;

import Image.MainImage;
import LogicGamePlay.*;
import Ball.*;
import Media.MainMedia;
import PowerUp.*;


public abstract class Brick extends BaseClass {
    protected boolean cracked;
    protected boolean destroyed;
    protected boolean exploded;

    protected GameObject gameObject;
    protected MainImage mainImage;

    public Brick(int i, int j) {
        super(null, j * WIDTHBrick, i * HEIGHTBrick, WIDTHBrick, HEIGHTBrick);
        cracked = false;
        destroyed = false;
        exploded = false;
        gameObject = new GameObject();
        mainImage = MainImage.getInstance();
    }

    public abstract void BallHit(Ball ball, Render render, MainMedia media, List<PowerUp> powerUps, Brick[][] brick, PowerUpManager powerUpManager);

    public void destroyBrick(Render render, MainMedia media, List<PowerUp> powerUps) {
        if (exploded) return;
        exploded = true;
        destroyed = true;
        type = "null";
        image = null;
        numBrick--;
        score.addAndGet(10);
        explosion(render);
        if ((int) (Math.random() * probability) % probability == 0) {
            render.addPowerUp(x + width / 2 - 15, y + height / 2 - 15, powerUps);
        }
        media.playDestroyBrick();
    }

    public void explosion(Render render) {
        int explosionX = (x + width / 2 - 32);
        int explosionY = (y + height / 2 - 32);
        render.addExplosion(explosionX, explosionY);
    }

    public void boom(Render render, MainMedia media, List<PowerUp> powerUps,
                     Brick[][] brick, PowerUpManager powerUpManager) {
        int[] a = {0, 0, 0, 1, 1, 1, -1, -1, -1};
        int[] b = {0, 1, -1, 1, -1, 0, 1, -1, 0};
        Ball ball = gameObject.createBall(0, 0, "normal");
        int brickCol = x / WIDTHBrick;
        int brickRow = y / HEIGHTBrick;
        for (int i = 0; i <= 8; i++) {
            if (brickRow + a[i] >= 0 && brickRow + a[i] < ROW && brickCol + b[i] >= 0 && brickCol + b[i] < COL) {
                brick[brickRow + a[i]][brickCol + b[i]].BallHit(ball, render, media, powerUps, brick, powerUpManager);
            }
        }
        Update();
    }


    public void Update() {
        if (type == "null") {
            image = null;
        }
        if (type == "normal") {
            image = mainImage.getNormalBrick();
        }
        if (type == "strong") {
            image = mainImage.getStrongBrick();
        }
        if (type == "broken") {
            image = mainImage.getBrokenBrick();
        }
        if (type == "explosive") {
            image = mainImage.getExplosionBrick();
        }
        if (type == "unbreakable") {
            image = mainImage.getUnbreakableBrick();
        }
    }

}