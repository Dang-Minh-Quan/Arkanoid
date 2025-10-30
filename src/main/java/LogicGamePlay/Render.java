package LogicGamePlay;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static LogicGamePlay.Specifications.*;

public class Render {
    private long lastTime = 0;
    private final ArrayList<Explosion> explosions = new ArrayList<>();
    private final MainImage image = new MainImage();

    public void addExplosion(int x, int y) {
        explosions.add(new Explosion(x, y));
    }
    public void addPowerUp(int x, int y, List<PowerUp> powerUps) {
    powerUps.add(new PowerUp(MainImage.getPowerup(), x, y));
}

    private void renderPowerUp(GraphicsContext gc, List<PowerUp> powerUps) {
        for (PowerUp p : powerUps) {
            if(!p.checkActivate) {
                p.update();
                p.render(gc);
            }
        }
    }

    public void renderGame(GraphicsContext gc,List<Ball> balls,  Ball ball, Paddle paddle, Brick[][] brick,List<PowerUp>powerUps) {
        gc.clearRect(0,0,WIDTH, HEIGHT);
        renderBackGround(gc);
        renderBrick(gc,brick);
        renderExplosions(gc);
        renderBall(gc, ball);
        renderBalls(gc, balls);
        renderPowerUp(gc,powerUps);
        renderPaddle(gc, paddle);
        renderBackBar(gc);
    }
    private void renderBackGround(GraphicsContext gc) {
        Image background = image.getBackground();
        gc.drawImage(background, 0, 0, WIDTH, HEIGHT);
    }

    private void renderBackBar(GraphicsContext gc) {
        Image bar = image.getBar();
        gc.drawImage(bar,0,HEIGHT, WIDTH, HEIGHTBar);
    }

    private void renderBrick(GraphicsContext gc, Brick[][] brick) {
        for(int i = 0; i < ROW; i++) {
            for(int j = 0; j < COL; j++) {
                Brick b = brick[i][j];
                if(b == null) {
                    continue;
                }

              //  b.Update();
                if(b.type == 0 || b.image == null) {
                    continue;
                }

                gc.drawImage(b.image, b.x, b.y, b.width, b.height);
            }
        }
    }

    private void renderPaddle(GraphicsContext gc, Paddle paddle) {
        Image paddleImage = image.getPaddle();
        gc.drawImage(paddleImage, paddle.x, paddle.y, paddle.width, paddle.height);
    }

    private void renderBalls(GraphicsContext gc,List<Ball> balls){
        for (Ball b : balls) {
            Image ballImange = image.getBall();
            b.RenderTail(gc);
            gc.drawImage(ballImange, b.x - b.width *3/2, b.y-b.width*3/2,b.width * 4, b.width * 4);
        }
    }

    private void renderBall(GraphicsContext gc, Ball ball){
        Image ballImange = image.getBall();
        ball.RenderTail(gc);
        gc.drawImage(ballImange, ball.x - ball.width *3/2, ball.y-ball.width*3/2,ball.width * 4, ball.width * 4);
    }

  private void renderExplosions(GraphicsContext gc) {
        long currentTime = System.nanoTime();
        if (lastTime == 0) lastTime = currentTime;
        double deltaTime = (currentTime - lastTime) / 1_000_000_000.0;
        lastTime = currentTime;

        Iterator<Explosion> iterator = explosions.iterator();
        while (iterator.hasNext()) {
            Explosion explosion = iterator.next();
            explosion.update(deltaTime);
            if (explosion.toBeRemoved) {
                iterator.remove();
                continue;
            }
            explosion.draw(gc);
        }
    }
}
