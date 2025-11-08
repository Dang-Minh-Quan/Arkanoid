package LogicGamePlay;

import Interface.GamePlayController;

import java.util.concurrent.atomic.AtomicInteger;

import static Interface.GamePlayController.*;

public class Specifications {

    public static final int COL = 9;
    public static final int ROW = 10;
    public static final int vxOriginal = 10;
    public static final int LevelMax = 4;
    public static final int paddleWidthOriginal = 100;
    public static final int paddleHeightOriginal = 20;
    public static final int spvxOriginal = 6;
    public static int numBrick = 0;
    public static int HEIGHT = 550;
    public static int WIDTH = 500;
    public static int HEIGHTBrick = 30;
    public static int WIDTHBrick = WIDTH / COL;
    public static int speedPU = 2;
    public static int RADIUSPU = HEIGHTBrick / 2;
    public static int PU = 6;
    public static final int probability = 4;
    public static final int TailLength = 40;
    public static final int HEIGHTBar = 150;
    public static final int ballRadiusOriginal = 10;
    public static AtomicInteger heartCount = new AtomicInteger(3);
    public static boolean winLevel = false;
    public static AtomicInteger Level = new AtomicInteger(1);
    public static AtomicInteger score = new AtomicInteger(0);
    public static final int TimePowerUpOriginal = 10;
    public static boolean blind = false;
    public static int timeButter = 60;


    public static void reset() {
        Level.set(1);
        winLevel = false;
        numBrick = 0;
        heartCount.set(3);
        score.set(0);
        GamePlayController.GameOverCheck = false;
    }
}
