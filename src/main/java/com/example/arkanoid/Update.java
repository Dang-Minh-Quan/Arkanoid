package com.example.arkanoid;

import java.util.concurrent.atomic.AtomicInteger;

import static com.example.arkanoid.Specifications.*;

public class Update {
    private static void updatePaddle(Paddle paddle) {
        paddle.Update();
    }

    public void updateGame(Paddle paddle, Brick[][] brick, AtomicInteger Level) {
        updateBrick(Level, brick);
        updatePaddle(paddle);
    }

    private void updateBrick(AtomicInteger Level, Brick[][] brick) {
        System.out.println(Level);
        if (numBrick <= 0) {
            Level.getAndIncrement();
            if (Level.get() <= LevelMax) {
                builderLevel(brick, Level);
            } else {
                Level.set(0);
                //WIN
            }
        }

        // bổ sung hàm ballHits
        if (ballHits(ball, b)) {
            boolean broken = b.hit();

            if (broken) {
                render.addExplosion(b.x, b.y);
                numBrick--;
            } else if (b.isCracked()) {
                render.addCrack(b.x, b.y);
            }
        }
}





    private void builderLevel(Brick[][] brick, AtomicInteger Level) {
        Map map = new Map();
        if (Level.get() > LevelMax) {
            checkPlay = false;
        } else {
            int[][] a = map.builderMap(Level.get());
            for (int i = 0; i < ROW; i++) {
                for (int j = 0; j < COL; j++) {
                    brick[i][j] = new Brick(i,j);
                    brick[i][j].type=a[i][j];
                    if (brick[i][j].type > 0) {
                        numBrick = numBrick + brick[i][j].type;
                    }
                    brick[i][j].Update();
                }
            }
        }
    }
}
