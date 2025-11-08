package LogicGamePlay;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import Image.*;


import static LogicGamePlay.Specifications.*;

public class Explosion extends AnimationClass{
    public boolean toBeRemoved = false;

    private static final Image explosionImage = MainImage.getInstance().getExplosion();


    public Explosion(int x, int y) {
        super(explosionImage, x, y-WIDTHBrick/2,0,speedPU,
                (WIDTHBrick+HEIGHTBrick)/2,
                (WIDTHBrick+HEIGHTBrick)/2,
                4,4,5);
    }

    public void Update() {
        super.Update();
        if (currentFrame >= totalFrames - 1) {
            toBeRemoved = true;
        }
    }

    public void draw(GraphicsContext gc) {
        if (toBeRemoved) return;
        super.draw(gc);
    }
}