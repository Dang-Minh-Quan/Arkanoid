package LogicGamePlay;

import javafx.scene.image.Image;

public class MainImage {

    private static MainImage instance;

    public static synchronized MainImage getInstance() {
        if (instance == null) {
            instance = new MainImage();
        }
        return instance;
    }

    private Image brick1, brick2, brick3, brick4, brickBroken;
    private Image paddle, paddle1, ball, ball1, background, explosion, bar, powerUp;

    private MainImage() {
        loadImages();
    }

    private void loadImages() {
        background = new Image(getClass().getResourceAsStream("/Interface/Image/background.png"));
        brick1 = new Image(getClass().getResourceAsStream("/Interface/Image/brick1.png"), 60, 30, false, false);
        brick2 = new Image(getClass().getResourceAsStream("/Interface/Image/brick2.png"), 60, 30, false, false);
        brick3 = new Image(getClass().getResourceAsStream("/Interface/Image/brick3.png"), 60, 30, false, false);
        brick4 = new Image(getClass().getResourceAsStream("/Interface/Image/brick4.png"), 60, 30, false, false);
        brickBroken = new Image(getClass().getResourceAsStream("/Interface/Image/brickBroken.png"), 60, 30, false, false);

        paddle = new Image(getClass().getResourceAsStream("/Interface/Image/paddle.png"), 100, 30, false, false);
        paddle1 = new Image(getClass().getResourceAsStream("/Interface/Image/paddle1.png"), 100, 30, false, false);
        ball = new Image(getClass().getResourceAsStream("/Interface/Image/ball.png"), 30, 30, false, false);
        ball1 = new Image(getClass().getResourceAsStream("/Interface/Image/ball1.png"), 30, 30, false, false);

        explosion = new Image(getClass().getResourceAsStream("/Interface/Image/explosion.png"), 256, 256, true, false);
        powerUp = new Image(getClass().getResourceAsStream("/Interface/Image/PowerUp.png"), 128 * 8, 128 * 8, true, false);
        bar = new Image(getClass().getResourceAsStream("/Interface/Image/bar.png"), Specifications.WIDTH, Specifications.HEIGHTBar, false, false);
    }

    public Image getBrick1() { return brick1; }
    public Image getBrick2() { return brick2; }
    public Image getBrick3() { return brick3; }
    public Image getBrick4() { return brick4; }
    public Image getBrickBroken() { return brickBroken; }
    public Image getPaddle() { return paddle; }
    public Image getPaddle1() { return paddle1; }
    public Image getBall() { return ball; }
    public Image getBall1() { return ball1; }
    public Image getBackground() { return background; }
    public Image getExplosion() { return explosion; }
    public Image getPowerUp() { return powerUp; }
    public Image getBar() { return bar; }
}
