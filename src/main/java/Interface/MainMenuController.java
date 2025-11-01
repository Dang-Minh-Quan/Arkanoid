package Interface;

import static LogicGamePlay.SaveGame.hasSavedGame;
import static LogicGamePlay.SaveGame.loadProgress;
import static LogicGamePlay.SaveGame.saveProgress;
import static LogicGamePlay.Specifications.HEIGHT;
import static LogicGamePlay.Specifications.HEIGHTBar;
import static LogicGamePlay.Specifications.WIDTH;

import LogicGamePlay.SaveGame;
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
import static LogicGamePlay.Specifications.*;

public class MainMenuController {
  @FXML
  private Button ButtonPlay;
  @FXML
  private Button ButtonNewGame;
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
  protected void StartGame(ActionEvent event) throws IOException {
    ButtonPlay.setVisible(false);

    SelectionMenu.setVisible(true);
    SelectionMenu.toFront();

    if (hasSavedGame()) {
      ButtonContinueGame.setDisable(false);
    } else {
      ButtonContinueGame.setDisable(true);
    }

    System.out.println("Clicked Play");
  }

  @FXML
  protected void NewGame(ActionEvent event) throws IOException {
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
    //saveProgress();
    System.out.println("Clicked Exit");
    System.exit(0);
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
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    loadNextLevel(stage);
  }

  public void loadCurrentLevel(Stage stage) throws IOException {
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
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    loadCurrentLevel(stage);
  }

}
