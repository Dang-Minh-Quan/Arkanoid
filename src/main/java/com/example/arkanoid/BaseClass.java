package com.example.arkanoid;


import javafx.scene.image.Image;

public abstract class BaseClass {
    public Image image;
    public double type;
    public double x;
    public double y;
    public double vx;
    public double vy;
    public double width;
    public double height;

    public BaseClass() {
        this(null, 0, 0, 0, 0, 0, 0, 0);
    }

    public BaseClass(final Image image) {
        this(image, 0, 0, 0, 0, 0, 0, 0);
    }

    public BaseClass(final Image image, final double x, final double y) {
        this(null, 0, x, y, 0, 0, 0, 0);
    }

    public BaseClass(final Image image, final double x, final double y, final double width, final double height) {
        this(image, 0, x, y,0, 0, width, height);
    }

    public BaseClass(final Image image, final double x, final double y, double vx, final double width, final double height) {
        this(image, 0, x, y, vx, 0, width, height);
    }

    public BaseClass(final Image image, final double type, final double x, double y, double vx, double vy, double width, double height) {
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


