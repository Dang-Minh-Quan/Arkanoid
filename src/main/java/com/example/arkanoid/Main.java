package com.example.arkanoid;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

import static com.example.arkanoid.Specifications.*;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        Pane pane = new Pane();

        AtomicInteger Level = new AtomicInteger(0);
        Ball ball = new Ball();
        Paddle paddle= new Paddle();
        Brick[][] brick = new Brick[(int)ROW][(int)COL];
        MainImage IMAGE = new MainImage();
        ArrayList<PowerUp>powerUps=new ArrayList<>();
        ArrayList<Ball>balls=new ArrayList<>();
        IMAGE.LoadImage();

        Update update = new Update();
        Render render = new Render();

        AnimationTimer mainGame = new AnimationTimer() {
            long LastUpdate = 0;

            @Override
            public void handle(long now) {
                if (now - LastUpdate >= 160_000_000) {
                    update.updateGame(balls,ball,paddle, brick, Level,powerUps);
                    render.renderGame(IMAGE,balls,ball,paddle, brick, pane,powerUps);
                    LastUpdate = now;
                }
            }
        };
        Scene scene = new Scene(pane, WIDTH, HEIGHT+HEIGHTBar);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        paddle.controllerPaddle(scene);
        pane.requestFocus();
        mainGame.start();
    }
}
