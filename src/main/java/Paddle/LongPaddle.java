package Paddle;

import static LogicGamePlay.Specifications.*;

public class LongPaddle extends Paddle {
    public LongPaddle(int x, int y) {
        super(x, y);
        width = paddleWidthOriginal * 2;
        this.image = mainImage.getLongPaddle();
        this.type = "long";
    }
}
