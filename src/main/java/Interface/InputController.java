package Interface;

import Paddle.Paddle;
import javafx.scene.Scene;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class InputController {
    private final Scene scene;
    private final GamePlayController GameState;
    private boolean isPaused=false;

  /**
   * Nhận phím để điều khiển paddle và tạm dừng/tiếp tục trò chơi.
   * @param scene
   * @param newPaddle
   * @param gameRestarted
   * @param GameState
   */
    public InputController(Scene scene, AtomicReference<Paddle> newPaddle, AtomicBoolean gameRestarted,GamePlayController GameState) {
        this.scene = scene;
        this.GameState = GameState;

        scene.setOnKeyPressed(event -> {
            Paddle paddle = newPaddle.get();
            switch (event.getCode()) {
                case LEFT, A -> paddle.setMoveLeft(true);
                case RIGHT, D -> paddle.setMoveRight(true);
                case SPACE -> {
                    if (gameRestarted.get()) {
                        gameRestarted.set(false);
                    }
                }
                case P -> {
                  if(!isPaused) {
                    GameState.Pause();
                    isPaused = true;
                  } else {
                    GameState.Resume();
                    isPaused = false;
                  }
                }
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
