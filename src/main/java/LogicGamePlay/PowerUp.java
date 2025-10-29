/*package LogicGamePlay;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import static LogicGamePlay.Specifications.*;

public class PowerUp {
    public double x, y;
    public double width = 30, height = 30;
    public double vy = 2;
    public boolean active = true;
    public Image image;

    public PowerUp(double x, double y, Image image) {
        this.x = x;
        this.y = y;
        this.image = image;
    }

    public void update() {
        if (!active) return;
        y += vy;

        if (y > HEIGHT) {
            active = false;
        }
    }

    public void render(GraphicsContext gc) {
        if (!active || image == null) return;
        gc.drawImage(image, x, y, width, height);
    }
}
*/
package LogicGamePlay;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class PowerUp {
    private double x, y;
    private double width = 32, height = 32;
    private double vy = 2;
    private boolean active = true;

    private Image image;
    private int frameCols = 4;
    private int frameRows = 4;
    private int totalFrames = frameCols * frameRows;
    private int currentFrame = 0;
    private int frameDelay = 5;  // tốc độ xoay
    private int delayCounter = 0;

    public PowerUp(Image spriteSheet, double x, double y) {
        this.image = spriteSheet;
        this.x = x;
        this.y = y;
    }

    public void update() {
        if (!active) return;

        // Cập nhật vị trí rơi
        y += vy;
        if (y > 720) active = false;

            delayCounter++;
        if (delayCounter >= frameDelay) {
            delayCounter = 0;
            currentFrame = (currentFrame + 1) % totalFrames;
        }
    }

    public void render(GraphicsContext gc) {
        if (!active || image == null) return;

        int frameWidth = (int) image.getWidth() / frameCols;
        int frameHeight = (int) image.getHeight() / frameRows;

        int col = currentFrame % frameCols;
        int row = currentFrame / frameCols;

        gc.drawImage(
                image,
                col * frameWidth, row * frameHeight, frameWidth, frameHeight,
                x, y, width, height
        );
    }

    public boolean isActive() {
        return active;
    }
}
