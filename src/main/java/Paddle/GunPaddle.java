package Paddle;

public class GunPaddle extends Paddle {
    public GunPaddle(int x, int y) {
        super(x, y);
        this.image = mainImage.getGunPaddle();
        this.type = "gun";
    }
}
