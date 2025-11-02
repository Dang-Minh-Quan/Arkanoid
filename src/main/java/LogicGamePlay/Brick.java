package LogicGamePlay;


import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.List;

import static LogicGamePlay.Specifications.*;

public class Brick extends BaseClass {
    private boolean cracked = false;
    private boolean destroyed = false;
    private boolean exploded = false;

    private MainImage imageLoad;
    public Brick(int i,int j) {
        super(null, j*WIDTHBrick, i*HEIGHTBrick, WIDTHBrick,HEIGHTBrick);
    }

    public void BallHit(Ball ball, Render render, MainMedia media, List<PowerUp> powerUps,Brick[][] brick) {
        System.out.println(ball.type);
        switch (ball.type) {
            case 0,2:
                if (type == 0 || destroyed) {
                return;
                } else {
                switch (type) {
                    case 1:
                        destroyBrick(render, media, powerUps);
                        break;
                    case 2:
                        if (!cracked) {
                            cracked = true;
                            type = 3;
                            Update();
                        } else {
                            destroyBrick(render, media, powerUps);
                        }
                        break;
                    case 3:
                        destroyBrick(render, media, powerUps);
                        break;
                    case 4:
                        type = 1;
                        boom(render, media, powerUps, brick);
                        destroyBrick(render, media, powerUps);
                        break;
                }
            }
            break;
            case 1:
            {
                if (type == 0 || destroyed) {
                    return;
                }else {
                    if(type == -1){numBrick=numBrick+1;}
                    type = 1;
                    boom(render, media, powerUps, brick);
                    destroyBrick(render, media, powerUps);
                }
            }
        }
    }

    public void destroyBrick(Render render,MainMedia media, List<PowerUp> powerUps) {
        if (exploded) return;
        exploded = true;
        destroyed = true;
        type = 0;
        numBrick--;
        explosion(render);
        if((int)(Math.random()*probability)%probability==0) {
            render.addPowerUp(x + width / 2 - 15, y + height / 2 - 15,powerUps);
        }
        media.playDestroyBrick();
    }

    public void explosion(Render render) {
        int explosionX =  (x + width / 2 - 32);
        int explosionY =  (y + height / 2 - 32);
        render.addExplosion(explosionX, explosionY);
    }

    public void boom(Render render,MainMedia media, List<PowerUp> powerUps,Brick[][] brick){
        int[] a={0,0,1,1,1,-1,-1,-1};
        int[] b={1,-1,1,-1,0,1,-1,0};
        Ball ball = new Ball();
        int brickCol = x / WIDTHBrick;
        int brickRow = y / HEIGHTBrick;
        for (int i = 0;i <=7 ;i++){
            if(brickRow+a[i]>=0&&brickRow+a[i]<ROW&&brickCol+b[i]>=0&&brickCol+b[i]<COL){
                brick[brickRow+a[i]][brickCol+b[i]].BallHit(ball,render,media,powerUps,brick);
            }
        }
    }


    public void Update() {
        MainImage newImage = new MainImage();
        if (type == 0) {
            image = null;
        }
        if (type == 1) {
            image = newImage.getBrick1();
        }
        if (type == 2) {
            image = newImage.getBrick3();
        }
        if (type == 3) {
            image = newImage.getBrickBroken();
        }
        if (type == 4) {
            image = newImage.getBrick4();
        }
        if (type == -1) {
            image = newImage.getBrick2();
        }
    }

    public Rectangle builderBrick(int i, int j) {
        Rectangle brick = new Rectangle(x, y, width, height);
        if (type == 0) {
            brick.setFill(Color.TRANSPARENT);
            brick.setStroke(Color.TRANSPARENT);
        }
        else  {
            brick.setFill(new ImagePattern(image));
            brick.setStroke(Color.BLACK);
        }
        return brick;
    }
}