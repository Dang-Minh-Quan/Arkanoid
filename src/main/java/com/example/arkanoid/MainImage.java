package com.example.arkanoid;

import javafx.scene.image.Image;

public class MainImage {
    private static Image brick1;
    private static Image brick2;
    private static Image paddle;
    private static Image ball;
    private static Image background1;

    public Image getBrick2() {

        return brick2;
    }

    public Image getBrick1() {

        return brick1;
    }

    public Image getBackground1() {

        return background1;
    }

    public Image getPaddle() {
        return paddle;
    }

    public Image getBall() {
        return ball;
    }

    public void LoadImage() {
        paddle = new Image(getClass().getResourceAsStream("paddle.png"), 100, 30, false, false);
        ball = new Image(getClass().getResourceAsStream("ball.png"), 30, 30, false, false);
        brick1 = new Image(getClass().getResourceAsStream("brick1.png"), 60, 30, false, false);
        brick2 = new Image(getClass().getResourceAsStream("brick2.png"), 60, 30, false, false);
        background1 = new Image(getClass().getResourceAsStream("background.jpg"), 60, 30, false, false);
    }
}
