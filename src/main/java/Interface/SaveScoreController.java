package Interface;

import Media.*;
import LogicGamePlay.ScoreManager;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.text.Font;

import java.io.IOException;

public class SaveScoreController extends MainMenuController {

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

  /**
   * Áp dụng phông chữ.
   */
  private void loadAndApplyFont() {
    Font scoreFont = Font.loadFont(
        getClass().getResourceAsStream("/Interface/Font/Minecraftia-Regular.ttf"),
        20
    );

    Font inputFont = Font.loadFont(
        getClass().getResourceAsStream("/Interface/Font/Minecraftia-Regular.ttf"),
        16
    );
  }

  /**
   * Lưu điểm của người chơi và kiểm tra điểm cao,
   * nếu có, cho người chơi nhập tên.
   * @param score
   * @param stage
   */
  public void setFinalScore(int score, Stage stage) {
    this.finalScore = score;
    this.stage = stage;
    this.scoreSaved = false;

    loadAndApplyFont();
    ScoreLabel.setText("YOUR SCORE: " + String.valueOf(score));

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

  /**
   * Thực hiện nhận tên được lưu và thêm vào danh sách điểm cao.
   * @param event
   * @throws IOException
   */
  @FXML
  private void SaveScore(ActionEvent event) throws IOException {
    if (scoreSaved) return;

    String playerName = nameInput.getText().trim();
    if (playerName.isEmpty()) {
      playerName = "Player";
    } else if(playerName.length()>9) {
      playerName = playerName.substring(0,9) + "...";
    }

    scoreManager.addScore(playerName, finalScore);
    scoreSaved = true;

    BackToScoreBoard(event);
  }

  /**
   * Trở về bảng xếp hạng.
   * @param event
   * @throws IOException
   */
  @FXML
  protected void BackToScoreBoard(ActionEvent event) throws IOException {
    super.OpenScoreboard(event);
  }

  /**
   * Trở về sảnh.
   * @param event
   * @throws IOException
   */
  @FXML
  protected void BackToMenu (ActionEvent event) throws IOException {
    super.BackToMenu(event);
  }
}