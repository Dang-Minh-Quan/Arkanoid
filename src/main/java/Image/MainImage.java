package Image;


import LogicGamePlay.Specifications;
import javafx.scene.image.Image;

public class MainImage {

    private static MainImage instance;

    public static synchronized MainImage getInstance() {
        if (instance == null) {
            instance = new MainImage();
        }
        return instance;
    }

    private Image normalBrick, unbreakableBrick, strongBrick, explosiveBrick, brokenBrick, blindBrick;
    private Image normalPaddle, longPaddle, gunPaddle, normalBall, infinityBall, explosiveBall, bullet,
            background, explosion, bar, powerUp;

    private MainImage() {
        loadImages();
    }

    private void loadImages() {
        background = new Image(getClass().getResourceAsStream("/Interface/Image/background.png"));
        normalBrick= new Image(getClass().getResourceAsStream("/Interface/Image/Brick/normalBrick.png"),
                60, 30, false, false);
        unbreakableBrick = new Image(getClass().getResourceAsStream("/Interface/Image/Brick/unbreakableBrick.png"),
                60, 30, false, false);
        strongBrick = new Image(getClass().getResourceAsStream("/Interface/Image/Brick/strongBrick.png"),
                60, 30, false, false);
        explosiveBrick = new Image(getClass().getResourceAsStream("/Interface/Image/Brick/explosionBrick.png"),
                60, 30, false, false);
        brokenBrick = new Image(getClass().getResourceAsStream("/Interface/Image/Brick/brokenBrick.png"),
                60, 30, false, false);
        blindBrick = new Image(getClass().getResourceAsStream("/Interface/Image/Brick/blindBrick.png"),
                60, 30, false, false);
        normalPaddle = new Image(getClass().getResourceAsStream("/Interface/Image/Paddle/normalPaddle.png"),
                100, 30, false, false);
        longPaddle = new Image(getClass().getResourceAsStream("/Interface/Image/Paddle/longPaddle.png"),
                100, 30, false, false);
        gunPaddle = new Image(getClass().getResourceAsStream("/Interface/Image/Paddle/gunPaddle.png"),
                100, 30, false, false);
        normalBall = new Image(getClass().getResourceAsStream("/Interface/Image/Ball/normalBall.png"),
                30, 30, false, false);
        infinityBall = new Image(getClass().getResourceAsStream("/Interface/Image/Ball/infinityBall.png"),
                30, 30, false, false);
        explosiveBall = new Image(getClass().getResourceAsStream("/Interface/Image/Ball/explosionBall.png"),
                30, 30, false, false);
        bullet = new Image(getClass().getResourceAsStream("/Interface/Image/Ball/bullet.png"),
                10, 10, false, false);
        explosion = new Image(getClass().getResourceAsStream("/Interface/Image/explosion.png"),
                256, 256, true, false);
        powerUp = new Image(getClass().getResourceAsStream("/Interface/Image/PowerUp.png"),
                128 * 8, 128 * 8, true, false);
        bar = new Image(getClass().getResourceAsStream("/Interface/Image/bar.png"),
                Specifications.WIDTH, Specifications.HEIGHTBar, false, false);
    }

    public Image getBackground() {
        return background;
    }

    public Image getExplosion() {
        return explosion;
    }

    public Image getPowerUp() {
        return powerUp;
    }

    public Image getBar() {
        return bar;
    }

    public Image getBullet() {
        return bullet;
    }

    public Image getNormalBrick() {
        return normalBrick;
    }

    public Image getUnbreakableBrick() {
        return unbreakableBrick;
    }

    public Image getStrongBrick() {
        return strongBrick;
    }

    public Image getBrokenBrick() {
        return brokenBrick;
    }

    public Image getExplosionBrick() {
        return explosiveBrick;
    }

    public Image getBlindBrick() {
        return blindBrick;
    }

    public Image getNormalPaddle() {
        return normalPaddle;
    }

    public Image getLongPaddle() {
        return longPaddle;
    }

    public Image getGunPaddle() {
        return gunPaddle;
    }

    public Image getNormalBall() {
        return normalBall;
    }

    public Image getInfinityBall() {
        return infinityBall;
    }

    public Image getExplosiveBall() {
        return explosiveBall;
    }


}
