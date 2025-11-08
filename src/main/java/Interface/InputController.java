package Interface;

import Paddle.Paddle;
import javafx.scene.Scene;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class InputController {
    private final Scene scene;

    public InputController(Scene scene, AtomicReference<Paddle> newPaddle, AtomicBoolean gameRestarted) {
        this.scene = scene;

        scene.setOnKeyPressed(event -> {
            Paddle paddle = newPaddle.get();
            switch (event.getCode()) {
                case LEFT, A -> paddle.setMoveLeft(true);
                case RIGHT, D -> paddle.setMoveRight(true);
                case SPACE -> gameRestarted.set(false);
            }
        });

        scene.setOnKeyReleased(event -> {
            Paddle paddle =  newPaddle.get();
            switch (event.getCode()) {
                case LEFT, A -> paddle.setMoveLeft(false);
                case RIGHT, D -> paddle.setMoveRight(false);
            }
        });
    }
}
