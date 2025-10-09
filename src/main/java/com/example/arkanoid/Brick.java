package com.example.arkanoid;


import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import static com.example.arkanoid.Specifications.*;

public class Brick extends Baseclass {
    public Brick(int i,int j) {
        super(null, i*(WIDTH/COL), j*(HEIGHT/ROW), WIDTH/COL,HEIGHT/ROW);
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
        Rectangle brick = new Rectangle(60 * j, 30 * i, 60, 30);
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