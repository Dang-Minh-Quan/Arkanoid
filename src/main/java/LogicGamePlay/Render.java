package LogicGamePlay;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.util.ArrayList;
import java.util.Iterator;

import static LogicGamePlay.Specifications.*;

public class Render {
    private long lastTime = 0;
    private final ArrayList<Explosion> explosions = new ArrayList<>();
    private final ArrayList<PowerUp> powerUps = new ArrayList<>();
    private final MainImage image = new MainImage();

    public void addExplosion(int x, int y) {
        explosions.add(new Explosion(x, y));
    }

public void addPowerUp(double x, double y) {
    powerUps.add(new PowerUp(MainImage.getPowerup(), x, y));
}

    private void renderPowerUp(GraphicsContext gc) {
        for (PowerUp p : powerUps) {
            p.update();
            p.render(gc);
        }
        powerUps.removeIf(p -> !p.isActive());
    }

    public void renderGame(GraphicsContext gc, Ball ball, Paddle paddle, Brick[][] brick) {
        gc.clearRect(0,0,WIDTH, HEIGHT);
        renderBackGround(gc);
        renderBrick(gc,brick);
        renderExplosions(gc);
        ball.RenderTail(gc);
        renderBall(gc, ball);
        renderPowerUp(gc);
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

    private void renderBall(GraphicsContext gc, Ball ball){
        Image ballImange = image.getBall();
        gc.drawImage(ballImange, ball.x - ball.width, ball.y-ball.width,ball.width * 2, ball.width * 2);
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
