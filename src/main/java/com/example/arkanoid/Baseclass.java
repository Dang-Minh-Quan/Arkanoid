package com.example.arkanoid;


import javafx.scene.image.Image;

public abstract class Baseclass {
    public Image image;
    public int type;
    public int x;
    public int y;
    public int vx;
    public int vy;
    public int width;
    public int height;

    public Baseclass() {
        this(null, 0, 0, 0, 0, 0, 0, 0);
    }

    public Baseclass(final Image image) {
        this(image, 0, 0, 0, 0, 0, 0, 0);
    }

    public Baseclass(final Image image, final int x, final int y) {
        this(null, 0, x, y, 0, 0, 0, 0);
    }

    public Baseclass(final Image image, final int x, final int y, final int width,final int height) {
        this(image, 0, x, y,0, 0, width, height);
    }

    public Baseclass(final Image image, final int x, final int y, int vx,final int width,final int height) {
        this(image, 0, x, y, vx, 0, width, height);
    }

    public Baseclass(final Image image, final int type, final int x, int y, int vx, int vy, int width, int height) {
        this.image = image;
        this.type = type;
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.width = width;
        this.height = height;
    }

    public abstract void Update();

    protected void init() {
    }

    public void respawn() {
    }
}


