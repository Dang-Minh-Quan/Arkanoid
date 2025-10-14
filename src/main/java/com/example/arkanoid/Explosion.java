package com.example.arkanoid;

import javafx.scene.image.Image;

public class Explosion extends AnimationClass {
    public Explosion(int x, int y) {
        super(MainImage.getExplosion(), x, y, 4, 4, 64, 64, 10);
    }
}