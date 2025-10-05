package Interface;

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
  protected void StartGame(ActionEvent event) throws IOException {
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

    Parent root = FXMLLoader.load(getClass().getResource("/Interface/GamePlay.fxml"));
    Scene scene = new Scene(root);

    stage.setScene(scene);
    stage.show();

    System.out.println("Clicked Play");
  }

  @FXML
  protected void OpenScoreboard(ActionEvent event) throws IOException {
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

    Parent root = FXMLLoader.load(getClass().getResource("/Interface/Scoreboard.fxml"));
    Scene scene = new Scene(root);

    stage.setScene(scene);
    stage.show();

    System.out.println("Clicked Scoreboard");
  }

  @FXML
  protected void ExitGame(ActionEvent event) {
    System.out.println("Clicked Exit");
    System.exit(0);
  }

}
