//package LogicGamePlay;
//
//import LogicGamePlay.Ball;
//import LogicGamePlay.Paddle;
//import javafx.animation.AnimationTimer;
//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.layout.Pane;
//import javafx.stage.Stage;
//
//import java.io.IOException;
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
//        IMAGE.LoadImage();
//
//        Update update = new Update(this);
//        Render render = new Render();
//
//        AnimationTimer mainGame = new AnimationTimer() {
//            long LastUpdate = 0;
//
//            @Override
//            public void handle(long now) {
//                if (now - LastUpdate >= 16_000_000) {
//                    update.updateGame(ball, paddle, brick, Level);
//                    render.renderGame(IMAGE, ball, paddle, brick, pane);
//                    LastUpdate = now;
//                }
//            }
//        };
//        Scene scene = new Scene(pane, WIDTH, HEIGHT);
//        stage.setTitle("Hello!");
//        stage.setScene(scene);
//        stage.setResizable(false);
//        stage.show();
//        pane.requestFocus();
//        mainGame.start();
//    }
//}
