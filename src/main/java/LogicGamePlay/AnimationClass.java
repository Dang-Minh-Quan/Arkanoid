package LogicGamePlay;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Lớp trừu tượng AnimationClass dùng để xử lý các đối tượng có hoạt ảnh (animation),
 * ví dụ như vụ nổ, chuyển động khung hình,... kế thừa từ {@link BaseClass}.
 * <p>
 * Lớp này chia hình ảnh gốc thành nhiều khung (frame) và hiển thị lần lượt
 * để tạo hiệu ứng hoạt hình.
 */
public abstract class AnimationClass extends BaseClass {
    protected int frameCols;
    protected int frameRows;
    protected int totalFrames;
    protected int currentFrame;
    protected int frameDelay;  // tốc độ xoay
    protected int delayCounter;

    /**
     * Constructor mặc định, chỉ định loại animation.
     *
     * @param type loại animation (explosion, powerup)
     */
    public AnimationClass(String type) {
        super();
        this.type = type;
        this.frameCols = 0;
        this.frameRows = 0;
        this.frameDelay = 0;
        this.totalFrames = frameCols * frameRows;
        this.currentFrame = 0;
    }

    public AnimationClass(Image image, final int x, final int y, int vx, int vy,
                          final int width, final int height, int frameCols,
                          int frameRows, int frameDelay) {
        super(image, x, y, vx, vy, width, height);
        this.frameCols = frameCols;
        this.frameRows = frameRows;
        this.frameDelay = frameDelay;
        this.totalFrames = frameCols * frameRows;
        this.currentFrame = 0;
    }

    /**
     * Cập nhật frame hiện tại của animation.
     * Tăng delayCounter và chuyển sang frame tiếp theo khi đủ thời gian trễ.
     */
    @Override
    public void Update() {
        delayCounter++;
        if (delayCounter >= frameDelay) {
            delayCounter = 0;
            currentFrame = (currentFrame + 1) % totalFrames;
        }
    }

    /**
     * Vẽ frame hiện tại của animation lên canvas.
     *
     * @param gc GraphicsContext dùng để vẽ ảnh lên canvas
     */
    public void draw(GraphicsContext gc) {
        int frameWidth = (int) image.getWidth() / frameCols;
        int frameHeight = (int) image.getHeight() / frameRows;

        int col = currentFrame % frameCols;
        int row = currentFrame / frameCols;

        gc.drawImage(
                image,
                col * frameWidth, row * frameHeight, frameWidth, frameHeight,
                x, y, width * 2, height * 2
        );
    }
}
