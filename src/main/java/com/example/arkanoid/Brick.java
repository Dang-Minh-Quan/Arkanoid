package com.example.arkanoid;


import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import static com.example.arkanoid.Specifications.*;

public class Brick extends Baseclass {
    private boolean cracked = false;
    private boolean destroyed = false;
    private MainImage imageLoad;
    public Brick(int i,int j) {
        super(null, j * 60, i * 30, 60, 30);
    }

    public void Update() {
        MainImage newImage = new MainImage();
        if(type==0 || destroyed){
            image=null;
            return;
        }
        switch (type) {
            case 1:
                image = imageLoad.getBrick1();
                break;
            case -1:
                image  = imageLoad.getBrick2();
                break;
            case 2:
                image = cracked ? imageLoad.getBrick3_2() : imageLoad.getBrick3_1();
                break;
        }
    }

    public Rectangle builderBrick(int i, int j) {
        Rectangle brick = new Rectangle( 60 * j, 30 * i, 60, 30);
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

    public boolean hit() {
        if (type == 0 || destroyed) {
            return false;
        }

        if (type == 1) {
            destroyed = true;
            type = 0;
            return true;
        }

        if (!cracked) {
            cracked = true;
            return false;
        } else {
            destroyed = true;
            type = 0;
            return true;
        }
    }

    public boolean isCracked() {
        return cracked;
    }

    public boolean isDestroyed() {
        return destroyed;
    }
}