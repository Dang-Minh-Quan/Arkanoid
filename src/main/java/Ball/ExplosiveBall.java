package Ball;

public class ExplosiveBall extends Ball {

    public ExplosiveBall(int x, int y) {
        super(x, y);
        this.type = "explosive";
        this.image = mainImage.getExplosiveBall();
        colorTail(1,159 / 255.0,0);
    }
}
