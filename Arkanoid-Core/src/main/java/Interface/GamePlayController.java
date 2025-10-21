package Interface;


import LogicGamePlay.Ball;
import LogicGamePlay.Brick;
import LogicGamePlay.MainImage;
import LogicGamePlay.Paddle;
import LogicGamePlay.Render;
import LogicGamePlay.Update;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import static LogicGamePlay.Specifications.*;
import static LogicGamePlay.Specifications.HEIGHT;

public class GamePlayController {

  @FXML
  private Pane GamePlay;

  @FXML
  private Pane gameLayer;

  @FXML
  private Pane WinGame;

  @FXML
  private Button ButtonPause;

  @FXML
  private Pane PauseMenu;

  @FXML
  private Button ButtonResume;

  @FXML
  private Button ButtonBack;


  private AnimationTimer mainGame;

  private Ball ball;
  private Paddle paddle;
  private Brick[][] brick;
  private AtomicInteger Level;
  private Update update;
  private Render render;
  private MainImage IMAGE;

  public void start(Stage stage) throws IOException {

    if (mainGame != null) {
      mainGame.stop();
      mainGame = null;
    }
    PauseMenu.setVisible(false);

    //gameLayer = new Pane();
    //GamePlay.getChildren().add(gameLayer);
    gameLayer.toBack(); // gameLayer nằm dưới các nút UI

    //PauseMenu.toFront();
    ButtonPause.toFront(); // đảm bảo nút Pause nằm trên
    PauseMenu.toFront();
//    GamePlay.getChildren().clear();
//    gameLayer = new Pane();
//    GamePlay.getChildren().addAll(gameLayer, ButtonPause);
//    ButtonBack.toFront();

    Canvas canvas = new Canvas(WIDTH, HEIGHT+ HEIGHTBar);
    gameLayer.getChildren().add(canvas);
    // canvas.toFront();
    GraphicsContext gc = canvas.getGraphicsContext2D();

    Level = new AtomicInteger(0);
    ball = new Ball();
    paddle = new Paddle();
    brick = new Brick[(int)ROW][(int)COL];
    IMAGE = new MainImage();
    IMAGE.LoadImage();

    update = new Update(this);
    render = new Render();
    update.initializeLevel(ball, brick, Level);
    AtomicBoolean gameRestarted = new AtomicBoolean(true);
    AtomicBoolean gameIsRunning = new AtomicBoolean(true);

    mainGame = new AnimationTimer() {
      long LastUpdate = 0;

      @Override
      public void handle(long now) {
        if (now - LastUpdate >= 16_000_000) {
          update.updateGame(ball,paddle, brick, Level,gameRestarted,gameIsRunning,render);
          //gameLayer.getChildren().clear();
          render.renderGame(gc, ball, paddle, brick);
          // render.renderGame(IMAGE,ball,paddle, brick, gameLayer);
          LastUpdate = now;
        }
      }
    };

    Platform.runLater(() -> {
      paddle.controllerPaddle(GamePlay.getScene(),gameRestarted);
      GamePlay.requestFocus();
      ButtonPause.toFront();
      mainGame.start();
    });
  }

  @FXML
  protected void Pause(ActionEvent event) {
    if (mainGame != null) {
      mainGame.stop(); // tạm dừng game
    }
    PauseMenu.setVisible(true);// hiện menu pause
    ButtonPause.setVisible(false);
    System.out.println("Game Paused");
  }

  @FXML
  protected void Resume(ActionEvent event) {
    if (mainGame != null) {
      mainGame.start(); // tiếp tục game
    }
    PauseMenu.setVisible(false); // ẩn menu pause
    ButtonPause.setVisible(true);

    GamePlay.requestFocus();
    System.out.println("Game Resumed");
  }

  @FXML
  protected void Back(ActionEvent event) throws IOException {
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

    if (mainGame != null) {
      mainGame.stop();
      mainGame = null;
    }

    Parent root = FXMLLoader.load(getClass().getResource("/Interface/MainMenu.fxml"));
    Scene scene = new Scene(root);

    stage.setScene(scene);
    stage.centerOnScreen();
    stage.show();

    System.out.println("Clicked Back");
  }

  boolean WinCheck = false;

  public void Win() {
    try {
      if (WinCheck) return;
      WinCheck = true;

      Stage stage = (Stage) GamePlay.getScene().getWindow();
      Parent root = FXMLLoader.load(getClass().getResource("/Interface/WinGame.fxml"));
      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.centerOnScreen();
      stage.show();

      System.out.println("Win!");
    } catch (IOException e) {
      e.printStackTrace();
      WinCheck =false;
    }
  }
}
