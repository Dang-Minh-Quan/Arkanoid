package Interface;


import static LogicGamePlay.Specifications.COL;
import static LogicGamePlay.Specifications.ROW;

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


public class GamePlayController {

  @FXML
  private Pane GamePlay;

  @FXML
  private Pane gameLayer;

  @FXML
  private Pane WinGame;

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

    GamePlay.getChildren().clear();
    gameLayer = new Pane();
    GamePlay.getChildren().addAll(gameLayer, ButtonBack);
    //ButtonBack.toFront();

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

    mainGame = new AnimationTimer() {
      long LastUpdate = 0;

      @Override
      public void handle(long now) {
        if (now - LastUpdate >= 16_000_000) {
          update.updateGame(ball,paddle, brick, Level,gameRestarted);
          gameLayer.getChildren().clear();
          render.renderGame(IMAGE,ball,paddle, brick, gameLayer);
          LastUpdate = now;
        }
      }
    };

    Platform.runLater(() -> {
      paddle.controllerPaddle(GamePlay.getScene(),gameRestarted);
      GamePlay.requestFocus();
      ButtonBack.toFront();
      mainGame.start();
    });
  }

  @FXML
  private Button ButtonBack;

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
