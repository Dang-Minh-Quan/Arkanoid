package com.example.arkanoid;

import javafx.scene.layout.Pane;

public class Paint {
    public static Pane paintBrick(Brick[][] brick, int COL, int ROW) {
        Pane pane = new Pane();
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                pane.getChildren().add(brick[i][j].builderBrick(i, j));
            }
        }
        return pane;
    }
}
