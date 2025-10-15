package com.example.arkanoid;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

import static com.example.arkanoid.Specifications.*;

public class Render {
    public void renderGame(ArrayList<Ball>balls,Ball ball, Paddle paddle, Brick[][] brick, Pane pane, ArrayList<PowerUp> powerUps) {
        pane.getChildren().clear();
        renderBackGround(pane);
        renderBrick(brick, pane);
        renderPaddle(paddle, pane);
        renderBall(ball,pane);
        renderBalls(balls,pane);
        renderPowerUp(powerUps,pane);
    }

    private void renderPowerUp(ArrayList<PowerUp> powerUps, Pane pane) {
        for (int i=0;i<powerUps.size();i++) {
            Rectangle powerUpImage = new Rectangle(powerUps.get(i).x, powerUps.get(i).y,
                    powerUps.get(i).width, powerUps.get(i).height);
            powerUpImage.setFill(Color.BLACK);
            powerUpImage.setStroke(Color.BLUE);
            pane.getChildren().add(powerUpImage);
        }
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
        Rectangle paddleImage = new Rectangle(paddle.x , paddle.y,
                paddle.width, paddle.height);
        paddleImage.setFill(Color.BLACK);
        paddleImage.setStroke(Color.BLUE);
        pane.getChildren().add(paddleImage);
    }

    private void renderBall(Ball ball, Pane pane){
        pane.getChildren().addAll(ball.getBall());
    }

    private void renderBalls(ArrayList<Ball>balls, Pane pane) {
        for (int i = 0; i < balls.size(); i++) {
            pane.getChildren().addAll(balls.get(i).getBall());
        }
    }
}
