package LogicGamePlay;

import javafx.scene.image.Image;

import static LogicGamePlay.Specifications.HEIGHTBar;
import static LogicGamePlay.Specifications.WIDTH;

public class MainImage {
    private static Image brick1;
    private static Image brick2;
    private static Image brick3;
    private static Image brick4;
    private static Image paddle;
    private static Image ball;
    private static Image background1;
    private static Image explosion;
    private static Image bar;

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

    public Image getBackground1() {

        return background1;
    }


    public Image getBar() {

        return bar;
    }
    public Image getPaddle() {
        return paddle;
    }

    public Image getBall() {
        return ball;
    }

    public static Image getExplosion() {
        return explosion;
    }

    public void LoadImage() {
        brick1 = new Image(getClass().getResourceAsStream("/Interface/Image1/brick1.png"),
                60, 30, false, false);
        brick2 = new Image(getClass().getResourceAsStream("/Interface/Image1/brick2.png"),
                60, 30, false, false);
        brick3 = new Image(getClass().getResourceAsStream("/Interface/Image1/brick3.png"),
                60, 30, false, false);
        brick4 = new Image(getClass().getResourceAsStream("/Interface/Image1/brick4.png"),
                60, 30, false, false);
        background1 = new Image(getClass().getResourceAsStream("/Interface/Image1/background1.png"));
        paddle = new Image(getClass().getResourceAsStream("/Interface/Image1/paddle.png"),
                100, 30, false, false);
        ball = new Image(getClass().getResourceAsStream("/Interface/Image1/ball.png"),
                30, 30, false, false);
        explosion = new Image(getClass().getResourceAsStream("/Interface/Image1/explosion.png"),
                256, 256, true, false);
        bar = new Image(getClass().getResourceAsStream("/Interface/Image1/bar1.png"),
                WIDTH, HEIGHTBar, false,false);
    }
}
