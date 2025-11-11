package Paddle;

import static LogicGamePlay.Specifications.*;

public class LongPaddle extends Paddle {
    /**
     * khoi tao paddle dai.
     * @param x vi tri theo phuong x.
     * @param y vi tri theo phuong y.
     */
    public LongPaddle(int x, int y) {
        super(x, y);
        width = paddleWidthOriginal * 2;
        this.image = mainImage.getLongPaddle();
        this.type = "long";
    }
}
