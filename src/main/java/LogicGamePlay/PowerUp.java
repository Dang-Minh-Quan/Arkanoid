package LogicGamePlay;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;


import java.util.ArrayList;

import static LogicGamePlay.Specifications.*;

public class PowerUp extends BaseClass {
    private Circle HitBoxPowerUp;
    int checkTimePowerUp=TimePowerUp;
    boolean checkActivate=false;

    public PowerUp(int i,int j){
        super( i+WIDTHBrick/2, j+HEIGHTBrick/2,0,speedPU, RADIUSPU,RADIUSPU);
        type = (int)(Math.random()*PU)%PU;
        if(type==3){checkTimePowerUp=0;}
        HitBoxPowerUp = new Circle(x, y, width, Color.BLACK);
    }

    public Circle getHitBoxPowerUp() {
        return HitBoxPowerUp;
    }

    public void checkStopPowerUp(ArrayList<Ball>balls,Paddle paddle,Ball ball) {
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

    public int UpdatePU(ArrayList<Ball>balls, Paddle paddle, Ball ball) {
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

    public void Activate(ArrayList<Ball>balls,Paddle paddle,Ball ball){
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
}

