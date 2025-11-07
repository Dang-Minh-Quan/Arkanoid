package LogicGamePlay;


import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

import static LogicGamePlay.Specifications.*;

public abstract class Brick extends BaseClass {
    private boolean cracked = false;
    private boolean destroyed = false;
    private boolean exploded = false;

    private GameObject gameObject;
    private MainImage imageLoad;

    public Brick(int i, int j) {
        super(null, j * WIDTHBrick, i * HEIGHTBrick, WIDTHBrick, HEIGHTBrick);
        gameObject = new GameObject();
    }

    public void BallHit(Ball ball, Render render, MainMedia media, List<PowerUp> powerUps,
                        Brick[][] brick, PowerUpManager powerUpManager) {
        System.out.println(ball.type);
        switch (ball.type) {
            case "basic", "immortal":
                if (type == "non" || destroyed) {
                    return;
                } else {
                    switch (type) {
                        case "basic":
                            destroyBrick(render, media, powerUps);
                            break;
                        case "solid":
                            if (!cracked) {
                                cracked = true;
                                type = "broken";
                                Update();
                            } else {
                                destroyBrick(render, media, powerUps);
                            }
                            break;
                        case "broken":
                            destroyBrick(render, media, powerUps);
                            break;
                        case "boom":
                            type = "basic";
                            boom(render, media, powerUps, brick, powerUpManager);
                            destroyBrick(render, media, powerUps);
                            break;
                        case "blind":
                            PowerUp p = new PowerUp("blind");
                            powerUpManager.applyPowerUp(p, gameObject.createPaddle("normal"), new ArrayList<>());
                            destroyBrick(render, media, powerUps);
                    }
                }
                break;
            case "boom": {
                if (type == "non" || destroyed) {
                    return;
                } else {
                    if (type == "hard") {
                        numBrick = numBrick + 1;
                    }
                    if (type == "hard" || type == "solid" || type == "boom") {
                        type = "basic";
                    }
                    boom(render, media, powerUps, brick, powerUpManager);
                }
            }
        }
    }

    public void destroyBrick(Render render, MainMedia media, List<PowerUp> powerUps) {
        if (exploded) return;
        exploded = true;
        destroyed = true;
        type = "non";
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
        Ball ball = gameObject.createBall("normal type");
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
        MainImage newImage = MainImage.getInstance();
        if (type == "non") {
            image = null;
        }
        if (type == "basic") {
            image = newImage.getBrick1();
        }
        if (type == "solid") {
            image = newImage.getBrick3();
        }
        if (type == "broken") {
            image = newImage.getBrickBroken();
        }
        if (type == "boom") {
            image = newImage.getBrick4();
        }
        if (type == "hard") {
            image = newImage.getBrick2();
        }
    }

}