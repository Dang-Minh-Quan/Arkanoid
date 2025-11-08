package Interface;

import java.awt.Canvas;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SwitchScene {
    public static void fade(Stage stage, Parent root) {
        Scene scene = new Scene(root);
        FadeTransition fade = new FadeTransition(Duration.millis(700), root);
        fade.setFromValue(0);
        fade.setToValue(1);
        stage.setScene(scene);
        stage.centerOnScreen();
        fade.play();
    }
}
