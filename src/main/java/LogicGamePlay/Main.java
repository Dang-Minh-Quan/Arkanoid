//package LogicGamePlay;
//
//import javafx.animation.AnimationTimer;
//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.layout.Pane;
//import javafx.stage.Stage;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.concurrent.Executor;
//import java.util.concurrent.Executors;
//import java.util.concurrent.ScheduledExecutorService;
//import java.util.concurrent.TimeUnit;
//import java.util.concurrent.atomic.AtomicInteger;
//
//import static LogicGamePlay.Specifications.*;
//
//public class Main extends Application {
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//
//    @Override
//    public void start(Stage stage) throws IOException {
//        Pane pane = new Pane();
//
//        AtomicInteger Level = new AtomicInteger(0);
//        Ball ball = new Ball();
//        Paddle paddle= new Paddle();
//        Brick[][] brick = new Brick[(int)ROW][(int)COL];
//        MainImage IMAGE = new MainImage();
//        MainMedia media = new MainMedia();
//        ArrayList<PowerUp>powerUps=new ArrayList<>();
//        ArrayList<Ball>balls=new ArrayList<>();
//        IMAGE.LoadImage();
//        media.LoadMedia();
//
//        ScheduledExecutorService gameThread = Executors.newScheduledThreadPool(1);
//        gameThread.schedule(()-> {
//            media.playMusic();
//        },1, TimeUnit.SECONDS);
//
//        Update update = new Update();
//        Render render = new Render();
//
//        AnimationTimer mainGame = new AnimationTimer() {
//            long LastUpdate = 0;
//
//            @Override
//            public void handle(long now) {
//                if (now - LastUpdate >= 16_000_000) {
//                    update.updateGame(media,balls,ball,paddle, brick, Level,powerUps);
//                    render.renderGame(IMAGE,balls,ball,paddle, brick, pane,powerUps);
//                    LastUpdate = now;
//                }
//            }
//        };
//        Scene scene = new Scene(pane, WIDTH, HEIGHT+HEIGHTBar);
//        stage.setTitle("Hello!");
//        stage.setScene(scene);
//        stage.setResizable(false);
//        stage.show();
//        paddle.controllerPaddle(scene);
//        pane.requestFocus();
//        mainGame.start();
//    }
//}
