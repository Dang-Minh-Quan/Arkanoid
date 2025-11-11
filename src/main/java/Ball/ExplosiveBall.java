package Ball;

public class ExplosiveBall extends Ball {
    /**
     * khoi tao bong no.
     * @param x vi tri theo phuong x.
     * @param y vi tri theo phuong y.
     */
    public ExplosiveBall(int x, int y) {
        super(x, y);
        this.type = "explosive";
        this.image = mainImage.getExplosiveBall();
        colorTail(1,159 / 255.0,0);
    }
}
