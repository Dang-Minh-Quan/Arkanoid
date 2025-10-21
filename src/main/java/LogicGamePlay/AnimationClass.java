package LogicGamePlay;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class AnimationClass extends Baseclass {
    protected int frameCols;
    protected int frameRows;
    protected int frameWidth;
    protected int frameHeight;
    protected int countX = 0;
    protected int countY = 0;
    protected int totalFrames;
    protected int currentFrame;
    protected int frameDelay;
    protected int delayCounter = 0;
    public boolean toBeRemoved = false;

    public AnimationClass(Image image, int x, int y, int frameCols, int frameRows,
                          int frameWidth, int frameHeight, int frameDelay) {
        super(image, x, y, frameWidth, frameHeight);
        this.frameCols = frameCols;
        this.frameRows = frameRows;
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        this.frameDelay = frameDelay;
        this.totalFrames = frameCols * frameRows;
        this.currentFrame = 0;
    }

    @Override
    public void Update() {
        delayCounter++;
        if (delayCounter < frameDelay) return;
        delayCounter = 0;

        currentFrame++;
        countX = currentFrame % frameCols;
        countY = currentFrame / frameCols;

        if (currentFrame >= totalFrames) {
            toBeRemoved = true;
        }
    }

    public void draw(GraphicsContext gc) {
        if (image != null && !toBeRemoved) {
            gc.drawImage(image,
                    countX * frameWidth, countY * frameHeight, frameWidth, frameHeight,
                    x, y, frameWidth, frameHeight);
        }
    }
}
