package com.example.arkanoid;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

import static com.example.arkanoid.Specifications.*;

public class Paddle extends Baseclass {
    private int spvx ;
    private int stvx ;

    public Paddle() {
        super(null, WIDTH / 2 - paddleWidthOriginal / 2, HEIGHT - 40, vxOriginal,paddleWidthOriginal,paddleHeightOriginal);
        spvx=spvxOriginal;
        stvx=0;
    }

    public void Update() {
        if (stvx > 0) {
            if (x < WIDTH -width) {
                x = x + spvx;
                stvx = stvx - spvx;
            }
        }
        if (stvx < 0) {
            if (x > 0) {
                x = x - spvx;
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
