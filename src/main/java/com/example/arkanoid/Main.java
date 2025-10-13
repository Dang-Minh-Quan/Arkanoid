package com.example.arkanoid;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import static com.example.arkanoid.Specifications.*;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Pane pane = new Pane();
        Scene scene = new Scene(pane, WIDTH, HEIGHT);

        AtomicInteger Level = new AtomicInteger(0);
        Paddle paddle = new Paddle();
        Ball ball = new Ball();
        Brick[][] brick = new Brick[ROW][COL];
        MainImage image = new MainImage();
        image.LoadImage();

        Update update = new Update();
        Render render = new Render();

        pane.requestFocus();

        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case LEFT -> paddle.setMoveLeft(true);
                case RIGHT -> paddle.setMoveRight(true);
            }
        });

        scene.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case LEFT -> paddle.setMoveLeft(false);
                case RIGHT -> paddle.setMoveRight(false);
            }
        });

        AnimationTimer mainGame = new AnimationTimer() {
            @Override
            public void handle(long now) {
                double nextBallX = ball.getBall().getCenterX() + ball.vx;
                double nextBallY = ball.getBall().getCenterY() + ball.vy;
                ball.setBall(nextBallX, nextBallY);
                switch(ball.checkWallCollision()) {
                    case 1:
                        ball.vx = -ball.vx;
                        ball.vy = -ball.vy;
                        break;
                    case 2:
                        ball.vx = -ball.vx;
                        break;
                    case 3:
                        ball.vy = -ball.vy;
                        break;
                }
                switch (ball.checkPaddleCollision(paddle)) {
                    case 1:
                        ball.vy = -ball.vy;
                        break;
                    case 2: case 3:
                        ball.vx = -ball.vx;
                        break;
                }

                double nextPaddleX = paddle.getPaddle().getX();
                if (paddle.isMoveLeft()) {
                    nextPaddleX -= paddle.vx;
                }
                if (paddle.isMoveRight()) {
                    nextPaddleX += paddle.vx;
                }
                nextPaddleX = paddle.ClampPosition(nextPaddleX);
                paddle.setPaddle(nextPaddleX);

                update.updateGame(paddle, brick, Level);
                render.renderGame(paddle, ball, brick, pane, image);
            }
        };
        mainGame.start();
        stage.setScene(scene);
        stage.setTitle("DemoArkanoid");
        stage.show();
    }

    public static void main(String[] args) {

        launch(args);
    }
}
