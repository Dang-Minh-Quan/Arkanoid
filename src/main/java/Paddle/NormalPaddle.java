package Paddle;

public class NormalPaddle extends Paddle {
    /**
     * khoi tao paddle thuong.
     * @param x vi tri theo phuong x.
     * @param y vi tri theo phuong y.
     */
    public NormalPaddle(int x, int y) {
        super(x, y);
        this.image = mainImage.getNormalPaddle();
        this.type = "normal";
    }
}
