package Ball;

public class NormalBall extends Ball {

    public NormalBall(int x, int y) {
        super(x, y);
        this.image = mainImage.getNormalBall();
        this.type = "normal";
        colorTail(175 / 255.0,255 / 255.0,255 / 255.0);
    }
}
