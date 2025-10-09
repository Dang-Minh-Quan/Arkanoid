package com.example.arkanoid;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import static com.example.arkanoid.Specifications.*;

public class Render {
    public void renderGame(Paddle paddle, Brick[][] brick, Pane pane) {
        pane.getChildren().clear();
        renderBackGround(pane);
        renderBrick(brick, pane);
        renderPaddle(paddle, pane);
    }

    private void renderBackGround(Pane pane) {
        Rectangle backGround = new Rectangle(0, 0, WIDTH, HEIGHT);
        MainImage image = new MainImage();
        backGround.setFill(new ImagePattern(image.getBackgroud1()));
        pane.getChildren().add(backGround);
    }

    private void renderBrick(Brick[][] brick, Pane pane) {
        Pane newBrickPane = Paint.paintBrick(brick, COL, ROW);
        pane.getChildren().add(newBrickPane);
    }

    private void renderPaddle(Paddle paddle, Pane pane) {
        Rectangle paddleImage = new Rectangle(paddle.x - paddle.height / 2, paddle.y,
                paddle.width, paddle.height);
        paddleImage.setFill(Color.BLACK);
        paddleImage.setStroke(Color.BLUE);
        pane.getChildren().add(paddleImage);
    }
}
