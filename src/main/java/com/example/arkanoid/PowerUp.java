package com.example.arkanoid;

import com.fasterxml.jackson.databind.introspect.AnnotatedClass;

import java.util.ArrayList;

import static com.example.arkanoid.Specifications.*;

public class PowerUp extends baseClass{
    public PowerUp(double i,double j){
        super(null, i, j,0,speedPU, WIDTHPU,HEIGHTPU);
        type = (int)(Math.random()*PU)%PU;
    }

    public boolean UpdatePU(Paddle paddle,Ball ball) {
        y=y+vy;

        if(x >= paddle.x-width&&x<=paddle.x+paddle.width&&y>=paddle.y-height&&y<=paddle.y+paddle.height){
            Activate(paddle,ball);
            return true;
        }

        if(y==HEIGHT){return true;}
        return false;
    }

    public void Activate(Paddle paddle,Ball ball){
        if(type==0){
            ball.UpdateWidth();
        }
        if (type==1){
            paddle.width=300;
        }
    }
}

