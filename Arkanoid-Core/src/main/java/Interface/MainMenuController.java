package Interface;

import static LogicGamePlay.Specifications.HEIGHT;
import static LogicGamePlay.Specifications.HEIGHTBar;
import static LogicGamePlay.Specifications.WIDTH;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainMenuController {
  @FXML
  private Button ButtonPlay;
  @FXML
  private Button ButtonScore;
  @FXML
  private Button ButtonExit;
  @FXML
  private Button BackToMenu;

  @FXML
  protected void StartGame(ActionEvent event) throws IOException {
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

    System.out.println("Clicked Play");
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

}
