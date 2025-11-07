package LogicGamePlay;


import javafx.scene.image.Image;

public abstract class BaseClass {
    public Image image;
    public String type;
    public int x;
    public int y;
    public int vx;
    public int vy;
    public int width;
    public int height;

    public BaseClass() {
        this(null, "basic", 0, 0, 0, 0, 0, 0);
    }

    public BaseClass(final Image image) {
        this(image, "basic", 0, 0, 0, 0, 0, 0);
    }

    public BaseClass(Image image , final int x, final int y, final int width, final int height) {
        this(image, "basic", x, y,0, 0, width, height);
    }

    public BaseClass(Image image,final int x, final int y, int vx, int vy, final int width, final int height) {
        this(image, "basic", x, y, vx, vy, width, height);
    }

    public BaseClass(final Image image, final String type, final int x, int y, int vx, int vy, int width, int height) {
        this.image = image;
        this.type = type;
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.width = width;
        this.height = height;
    }

    public void Update(){}
}


