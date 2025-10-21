package LogicGamePlay;

import javafx.scene.image.Image;

import static LogicGamePlay.Specifications.*;

public class MainImage {
    private static Image brick1;
    private static Image brick2;
    private static Image backgroud1;
    private static Image bar;

    public Image getBrick2() {
        return brick2;
    }

    public Image getBrick1() {
        return brick1;
    }

    public Image getBackgroud1() {
        return backgroud1;
    }

    public Image getBar(){return bar;}

    public void LoadImage() {
        brick1 = new Image(getClass().getResourceAsStream("brick1.png"), 60, 30, false, false);
        brick2 = new Image(getClass().getResourceAsStream("brick2.png"), 60, 30, false, false);
        backgroud1 = new Image(getClass().getResourceAsStream("background1.jpg"), WIDTH, HEIGHT, false,false);
        bar = new Image(getClass().getResourceAsStream("bar.png"), WIDTH, HEIGHTBar, false,false);
    }
}
