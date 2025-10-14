package com.example.arkanoid;

import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.util.ArrayList;
import java.util.Iterator;

import static com.example.arkanoid.Specifications.*;

public class Render {
    private final ArrayList<Explosion> explosions = new ArrayList<>();

    public void addExplosion(int x, int y) {
        explosions.add(new Explosion(x, y));
    }

    private final ArrayList<Crack> cracks = new ArrayList<>();

    public void addCrack(int x, int y) {
        cracks.add(new Crack(x, y));
    }


    public void renderGame(GraphicsContext gc, Paddle paddle, Brick[][] brick) {
        gc.clearRect(0,0,WIDTH, HEIGHT);
        renderBackGround(gc);
        renderBrick(gc, brick);
        renderExplosions(gc);
        renderCracks(gc);
        renderPaddle(gc, paddle);
    }

    private void renderBackGround(GraphicsContext gc) {
        MainImage image = new MainImage();
        Image background = image.getBackground1();
        gc.drawImage(background, 0, 0, WIDTH, HEIGHT);
    }

 private void renderBrick(GraphicsContext gc, Brick[][] brick) {
     for (int i = 0; i < ROW; i++) {
         for (int j = 0; j < COL; j++) {
             Brick b = brick[i][j];
             if (b == null) continue;

             b.Update();
             if (b.type == 0 || b.image == null) continue;

             gc.drawImage(b.image, b.x, b.y, b.width, b.height);
         }
     }
 }

    private void renderPaddle(GraphicsContext gc, Paddle paddle) {
        gc.setFill(Color.BLACK);
        gc.setStroke(Color.BLUE);

        gc.fillRect(paddle.x - paddle.height / 2, paddle.y,
                paddle.width, paddle.height);
        gc.strokeRect(paddle.x - paddle.height / 2, paddle.y,
                paddle.width, paddle.height);
    }

    private void renderExplosions(GraphicsContext gc) {
        Iterator<Explosion> iterator = explosions.iterator();
        while (iterator.hasNext()) {
            Explosion explosion = iterator.next();
            explosion.Update();
            if (explosion.toBeRemoved) {
                iterator.remove();
                continue;
            }
            explosion.draw(gc);
        }
    }

    private void renderCracks(GraphicsContext gc) {
        Iterator<Crack> iterator = cracks.iterator();
        while (iterator.hasNext()) {
            Crack crack = iterator.next();
            crack.Update();
            if (crack.toBeRemoved) {
                iterator.remove();
                continue;
            }
            crack.draw(gc);
        }
    }

}