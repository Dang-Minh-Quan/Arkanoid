package Ball;

public class NormalBall extends Ball {
    /**
     * khoi tao ball thuong.
     * @param x vi tri theo phuong x.
     * @param y vi tri theo phuong y.
     */
    public NormalBall(int x, int y) {
        super(x, y);
        this.image = mainImage.getNormalBall();
        this.type = "normal";
        colorTail(175 / 255.0,255 / 255.0,255 / 255.0);
    }
}
