package Interface;

import Media.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        MainMedia media = MainMedia.getInstance();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        media.playMenuMusic();
        Parent root = FXMLLoader.load(getClass().getResource("/Interface/MainMenu.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Arkanoid");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
