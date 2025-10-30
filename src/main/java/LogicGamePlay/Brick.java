package LogicGamePlay;


import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

import static LogicGamePlay.Specifications.*;

public class Brick extends BaseClass {
    public Brick(int i,int j) {
        super( j*WIDTHBrick, i*HEIGHTBrick, WIDTHBrick,HEIGHTBrick);
    }

    public void UpdateBrick(MainMedia media,Ball ball, ArrayList<PowerUp> powerUps) {
        if (ball.type == 0) {
            if (type > 0) {
                type = type - 1;
                numBrick = numBrick - 1;
                if((int)(Math.random()*probability)%probability==0) {
                    PowerUp newPU = new PowerUp(x, y);
                    powerUps.add(newPU);
                }
                media.playDestroyBrick();
            }
            if (type == -1) {
                type = -1;
            }
        }
        Update();
    }

    public void Update() {
        MainImage newImage = new MainImage();
        if(type==0){
            image=null;
        }
        if(type==1){
            image=newImage.getBrick1();
        }
        if(type==-1)
        {
            image=newImage.getBrick2();
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