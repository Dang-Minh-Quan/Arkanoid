package Interface;

import LogicGamePlay.HighScoreList;
import LogicGamePlay.MainMedia;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;

public class ScoreBoardController extends MainMenuController {

  @FXML
  private Button ButtonBack;

  @FXML
  private VBox ScoreBoard;

  private ScoreManager scoreManager = new ScoreManager();

  private static Font customFont = null;

  @FXML
  public void initialize() {
    if (customFont == null) {
      customFont = Font.loadFont(
          getClass().getResourceAsStream("/Interface/Font/Minecraftia-Regular.ttf"),
          15
      );
    }
    ScoreBoard.setSpacing(-7);
    displayScores();
    ButtonBack.toFront();
  }

  private void displayScores() {
    ScoreBoard.getChildren().clear();
    List<HighScoreList> scores = scoreManager.getHighScores();

    int rank = 1;
    for (HighScoreList Player : scores) {
      Label scoreLabel = new Label("Top" + rank + ": " + Player.getName() + " - " + Player.getScore());

      scoreLabel.setFont(customFont);

      ScoreBoard.getChildren().add(scoreLabel);
      rank++;
    }
  }

  @FXML
  protected void Reset(ActionEvent event) {
    media = MainMedia.getInstance();
    media.playPressButton();
    scoreManager.resetScores();
    displayScores(); // Cập nhật lại giao diện sau khi reset
    System.out.println("ScoreBoard Reset.");
  }

  @FXML
  @Override
  protected void BackToMenu(ActionEvent event) throws IOException {
    media = MainMedia.getInstance();
    media.playPressButton();
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    Parent root = FXMLLoader.load(getClass().getResource("/Interface/MainMenu.fxml"));
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.centerOnScreen();
    stage.show();
  }
}