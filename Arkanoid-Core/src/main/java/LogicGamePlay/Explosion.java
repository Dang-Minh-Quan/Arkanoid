package LogicGamePlay;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Explosion {
    private static final double FRAME_WIDTH = 64;
    private static final double FRAME_HEIGHT = 64;
    private static final int COLUMNS = 4;
    private static final int ROWS = 4;
    private static final double FRAME_DURATION = 0.08;

    private double x, y;
    private double elapsedTime = 0;
    private int currentFrame = 0;
    public boolean toBeRemoved = false;

    private static final Image explosionImage =
            new Image(Explosion.class.getResourceAsStream("/Interface/Image1/explosion.png"),256, 256, true, false);

    public Explosion(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void update(double deltaTime) {
        elapsedTime += deltaTime;
        if (elapsedTime >= FRAME_DURATION) {
            currentFrame++;
            elapsedTime = 0;
            if (currentFrame >= ROWS * COLUMNS) {
                toBeRemoved = true;
            }
        }
    }

    public void draw(GraphicsContext gc) {
        if (toBeRemoved) return;

        int col = currentFrame % COLUMNS;
        int row = currentFrame / COLUMNS;

        gc.drawImage(
                explosionImage,
                col * FRAME_WIDTH, row * FRAME_HEIGHT, FRAME_WIDTH, FRAME_HEIGHT,
                x, y, FRAME_WIDTH, FRAME_HEIGHT
        );
    }
}
