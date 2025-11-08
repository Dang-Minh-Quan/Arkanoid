package Interface;

import static LogicGamePlay.SaveGame.hasSavedGame;
import static LogicGamePlay.SaveGame.loadProgress;
import static LogicGamePlay.SaveGame.saveProgress;
import static LogicGamePlay.Specifications.HEIGHT;
import static LogicGamePlay.Specifications.HEIGHTBar;
import static LogicGamePlay.Specifications.WIDTH;

import LogicGamePlay.MainMedia;
import LogicGamePlay.Specifications;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import static LogicGamePlay.Specifications.*;

public class MainMenuController {
  @FXML
  private Button ButtonPlay;
  @FXML
  private Button ButtonNewGame;
  @FXML
  private ImageView BlurButton;
  @FXML
  private Button ButtonContinueGame;
  @FXML
  private Pane SelectionMenu;
  @FXML
  private Button ButtonScore;
  @FXML
  private Button ButtonExit;
  @FXML
  private Button BackToMenu;
  @FXML
  private Button ButtonNextLevel;
  @FXML
  private Button ButtonPlayAgain;
  @FXML
  private Button ButtonMute;
  @FXML
  private Button ButtonUnmute;

  public MainMedia media;
  //public boolean Muted;

  @FXML
  protected void StartGame(ActionEvent event) throws IOException {
      media = MainMedia.getInstance();
    media.playPressButton();
    ButtonPlay.setVisible(false);
    SelectionMenu.setVisible(true);
    SelectionMenu.toFront();

    if (hasSavedGame()) {
      ButtonContinueGame.setDisable(false);
      BlurButton.setVisible(false);
    } else {
      ButtonContinueGame.setDisable(true);
      BlurButton.setVisible(true);
    }

    System.out.println("Clicked Play");
  }

  @FXML
  protected void NewGame(ActionEvent event) throws IOException {
    media = MainMedia.getInstance();
    media.playPressButton();
    reset();
    saveProgress();
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

    FXMLLoader loader = new FXMLLoader(getClass().getResource("/Interface/GamePlay.fxml"));
    Parent root = loader.load();

    Scene scene = new Scene(root, WIDTH, HEIGHT +HEIGHTBar);
    stage.setScene(scene);
    stage.centerOnScreen();
    stage.setResizable(false);
    stage.show();

    GamePlayController controller = loader.getController();
    controller.start(stage);

    System.out.println("Clicked New Game");
  }

  @FXML
  protected void ContinueGame(ActionEvent event) throws IOException {
      media = MainMedia.getInstance();
    media.playPressButton();
    loadProgress();
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

    FXMLLoader loader = new FXMLLoader(getClass().getResource("/Interface/GamePlay.fxml"));
    Parent root = loader.load();

    Scene scene = new Scene(root, WIDTH, HEIGHT +HEIGHTBar);
    stage.setScene(scene);
    stage.centerOnScreen();
    stage.setResizable(false);
    stage.show();

    GamePlayController controller = loader.getController();
    controller.start(stage);

    System.out.println("Clicked Continue, loading current level");
  }

  @FXML
  protected void OpenScoreboard(ActionEvent event) throws IOException {
    media = MainMedia.getInstance();
    media.playPressButton();
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

    Parent root = FXMLLoader.load(getClass().getResource("/Interface/Scoreboard.fxml"));
    Scene scene = new Scene(root);

    stage.setScene(scene);
    stage.centerOnScreen();
    stage.show();

    System.out.println("Clicked Scoreboard");
  }

  @FXML
  protected void ExitGame(ActionEvent event) {
      media = MainMedia.getInstance();
    media.playPressButton();
    System.out.println("Clicked Exit");
    System.exit(0);
  }

  public void loadNextLevel(Stage stage) throws IOException {
    Level.incrementAndGet();
    saveProgress();
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/Interface/GamePlay.fxml"));
    Parent root = loader.load();

    GamePlayController gameController = loader.getController();

    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.centerOnScreen();
    stage.show();

    gameController.start(stage);
  }

  @FXML
  protected void NextLevel(ActionEvent event) throws IOException {
      media = MainMedia.getInstance();
    media.playPressButton();
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    loadNextLevel(stage);
  }

  public void loadCurrentLevel(Stage stage) throws IOException {
    loadProgress();
    winLevel = false;
    numBrick = 0;
    heartCount.set(3);
    GamePlayController.GameOverCheck = false;
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/Interface/GamePlay.fxml"));
    Parent root = loader.load();

    GamePlayController gameController = loader.getController();

    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.centerOnScreen();
    stage.show();

    gameController.start(stage);
    System.out.println("Reloading Level " + Specifications.Level.get());
  }

  @FXML
  protected void PlayAgain(ActionEvent event) throws IOException {
      media = MainMedia.getInstance();
    media.playPressButton();
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    loadCurrentLevel(stage);
  }

  @FXML
  public void initialize() {
    try {
      if (MainMedia.isMuted()) {
        ButtonUnmute.setVisible(false);
        ButtonUnmute.setDisable(true);
        ButtonMute.setVisible(true);
        ButtonMute.setDisable(false);
      } else {
        ButtonUnmute.setVisible(true);
        ButtonUnmute.setDisable(false);
        ButtonMute.setVisible(false);
        ButtonMute.setDisable(true);
      }
    } catch (NullPointerException e) {
      System.err.println("Warning: Buttons not initialized correctly in FXML load.");
    }
  }

  /**
   * Tắt âm thanh game.
   */
  @FXML
  public void Mute() {
      ButtonUnmute.setVisible(false);
      ButtonUnmute.setDisable(true);
      ButtonMute.setVisible(true);
      ButtonMute.setDisable(false);
      MainMedia.getInstance().muteAllMedia();
  }

  /**
   * Bật âm thanh game.
   */
  @FXML
  public void Unmute() {
      ButtonUnmute.setVisible(true);
      ButtonUnmute.setDisable(false);
      ButtonMute.setVisible(false);
      ButtonMute.setDisable(true);
      MainMedia.getInstance().unmuteAllMedia();
  }

  @FXML
  protected void BackToMenu (ActionEvent event) throws IOException {
    media = MainMedia.getInstance();
    if(!GamePlayController.WinGameCheck) {
      media.playMenuMusic();
    }
    GamePlayController.WinGameCheck=false;
    media.playPressButton();
    GamePlayController.GameOverCheck = false;
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    Parent root = FXMLLoader.load(getClass().getResource("/Interface/MainMenu.fxml"));
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.centerOnScreen();
    stage.show();

    System.out.println(" Clicked Menu");
  }
}
