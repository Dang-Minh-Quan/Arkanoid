package LogicGamePlay;

import javafx.scene.image.Image;

import static LogicGamePlay.Specifications.HEIGHTBar;
import static LogicGamePlay.Specifications.WIDTH;

public class MainImage {
    private static Image brick1;
    private static Image brick2;
    private static Image brick3;
    private static Image brick4;
    private static Image brickBroken;
    private static Image paddle;
    private static Image paddle1;
    private static Image ball;
    private static Image ball1;
    private static Image ballpower;
    private static Image background;
    private static Image explosion;
    private static Image bar;
    private static Image powerUp;
    private static Image bullet;

    public Image getBrick1() {

        return brick1;
    }

    public Image getBrick2() {

        return brick2;
    }

    public Image getBrick3() {

        return brick3;
    }


    public Image getBrick4() {

        return brick4;
    }

    public static Image getBrickBroken() {
        return brickBroken;
    }

    public Image getBackground() {

        return background;
    }

    public Image getBar() {
        return bar;
    }

    public Image getPaddle() {
        return paddle;
    }

    public Image getPaddle1() {
        return paddle1;
    }

    public Image getBall() {
        return ball;
    }

    public Image getBall1() {
        return ball1;
    }

    public static Image getBallpower() {
        return ballpower;
    }

    public static Image getExplosion() {
        return explosion;
    }

    public static Image getPowerup() {
        return powerUp;
    }

    public Image getBullet() {
        return bullet;
    }

    public void LoadImage() {
        background = new Image(getClass().getResourceAsStream("/Interface/Image/background.png"));
        brick1 = new Image(getClass().getResourceAsStream("/Interface/Image/brick1.png"),
                60, 30, false, false);
        brick2 = new Image(getClass().getResourceAsStream("/Interface/Image/brick2.png"),
                60, 30, false, false);
        brick3 = new Image(getClass().getResourceAsStream("/Interface/Image/brick3.png"),
                60, 30, false, false);
        brick4 = new Image(getClass().getResourceAsStream("/Interface/Image/brick4.png"),
                60, 30, false, false);
        brickBroken = new Image(getClass().getResourceAsStream("/Interface/Image/brickBroken.png"),
                60, 30, false, false);
        paddle = new Image(getClass().getResourceAsStream("/Interface/Image/paddle.png"),
                100, 30, false, false);
        paddle1 = new Image(getClass().getResourceAsStream("/Interface/Image/paddle1.png"),
                100, 30, false, false);
        ball = new Image(getClass().getResourceAsStream("/Interface/Image/ball.png"),
                30, 30, false, false);
        ball1 = new Image(getClass().getResourceAsStream("/Interface/Image/ball1.png"),
                30, 30, false, false);
        ballpower = new Image(getClass().getResourceAsStream("/Interface/Image/ballpower.png"),
                30, 30, false, false);
        explosion = new Image(getClass().getResourceAsStream("/Interface/Image/explosion.png"),
                256, 256, true, false);
        powerUp = new Image(getClass().getResourceAsStream("/Interface/Image/PowerUp.png"),
                128 * 8, 128 * 8, true, false);
        bar = new Image(getClass().getResourceAsStream("/Interface/Image/bar.png"),
                WIDTH, HEIGHTBar, false, false);
        bullet = new Image(getClass().getResourceAsStream("/Interface/Image/bullet.png"),
                10, 10, false, false);
    }
}
