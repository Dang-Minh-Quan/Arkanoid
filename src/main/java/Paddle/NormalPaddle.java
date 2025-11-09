package Paddle;

public class NormalPaddle extends Paddle {
    public NormalPaddle(int x, int y) {
        super(x, y);
        this.image = mainImage.getNormalPaddle();
        this.type = "normal";
    }
}
