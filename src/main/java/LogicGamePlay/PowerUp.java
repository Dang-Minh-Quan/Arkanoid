package LogicGamePlay;

import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

import static LogicGamePlay.Specifications.*;

public class PowerUp extends BaseClass {
    private boolean active = true;

    private Image image;
    private int frameCols = 4;
    private int frameRows = 4;
    private int totalFrames = frameCols * frameRows;
    private int currentFrame = 0;
    private int frameDelay = 5;  // tốc độ xoay
    private int delayCounter = 0;
    private Circle HitBoxPowerUp;
    int checkTimePowerUp=TimePowerUp;
    boolean checkActivate=false;

    public PowerUp(Image spriteSheet, int x, int y) {
        super( x, y,0,speedPU, RADIUSPU,RADIUSPU);
        this.image = spriteSheet;
        type = (int)(Math.random()*PU)%PU;
        if(type==3){checkTimePowerUp=0;}
    }

    public Circle getHitBoxPowerUp() {
        return HitBoxPowerUp;
    }

    public void update() {
        if (!active) return;
        y += vy;
        if (y > 720) active = false;

            delayCounter++;
        if (delayCounter >= frameDelay) {
            delayCounter = 0;
            currentFrame = (currentFrame + 1) % totalFrames;
        }
    }

    public void checkStopPowerUp(List<Ball> balls, Paddle paddle, Ball ball) {
        if(checkTimePowerUp==0) {
            switch (type) {
                case 0:
                    break;
                case 1:
                    paddle.width = paddleWidthOriginal;
                    break;
            }
        }
        checkTimePowerUp--;
    }

    public int UpdatePU(List<Ball>balls, Paddle paddle, Ball ball) {
        y=y+vy;
        HitBoxPowerUp.setCenterY(y);
        if(checkActivate==false) {
            if (Shape.intersect(HitBoxPowerUp, paddle.getHitBoxPaddle()).getBoundsInLocal().getWidth() > 0) {
                Activate(balls, paddle, ball);
                return 1;
            }
            if (y == HEIGHT + RADIUSPU) {
                return 2;
            }
        }
        return 0;
    }

    public void Activate(List<Ball>balls,Paddle paddle,Ball ball){
        switch (type){
            case 0:
                break;
            case 1:
                paddle.width=300;
                break;
            case 2:
                Ball newBall=new Ball();
                balls.add(newBall);
                checkTimePowerUp=-1;
                break;
        }
    }

    public void render(GraphicsContext gc) {
        if (!active || image == null) return;

        int frameWidth = (int) image.getWidth() / frameCols;
        int frameHeight = (int) image.getHeight() / frameRows;

        int col = currentFrame % frameCols;
        int row = currentFrame / frameCols;

        gc.drawImage(
                image,
                col * frameWidth, row * frameHeight, frameWidth, frameHeight,
                x, y, width*4, height*4
        );
    }

    public boolean isActive() {
        return active;
    }
}

