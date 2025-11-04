package Interface;

import LogicGamePlay.HighScoreList;
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

public class ScoreBoardController {

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
          getClass().getResourceAsStream("/Font/Minecraftia-Regular.ttf"),
          30
      );
      if (customFont == null) {
        System.err.println("Cảnh báo: Không thể tải font Minecraftia-Regular.ttf. Vẫn sử dụng font mặc định.");
      }
    }

    displayScores();
    ButtonBack.toFront();
  }

  private void displayScores() {
    ScoreBoard.getChildren().clear();
    List<HighScoreList> scores = scoreManager.getHighScores();

    if (scores.isEmpty()) {
      Label emptyLabel = new Label("Empty.");
      if (customFont != null) {
        emptyLabel.setFont(customFont);
      }
      ScoreBoard.getChildren().add(emptyLabel);
      return;
    }

    int rank = 1;
    for (HighScoreList Player : scores) {
      Label scoreLabel = new Label("Top" + rank + ": " + Player.getName() + " - Score: " + Player.getScore());

      if (customFont != null) {
        scoreLabel.setFont(customFont);
      } else {
        scoreLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #3b2a1a;");
      }

      ScoreBoard.getChildren().add(scoreLabel);
      rank++;
    }
  }

  @FXML
  protected void Reset(ActionEvent event) {
    scoreManager.resetScores();
    displayScores(); // Cập nhật lại giao diện sau khi reset
    System.out.println("ScoreBoard Reset.");
  }

  @FXML
  protected void Back(ActionEvent event) throws IOException {
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

    Parent root = FXMLLoader.load(getClass().getResource("/Interface/MainMenu.fxml"));
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.centerOnScreen();
    stage.show();

    System.out.println("Clicked Back to Main Menu.");
  }
}