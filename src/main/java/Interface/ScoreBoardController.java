package Interface;

import Score.HighScoreList;
import Media.*;
//import LogicGamePlay.MainMedia;
import Score.ScoreManager;
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

  /**
   * Áp dụng phông chữ và khởi tạo khoảng cách giữa các dòng.
   */
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

  /**
   * Hiển thị bảng xếp hạng.
   */
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

  /**
   * Xóa bảng xếp hạng.
   * @param event
   */
  @FXML
  protected void Reset(ActionEvent event) {
    media = MainMedia.getInstance();
    media.playPressButton();
    scoreManager.resetScores();
    displayScores();
    System.out.println("ScoreBoard Reset.");
  }

  /**
   * Trở về sảnh.
   * @param event
   * @throws IOException
   */
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