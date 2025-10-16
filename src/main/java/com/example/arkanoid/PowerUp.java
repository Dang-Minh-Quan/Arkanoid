package com.example.arkanoid;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

import java.awt.*;
import java.util.ArrayList;

import static com.example.arkanoid.Specifications.*;

public class PowerUp extends Baseclass{
    private Circle HitBoxPowerUp;

    public PowerUp(int i,int j){
        super( i+WIDTHBrick/2, j+HEIGHTBrick/2,0,speedPU, RADIUSPU,RADIUSPU);
        type = (int)(Math.random()*PU)%PU;
        HitBoxPowerUp = new Circle(x, y, width, Color.BLACK);
    }

    public Circle getHitBoxPowerUp() {
        return HitBoxPowerUp;
    }

    public boolean UpdatePU(ArrayList<Ball>balls, Paddle paddle, Ball ball) {
        y=y+vy;
        HitBoxPowerUp.setCenterY(y);
        if(Shape.intersect(HitBoxPowerUp,paddle.getHitBoxPaddle()).getBoundsInLocal().getWidth()>0){
            Activate(balls,paddle,ball);
            return true;
        }

        if(y==HEIGHT+RADIUSPU){return true;}
        return false;
    }

    public void Activate(ArrayList<Ball>balls,Paddle paddle,Ball ball){
        System.out.println(type);
        if(type==0){

        }
        if (type==1){
            paddle.width=300;
        }
        if (type==2){
            Ball newBall=new Ball();
            balls.add(newBall);
        }
    }
}

