package LogicGamePlay;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class AnimationClass extends BaseClass {
    protected int frameCols;
    protected int frameRows;
    protected int totalFrames;
    protected int currentFrame;
    protected int frameDelay;  // tốc độ xoay
    protected int delayCounter;

    public AnimationClass(Image image, final int x, final int y, int vx, int vy,
                          final int width, final int height, int frameCols,
                          int frameRows, int frameDelay) {
        super(image, x, y, vx, vy, width, height);
        this.frameCols = frameCols;
        this.frameRows = frameRows;
        this.frameDelay = frameDelay;
        this.totalFrames = frameCols * frameRows;
        this.currentFrame = 0;
    }

    @Override
    public void Update() {
        delayCounter++;
        if (delayCounter >= frameDelay) {
            delayCounter = 0;
            currentFrame = (currentFrame + 1) % totalFrames;
        }
    }

    public void draw(GraphicsContext gc) {
        int frameWidth = (int) image.getWidth() / frameCols;
        int frameHeight = (int) image.getHeight() / frameRows;

        int col = currentFrame % frameCols;
        int row = currentFrame / frameCols;

        gc.drawImage(
                image,
                col * frameWidth, row * frameHeight, frameWidth, frameHeight,
                x, y, width * 2, height * 2
        );
    }
}
