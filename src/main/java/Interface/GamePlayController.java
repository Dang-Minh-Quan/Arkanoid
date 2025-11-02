package Interface;


import LogicGamePlay.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import javafx.animation.AnimationTimer;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.util.Duration;

import static LogicGamePlay.Specifications.*;

public class GamePlayController {

  @FXML
  private Pane GamePlay;

  @FXML
  private Pane gameLayer;

  @FXML
  private Pane WinGame;

  @FXML
  private Pane SaveHighScore;

  @FXML
  private Label ScoreLabel;

  @FXML
  private TextField nameInput;

  @FXML
  private Button ButtonPause;

  @FXML
  private Pane PauseMenu;

  @FXML
  private ImageView LoadingScene;

  @FXML
  private Button ButtonResume;

  @FXML
  private Button ButtonBack;


  private AnimationTimer mainGame;

  private Ball ball;
  private List<Ball> balls;
  private Paddle paddle;
  private Brick[][] brick;
  private Update update;
  private Render render;
  private MainImage IMAGE;
  private MainMedia media;
  private int FinalScore;
  private ScoreManager scoreManager = new ScoreManager();
  List<PowerUp> powerUps;


  public void start(Stage stage) throws IOException {
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

    ball = new Ball();
    balls = new ArrayList<>();
    powerUps = new ArrayList<>();
    paddle = new Paddle();
    brick = new Brick[ROW][COL];
    IMAGE = new MainImage();
    media = new MainMedia();
    IMAGE.LoadImage();
    media.LoadMedia();


    update = new Update(this);
    render = new Render();
    update.initializeLevel(ball, paddle, balls, brick);
    AtomicBoolean gameRestarted = new AtomicBoolean(true);
    System.out.println(numBrick);

    ScheduledExecutorService gameThread = Executors.newScheduledThreadPool(1);
    gameThread.schedule(()-> {
        media.playMusic();
        },1, TimeUnit.SECONDS);

    mainGame = new AnimationTimer() {
      long LastUpdate = 0;

      @Override
      public void handle(long now) {
        if (now - LastUpdate >= 16_000_000) {
          //System.out.println(ball.vx+" "+ball.vy);
          //System.out.println(numBrick);
          update.updateGame(media, balls, ball, paddle, brick, Level, gameRestarted, powerUps, render);
          //gameLayer.getChildren().clear();
          render.renderGame(gc, ball, paddle, brick);
          LastUpdate = now;
        }
      }
    };

    Platform.runLater(() -> {
      paddle.controllerPaddle(GamePlay.getScene(), gameRestarted);
      GamePlay.requestFocus();
      ButtonPause.toFront();

      PauseTransition loading = new PauseTransition(Duration.seconds(2));
      loading.setOnFinished(e -> {
        LoadingScene.setVisible(false);
        mainGame.start();
      });
      loading.play();
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

      WinCheck = false;
      GameOverCheck = false;

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
      Level.incrementAndGet();
      SaveGame.saveProgress();
      Stage stage = (Stage) GamePlay.getScene().getWindow();

      if (Level.get() > LevelMax) {
        FinalScore=score.get();

        System.out.println("Final Score: " + FinalScore);

        WinCheck = false;
        GameOverCheck = false;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Interface/WinGame.fxml"));
        Parent root = loader.load();

        SaveScoreController winGameController = loader.getController();
        if (winGameController != null) {
          winGameController.setFinalScore(FinalScore, stage);
        }

        SwitchScene.fade(stage, root);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();

        System.out.println("Congratulations! Game Completed. Loading WinGame Scene.");
        return;
      }

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

  @FXML
  protected void BackToMenu (ActionEvent event) throws IOException {
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    Parent root = FXMLLoader.load(getClass().getResource("/Interface/MainMenu.fxml"));
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.centerOnScreen();
    stage.show();

    System.out.println("Clicked Menu");
  }
}