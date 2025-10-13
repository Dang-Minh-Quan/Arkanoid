package com.example.arkanoid;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import static com.example.arkanoid.Specifications.*;

public class Render {
    public void renderGame(Paddle paddle, Ball ball, Brick[][] brick, Pane pane, MainImage image) {
        pane.getChildren().clear();
        renderBackGround(pane, image);
        renderBrick(brick, pane, image);
        renderPaddle(paddle, pane, image);
        renderBall(ball, pane, image);
    }

    private void renderBackGround(Pane pane, MainImage image) {
        Rectangle backGround = new Rectangle(0, 0, WIDTH, HEIGHT);
        backGround.setFill(new ImagePattern(image.getBackground1(), 0, 0, 1, 1, true));
        pane.getChildren().add(backGround);
    }

    private void renderBrick(Brick[][] brick, Pane pane, MainImage image) {
        Pane newBrickPane = Paint.paintBrick(brick, COL, ROW);
        pane.getChildren().add(newBrickPane);
    }

    private void renderPaddle(Paddle paddle, Pane pane, MainImage image) {
//        Rectangle paddleImage = new Rectangle(paddle.x - paddle.height / 2, paddle.y,
//                paddle.width, paddle.height);
//        paddleImage.setFill(Color.BLACK);
//        paddleImage.setStroke(Color.BLUE);
//        pane.getChildren().add(paddleImage);
        paddle.getPaddle().setFill(new ImagePattern(image.getPaddle()));
        pane.getChildren().add(paddle.getPaddle());
    }

    private  void renderBall(Ball ball, Pane pane, MainImage image) {
        ball.getBall().setFill(new ImagePattern(image.getBall(), 0, 0, 1, 1, true));
        pane.getChildren().add(ball.getBall());
    }
}
