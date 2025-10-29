package LogicGamePlay;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Rectangle;

import static LogicGamePlay.Specifications.*;

public class Paddle extends BaseClass {
    private int spvx ;
    private int stvx ;
    private Rectangle HitBoxPaddle;

    public Paddle() {
        super( WIDTH / 2 , HEIGHT - 40, vxOriginal,paddleWidthOriginal,paddleHeightOriginal);
        HitBoxPaddle = new Rectangle(x,y,width,height);
        spvx=spvxOriginal;
        stvx=0;
    }

    public Rectangle getHitBoxPaddle() {
        return HitBoxPaddle;
    }

    public void Update() {
        if (stvx > 0) {
            if (x < WIDTH -width) {
                x = x + spvx;
                HitBoxPaddle.setX(x);
                stvx = stvx - spvx;
            }
        }
        if (stvx < 0) {
            if (x > 0) {
                x = x - spvx;
                HitBoxPaddle.setX(x);
                stvx = stvx + spvx;
            }
        }
    }

    public void controllerPaddle(Scene scene) {
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.LEFT) {
                if (stvx - vx > -vx * 2) {
                    if (stvx > 0) {
                        stvx = 0;
                    }
                    stvx = stvx - vx;
                }
            }
            if (e.getCode() == KeyCode.RIGHT) {
                if (stvx + vx < vx * 2) {
                    if (stvx < 0) {
                        stvx = 0;
                    }
                    stvx = stvx + vx;
                }
            }
        });
    }
}
