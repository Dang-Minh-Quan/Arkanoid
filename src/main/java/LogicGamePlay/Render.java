package LogicGamePlay;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.geometry.VPos;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static LogicGamePlay.Specifications.*;

public class Render {
    private long lastTime = 0;
    private final List<Explosion> explosions = new ArrayList<>();
    private final MainImage image = MainImage.getInstance();
    private Font pixelFont;

    public void addExplosion(int x, int y) {
        explosions.add(new Explosion(x, y));
    }

    public void addPowerUp(int x, int y, List<PowerUp> powerUps) {
        powerUps.add(new PowerUp(image.getPowerUp(), x, y));
    }

    private void renderPowerUp(GraphicsContext gc, List<PowerUp> powerUps) {
        for (PowerUp p : powerUps) {
            if (!p.checkActivate) {
                p.update();
                p.render(gc);
            }
        }
        powerUps.removeIf(p -> !p.isActive());
    }

    public void renderGame(GraphicsContext gc,List<Ball> balls, Paddle paddle,
                           Brick[][] brick,List<PowerUp>powerUps,List<Bullet> bullets) {
        gc.clearRect(0,0,WIDTH, HEIGHT);
        //renderBackGround(gc);
        renderBrick(gc,brick);
        renderExplosions(gc);
        renderBalls(gc, balls);
        renderPowerUp(gc, powerUps);
        renderPaddle(gc, paddle);
        renderBackBar(gc);
        renderBullet(gc, bullets);
        renderHUD(gc);
        if(blind==true){
            Image background = image.getBackground();
            gc.drawImage(background, 0, 0, WIDTH, ROW*HEIGHTBrick);
        }
    }

    private void renderBullet(GraphicsContext gc,List<Bullet> bullets){
        for (Bullet b : bullets) {
            Image bulletImange = image.getBullet();
            gc.drawImage(bulletImange, b.x - b.width, b.y - b.width, b.width * 2, b.width * 2);
        }
    }

    private void renderBackGround(GraphicsContext gc) {
        Image background = image.getBackground();
        gc.drawImage(background, 0, 0, WIDTH, HEIGHT);
    }

    private void renderBackBar(GraphicsContext gc) {
        Image bar = image.getBar();
        gc.drawImage(bar, 0, HEIGHT, WIDTH, HEIGHTBar);
    }

    private void renderBrick(GraphicsContext gc, Brick[][] brick) {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                Brick b = brick[i][j];
                if (b == null) {
                    continue;
                }

                if (b.type == 0 || b.image == null) {
                    continue;
                }

                gc.drawImage(b.image, b.x, b.y, b.width, b.height);
            }
        }
    }

    private void renderPaddle(GraphicsContext gc, Paddle paddle) {
        Image img;
        switch (paddle.type) {
            case 1:
                img = image.getPaddle();
                break;
            case 2:
                img = image.getPaddle1();
                break;   // paddle gun
            default:
                img = image.getPaddle();
                break;
        }

        gc.drawImage(img, paddle.x, paddle.y, paddle.width, paddle.height);
    }

    private void renderBalls(GraphicsContext gc, List<Ball> balls) {
        for (Ball b : balls) {
            Image img;
            switch (b.type) {
                case 1:
                    img = image.getBall1();
                    break;
                case 2:
                    img = image.getBallpower();
                    break;
                default:
                    img = image.getBall();
                    break;
            }

            b.RenderTail(gc);
            gc.drawImage(img, b.x - b.width, b.y - b.width, b.width * 2, b.width * 2);
        }
    }

    private void renderExplosions(GraphicsContext gc) {
        long currentTime = System.nanoTime();
        if (lastTime == 0) lastTime = currentTime;
        double deltaTime = (currentTime - lastTime) / 1_000_000_000.0;
        lastTime = currentTime;

        Iterator<Explosion> iterator = explosions.iterator();
        while (iterator.hasNext()) {
            Explosion explosion = iterator.next();
            explosion.Update();
            if (explosion.currentFrame >= explosion.frameCols * explosion.frameRows - 1) {
                iterator.remove();
                continue;
            }
            explosion.draw(gc);
        }
    }

    private void renderHUD(GraphicsContext gc) {
        gc.setFill(Color.rgb(60, 30, 10, 0.9));
        pixelFont = Font.loadFont(
                getClass().getResourceAsStream("/Interface/Font/Minecraftia-Regular.ttf"),
                30
        );
        gc.setFont(pixelFont);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);

        int scoreValue = score.get();
        int livesValue = heartCount.get();
        int levelValue = Level.get();

        gc.fillText(String.valueOf(scoreValue), 90, HEIGHT + 85);
        gc.fillText(String.valueOf(livesValue), 210, HEIGHT + 85);
        gc.fillText(String.valueOf(levelValue), 330, HEIGHT + 85);

    }
}
