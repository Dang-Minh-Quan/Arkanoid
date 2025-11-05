package Interface;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.text.Font;

import java.io.IOException;

public class SaveScoreController {

  @FXML
  private Pane SaveHighScore;
  @FXML
  private TextField nameInput;
  @FXML
  private Label ScoreLabel;

  private int finalScore;
  private Stage stage;
  private ScoreManager scoreManager = new ScoreManager();
  private boolean scoreSaved = false;

  private void loadAndApplyFont() {
    Font scoreFont = Font.loadFont(
        getClass().getResourceAsStream("/Interface/Font/Minecraftia-Regular.ttf"),
        20
    );

    Font inputFont = Font.loadFont(
        getClass().getResourceAsStream("/Interface/Font/Minecraftia-Regular.ttf"),
        16
    );

    if (scoreFont != null) {
      ScoreLabel.setFont(scoreFont);
      System.out.println("Thành công");
    } else {
      System.err.println("Cảnh báo: Không thể tải font ScoreLabel. Kiểm tra lại đường dẫn /Font/Minecraftia-Regular.ttf.");
    }

    if (inputFont != null) {
      nameInput.setFont(inputFont);
    } else {
      System.err.println("Cảnh báo: Không thể tải font TextField.");
    }
  }

  public void setFinalScore(int score, Stage stage) {
    this.finalScore = score;
    this.stage = stage;
    this.scoreSaved = false;

    loadAndApplyFont();
    ScoreLabel.setText("SCORE: " + String.valueOf(score));

    if (scoreManager.isTopScore(finalScore)) {
      SaveHighScore.setVisible(true);

      Platform.runLater(() -> {
        nameInput.requestFocus();
      });

      nameInput.setOnKeyPressed(event -> {
        if (event.getCode() == KeyCode.ENTER) {
          try {
            SaveScore(new ActionEvent(nameInput, null));
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
      });

    } else {
      SaveHighScore.setVisible(false);
    }
  }

  @FXML
  private void SaveScore(ActionEvent event) throws IOException {
    if (scoreSaved) return;

    String playerName = nameInput.getText().trim();
    if (playerName.isEmpty()) {
      playerName = "Player";
    }

    scoreManager.addScore(playerName, finalScore);
    scoreSaved = true;

    BackToScoreBoard();
  }

  @FXML
  protected void BackToScoreBoard() throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("/Interface/ScoreBoard.fxml"));
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.centerOnScreen();
    stage.show();
  }

  @FXML
  protected void BackToMenu() throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("/Interface/MainMenu.fxml"));
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.centerOnScreen();
    stage.show();
  }
}