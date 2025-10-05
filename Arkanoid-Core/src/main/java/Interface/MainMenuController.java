package org.example.arkanoidcore;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainMenuController {
  @FXML
  private Button ButtonPlay;
  private Button ButtonScore;
  private Button ButtonExit;

  @FXML
  private void StartGame(ActionEvent event) {
    System.out.println("Clicked Play");
  }

  @FXML
  private void OpenScoreboard(ActionEvent event) {
    System.out.println("Clicked Scoreboard");
  }

  @FXML
  private void ExitGame(ActionEvent event) {
    System.out.println("Clicked Exit");
    System.exit(0);
  }
}
