package com.example.arkanoid;

import javafx.scene.image.Image;

public class MainImage {
    private static Image brick1;
    private static Image brick2;
    private static Image backgroud1;

    public Image getBrick2() {
        return brick2;
    }

    public Image getBrick1() {
        return brick1;
    }

    public Image getBackgroud1() {
        return backgroud1;
    }

    public void LoadImage() {
        brick1 = new Image(getClass().getResourceAsStream("brick1.png"), 60, 30, false, false);
        brick2 = new Image(getClass().getResourceAsStream("brick2.png"), 60, 30, false, false);
        backgroud1 = new Image(getClass().getResourceAsStream("background1.png"), 60, 30, false, false);
    }
}
