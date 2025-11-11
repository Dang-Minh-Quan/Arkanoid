package Interface;

import javafx.animation.FadeTransition;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SwitchScene {

  /**
   * hiệu ứng mờ dần.
   * @param stage
   * @param newRoot
   */
  public static void Fade(Stage stage, Parent newRoot) {

        Parent oldRoot = stage.getScene().getRoot();

        FadeTransition fadeOut = new FadeTransition(Duration.millis(150), oldRoot);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);

        fadeOut.setOnFinished(event -> {
          Scene newScene = new Scene(newRoot);

          stage.setScene(newScene);
          stage.centerOnScreen();

          FadeTransition fadeIn = new FadeTransition(Duration.millis(150), newRoot);
          fadeIn.setFromValue(0.0);
          fadeIn.setToValue(1.0);
          fadeIn.play();

        });

        fadeOut.play();
  }
}
