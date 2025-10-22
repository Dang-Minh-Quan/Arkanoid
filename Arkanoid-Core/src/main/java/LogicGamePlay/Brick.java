package LogicGamePlay;


import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import static LogicGamePlay.Specifications.*;

public class Brick extends BaseClass {
    private boolean cracked = false;
    private boolean destroyed = false;
    private boolean exploded = false;

    private MainImage imageLoad;
    public Brick(int i,int j) {
        super(null, j*WIDTHBrick, i*HEIGHTBrick, WIDTHBrick,HEIGHTBrick);
    }

    public void BallHit(Ball ball,  Render render) {

        if(type == 0 ||  destroyed) {
            return;
        }
        if(type == 2) {
            if(!cracked) {
                cracked = true;
                type = 3;
                Update();
                return;
            } else {
                destroyBrick(render);
            }
        }
        else if (type == 1) {
            destroyBrick(render);
        }
        else if(type == 3) {
            destroyBrick(render);
        }
    }

    public void destroyBrick(Render render) {
        if (exploded) return;
        exploded = true;
        destroyed = true;
        type = 0;
        numBrick--;
        explosion(render);
    }

    public void explosion(Render render) {
        int explosionX = (int) (x + width / 2 - 32);
        int explosionY = (int) (y + height / 2 - 32);
        render.addExplosion(explosionX, explosionY);
        System.out.println("Explosion added at: " + explosionX + ", " + explosionY);

    }

    public void UpdateBrick(Ball ball) {
        if (ball.type == 0) {
            if (type > 0) {
                type = type - 1;
                numBrick = numBrick - 1;
            }
            if (type == -1) {
                type = -1;
            }
        }
        Update();
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
        } else {
            brick.setFill(new ImagePattern(image));
            brick.setStroke(Color.BLACK);
        }
        return brick;
    }

}