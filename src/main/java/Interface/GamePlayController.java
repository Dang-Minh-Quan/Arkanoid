package Interface;


import LogicGamePlay.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
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

  private ScheduledExecutorService gameThread;
    private List<Ball> balls;
  private Paddle paddle;
  private Brick[][] brick;
  private Update update;
  private Render render;
  private MainImage IMAGE;
  private MainMedia media;
  List<PowerUp> powerUps;
    private final Object Lock = new Object();

  public void start(Stage stage) throws IOException {
      IMAGE = new MainImage();
      media = new MainMedia();
      IMAGE.LoadImage();
      media.LoadMedia();;
    if (mainGame != null) {
      mainGame.stop();
      mainGame = null;
      System.out.println(Specifications.Level.get());
    }

    gameLayer.toBack();
    ButtonPause.toFront();
    PauseMenu.toFront();

    Canvas canvas = new Canvas(WIDTH, HEIGHT + HEIGHTBar);
    gameLayer.getChildren().add(canvas);
    GraphicsContext gc = canvas.getGraphicsContext2D();

    Ball ball = new Ball();
    balls = new ArrayList<>();
    balls.add(ball);
    powerUps = new ArrayList<>();
    paddle = new Paddle();
    brick = new Brick[ROW][COL];

    update = new Update(this);
    render = new Render();
    update.initializeLevel( paddle, balls, brick);
    AtomicBoolean gameRestarted = new AtomicBoolean(true);
    System.out.println(numBrick);

    gameThread = Executors.newSingleThreadScheduledExecutor();
    gameThread.schedule(()-> {
        media.playMusic();
        },1, TimeUnit.SECONDS);

    mainGame = new AnimationTimer() {
      long LastUpdate = 0;
      @Override
      public void handle(long now) {
        if (now - LastUpdate >= 16_000_000) {
              System.out.println(numBrick);
              update.updateGame(media, balls, paddle, brick, Level, gameRestarted, powerUps, render);
              render.renderGame(gc, balls, paddle, brick, powerUps);
              LastUpdate = now;
        }
      }
    };

    Platform.runLater(() -> {
      paddle.controllerPaddle(GamePlay.getScene(), gameRestarted);
      GamePlay.requestFocus();
      ButtonPause.toFront();
      mainGame.start();
    });
  }

  @FXML
  protected void Pause(ActionEvent event) {
    if (mainGame != null) {
      mainGame.stop();
    }
    PauseMenu.setVisible(true);
    ButtonPause.setVisible(false);
    System.out.println("Game Paused");
  }

  @FXML
  protected void Resume(ActionEvent event) {
    if (mainGame != null) {
      mainGame.start();
    }
    PauseMenu.setVisible(false);
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

  public static boolean GameOverCheck = false;

  public void GameOver() {
    try {
      if (heartCount.get() > 0 || GameOverCheck) return;
      GameOverCheck = true;

      if (mainGame != null) {
        mainGame.stop();
        mainGame = null;
      }

      Stage stage = (Stage) GamePlay.getScene().getWindow();
      Parent root = FXMLLoader.load(getClass().getResource("/Interface/GameOver.fxml"));
      SwitchScene.fade(stage, root);
      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.centerOnScreen();
      stage.show();

      //GameOverCheck = false;
      System.out.println("You Lose!");
    } catch (IOException e) {
      e.printStackTrace();
      GameOverCheck = false;
    }
  }

  boolean WinCheck = false;

  public void Win() {
    try {
      if (WinCheck) return;
      WinCheck = true;

      if (mainGame != null) {
        mainGame.stop();
        mainGame = null;
      }

      Stage stage = (Stage) GamePlay.getScene().getWindow();
      Parent root = FXMLLoader.load(getClass().getResource("/Interface/WinLevel.fxml"));
      SwitchScene.fade(stage, root);
      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.centerOnScreen();
      stage.show();

      System.out.println("Win!");
    } catch (IOException e) {
      e.printStackTrace();
      WinCheck = false;
    }
  }
}