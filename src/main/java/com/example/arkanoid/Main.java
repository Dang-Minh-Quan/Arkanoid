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

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        Pane pane = new Pane();

        AtomicInteger Level = new AtomicInteger(0);
        Paddle paddle= new Paddle();
        Brick[][] brick = new Brick[ROW][COL];
        MainImage IMAGE = new MainImage();
        IMAGE.LoadImage();

        Update update = new Update();
        Render render = new Render();

        AnimationTimer mainGame = new AnimationTimer() {
            long LastUpdate = 0;

            @Override
            public void handle(long now) {
                if (now - LastUpdate >= 16_666_666) {
                    update.updateGame(paddle, brick, Level);
                    render.renderGame(paddle, brick, pane);
                    LastUpdate = now;
                }
            }
        };
        Scene scene = new Scene(pane, WIDTH, HEIGHT);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        paddle.controllerPaddle(scene);
        pane.requestFocus();
        mainGame.start();
    }
}
