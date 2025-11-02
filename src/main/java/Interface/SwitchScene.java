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

  public static void slideFromRight(Stage stage, Parent root) {
    Scene scene = new Scene(root);
    TranslateTransition slide = new TranslateTransition(Duration.millis(700), root);
    slide.setFromX(scene.getWidth());
    slide.setToX(0);
    stage.setScene(scene);
    stage.centerOnScreen();
    slide.play();
  }

  public static void slideFromLeft(Stage stage, Parent root) {
    Scene scene = new Scene(root);
    TranslateTransition slide = new TranslateTransition(Duration.millis(700), root);
    slide.setFromX(-scene.getWidth());
    slide.setToX(0);
    stage.setScene(scene);
    stage.centerOnScreen();
    slide.play();
  }

  public static void zoomIn(Stage stage, Parent root) {
    Scene scene = new Scene(root);
    ScaleTransition zoom = new ScaleTransition(Duration.millis(700), root);
    zoom.setFromX(0.8);
    zoom.setFromY(0.8);
    zoom.setToX(1);
    zoom.setToY(1);
    stage.setScene(scene);
    stage.centerOnScreen();
    zoom.play();
  }

  public static void zoomOut(Stage stage, Parent root) {
    Scene scene = new Scene(root);
    ScaleTransition zoom = new ScaleTransition(Duration.millis(700), root);
    zoom.setFromX(1.2);
    zoom.setFromY(1.2);
    zoom.setToX(1);
    zoom.setToY(1);
    stage.setScene(scene);
    stage.centerOnScreen();
    zoom.play();
  }

  public static void combo(Stage stage, Parent root) {
    Scene scene = new Scene(root);

    TranslateTransition slide = new TranslateTransition(Duration.millis(700), root);
    slide.setFromX(scene.getWidth());
    slide.setToX(0);

    FadeTransition fade = new FadeTransition(Duration.millis(700), root);
    fade.setFromValue(0);
    fade.setToValue(1);

    ScaleTransition zoom = new ScaleTransition(Duration.millis(700), root);
    zoom.setFromX(0.9);
    zoom.setFromY(0.9);
    zoom.setToX(1);
    zoom.setToY(1);

    ParallelTransition combo = new ParallelTransition(slide, fade, zoom);
    stage.setScene(scene);
    stage.centerOnScreen();
    combo.play();
  }

//  public static void showGame(Canvas canvas) {
//    container.setOpacity(0);
//    container.setTranslateY(200);
//
//    FadeTransition fadeIn = new FadeTransition(Duration.millis(800), container);
//    fadeIn.setFromValue(0);
//    fadeIn.setToValue(1);
//
//    TranslateTransition slideUp = new TranslateTransition(Duration.millis(800), container);
//    slideUp.setFromY(200);
//    slideUp.setToY(0);
//
//    ParallelTransition showGame = new ParallelTransition(fadeIn, slideUp);
//    showGame.play();
//  }
}
