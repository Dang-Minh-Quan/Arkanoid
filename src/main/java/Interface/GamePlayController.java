package Interface;


import Image.MainImage;
import LogicGamePlay.*;
import Ball.*;
import Brick.*;
import Media.MainMedia;
import Paddle.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;

import PowerUp.PowerUp;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.util.Duration;

import static LogicGamePlay.Specifications.*;
import static LogicGamePlay.SaveGame.saveProgress;

public class GamePlayController extends MainMenuController {

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
    private InputController input;
    private ScheduledExecutorService gameThread;
    private GameObject gameObject;
    private List<Ball> balls;
    private List<Bullet> bullets;
    private AtomicReference<Paddle> paddle;
    private Brick[][] brick;
    private Update update;
    private Render render;
    private MainImage image;
    private MainMedia media;
    private int FinalScore;
    //private ScoreManager scoreManager = new ScoreManager();
    List<PowerUp> powerUps;

  /**
   * Vòng lặp game chính.
   * @param stage
   * @throws IOException
   */
  public void start(Stage stage) throws IOException {
        image = MainImage.getInstance();
        media = MainMedia.getInstance();

        if (mainGame != null) {
            mainGame.stop();
            mainGame = null;
        }

        gameLayer.toBack();
        ButtonPause.toFront();
        PauseMenu.toFront();
        media.ViewBackGrounnd();
        Canvas canvas = new Canvas(WIDTH, HEIGHT + HEIGHTBar);
        gameLayer.getChildren().addAll(media.getBackGroundView(), canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gameObject = new GameObject();
        powerUps = new ArrayList<>();
        paddle = new AtomicReference<>(gameObject.createPaddle(WIDTH / 2 - paddleWidthOriginal / 2, HEIGHT - paddleHeightOriginal, "normal"));
        Ball ball = gameObject.createBall(paddle.get().x + paddleWidthOriginal / 2, HEIGHT - paddleHeightOriginal - 1, "normal");
        balls = new ArrayList<>();
        balls.add(ball);
        brick = new Brick[ROW][COL];
        bullets = new ArrayList<>();

        render = new Render();
        update = new Update(this, render);
        update.initializeLevel(paddle, balls, brick);
        AtomicBoolean gameRestarted = new AtomicBoolean(true);

        media.playGamePlayMusic();

        mainGame = new AnimationTimer() {
            long LastUpdate = 0;

          /**
           * Vận hành trò chơi.
           * @param now
           */
            @Override
            public void handle(long now) {
                if (now - LastUpdate >= 16_000_000) {
                    update.updateGame(media, balls, paddle, brick, Level, gameRestarted, powerUps, bullets, render);
                    render.renderGame(gc, balls, paddle, brick, powerUps, bullets);
                    LastUpdate = now;
                }
            }
        };

        Platform.runLater(() -> {
            input = new InputController(GamePlay.getScene(), paddle, gameRestarted,this);
            GamePlay.requestFocus();
            ButtonPause.toFront();
            mainGame.start();

            if (LoadingScene != null) {
                FadeTransition fade = new FadeTransition(Duration.millis(700), LoadingScene);
                fade.setFromValue(1.0);
                fade.setToValue(0.0);
                fade.setOnFinished(e -> LoadingScene.setVisible(false));
                fade.play();
            }
        });
    }

  /**
   * Tạm dừng trò chơi.
   */
  @FXML
    protected void Pause() {
      media.playPressButton();
      if (mainGame != null) {
          mainGame.stop();
      }
      PauseMenu.setVisible(true);
      ButtonPause.setVisible(false);
      System.out.println("Game Paused");
    }

  /**
   * Tiếp tục trò chơi.
   */
  @FXML
    protected void Resume() {
      media.playPressButton();
      if (mainGame != null) {
          mainGame.start();
      }
      PauseMenu.setVisible(false);
      ButtonPause.setVisible(true);

      GamePlay.requestFocus();
      System.out.println("Game Resumed");
    }

  /**
   * Chơi lại màn chơi hiện tại.
   * @param event
   * @throws IOException
   */
  @FXML
    protected void Restart(ActionEvent event) throws IOException  {
      super.PlayAgain(event);
      PauseMenu.setVisible(false);
      ButtonPause.setVisible(true);

      GamePlay.requestFocus();
      System.out.println("Game Restarted");
    }

    public static boolean GameOverCheck = false;

  /**
   * Thua cuộc.
   */
    public void GameOver() {
      media.playGameOver();
        try {
            if (heartCount.get() > 0 || GameOverCheck) return;
            GameOverCheck = true;

            if (mainGame != null) {
                mainGame.stop();
                mainGame = null;
            }

            Stage stage = (Stage) GamePlay.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Interface/GameOver.fxml"));
            Parent root = loader.load();
            SwitchScene.fade(stage, root);

            //GameOverCheck = false;
            System.out.println("You Lose!");
        } catch (IOException e) {
            e.printStackTrace();
            GameOverCheck = false;
        }
    }

    boolean WinCheck = false;
    static boolean WinGameCheck = false;

  /**
   * Thắng màn chơi/phá đảo trò chơi.
   */
    public void Win() {
      media.playWin();
        try {
            if (WinCheck) return;
            WinCheck = true;

            if (mainGame != null) {
                mainGame.stop();
                mainGame = null;
            }

            Stage stage = (Stage) GamePlay.getScene().getWindow();
            score.set(score.get() + heartCount.get() * 20);
            if (Level.get() == LevelMax) {
                FinalScore = score.get();
                reset();
                saveProgress();
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

                System.out.println("Congratulations! Game Completed. Loading WinGame Scene.");
                return;
            }

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Interface/WinLevel.fxml"));
            Parent root = loader.load();
            SwitchScene.fade(stage, root);

            System.out.println("Win!");
        } catch (IOException e) {
            e.printStackTrace();
            WinCheck = false;
        }
    }

  /**
   * Trở về sảnh.
   * @param event
   * @throws IOException
   */
  @FXML
    protected void BackToMenu(ActionEvent event) throws IOException {
        super.BackToMenu(event);
    }
}