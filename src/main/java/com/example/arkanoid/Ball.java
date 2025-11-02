package com.example.arkanoid;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

import static com.example.arkanoid.Specifications.*;

public class Ball extends Baseclass {
    private Circle ball;
    private Circle[] Tail = new Circle[TailLength];
    private int[] TailX=new int[TailLength];
    private int[] TailY=new int[TailLength];

    public Ball(){
        super( 500, 400,5,5, 10,10);
        ball = new Circle(x, y, width, Color.BLUE);
        for (int i=0;i<TailLength;i++){
            TailX[i]=x;
            TailY[i]=y;
            double density=Math.max(0,1-0.5-(double)i/(double)TailLength/2);
            Color ColorTail = new Color(1, 1, 1,density );
            Tail[i] = new Circle(x, y, width-i/2,ColorTail);
        }
    }
    public void setBall(int dx, int dy) {
        UpdateTail();
        x=dx;
        y=dy;
        ball.setCenterX(x);
        ball.setCenterY(y);
    }

    public void UpdateTail() {
        for (int i = TailLength - 1 ;i>0;i--){
            TailX[i]=TailX[i-1];
            TailY[i]=TailY[i-1];
            Tail[i].setCenterX(TailX[i]);
            Tail[i].setCenterY(TailY[i]);
        }
        TailX[0]=x;
        TailY[0]=y;
        Tail[0].setCenterX(TailX[0]);
        Tail[0].setCenterY(TailY[0]);
    }

    public void RenderTail(Pane pane){
        for (int i=0;i<TailLength;i++) {
            pane.getChildren().addAll(Tail[i]);
        }
    }

    public Circle getBall() {
        return ball;
    }

    public int checkWallCollision() {
        boolean check1 = x <= width || x >= WIDTH - width;
        boolean check2 = y <= width || y >= HEIGHT - width;
        if (check1 && check2) {
            return 1;
        } else if (check1) {
            return 2;
        } else if (check2) {
            return 3;
        }
        return 0;
    }

    public int checkPaddleCollision(Paddle paddle) {
        double ballX = x;
        double ballY = y;
        double radius = width;
        double paddleLeft = paddle.x;
        double paddleRight = paddleLeft + paddle.width;
        double paddleTop = paddle.y;
        double paddleBottom = paddleTop + paddle.height;

        boolean collisionX = ballX + radius >= paddleLeft && ballX - radius <= paddleRight;
        boolean collisionY = ballY + radius >= paddleTop && ballY - radius <= paddleBottom;

        if (!collisionX || !collisionY) {
            return -1;
        }
        if (ballX <= paddleLeft) {
            return 2;
        } else if (ballX >= paddleRight) {
            return 3;
        }
        return 1;
    }

    public int checkBrickCollision(Brick[][] brick, ArrayList<PowerUp> powerUps) {
        int brickCol = (int)x/(int) WIDTHBrick ;
        int brickRow = (int)y/(int) HEIGHTBrick ;

        boolean above = false,below = false,left = false,right = false;

        if(vy<0&&brickRow>0&&brickRow<ROW &&(brickRow)*HEIGHTBrick+width>=y){
            above = true;
        }
        if(vy>0&&brickRow<ROW-1 &&(brickRow+1)*HEIGHTBrick-width<=y){
            below =true;
        }
        if(vx<0&&brickCol>0&&brickRow<ROW &&(brickCol)*WIDTHBrick+width>=x){
            left = true;
        }
        if(vx>0&&brickCol<COL-1&&brickRow<ROW &&(brickCol+1)*WIDTHBrick-width<=x){
            right = true;
        }

        if(above == true){
            if(brick[brickRow - 1][brickCol].type!=0) {
                brick[brickRow - 1][brickCol].UpdateBrick(this,powerUps);
                return 2;
            }
        }
        if(below == true){
            if(brick[brickRow + 1][brickCol].type!=0) {
                brick[brickRow + 1][brickCol].UpdateBrick(this,powerUps);
                return 2;
            }
        }
        if(left == true){
            if(brick[brickRow][brickCol - 1].type!=0) {
                brick[brickRow][brickCol - 1].UpdateBrick(this,powerUps);
                return 1;
            }
        }
        if(right == true){
            if(brick[brickRow][brickCol + 1].type!=0) {
                brick[brickRow][brickCol + 1].UpdateBrick(this,powerUps);
                return 1;
            }
        }
        if(above==true&&left==true) {
            if(brick[brickRow - 1][brickCol - 1].type!=0) {
                brick[brickRow - 1][brickCol - 1].UpdateBrick(this,powerUps);
                if(Math.abs((brickRow)*HEIGHTBrick-(int)y)>Math.abs((brickCol)*WIDTHBrick-(int)x)){
                    return 2;
                }else {
                    return 1;
                }
            }
        }
        if(below==true&&left==true) {
            if(brick[brickRow + 1][brickCol - 1].type!=0) {
                brick[brickRow + 1][brickCol - 1].UpdateBrick(this,powerUps);
                if(Math.abs((brickRow+1)*HEIGHTBrick-(int)y)>Math.abs((brickCol)*WIDTHBrick-(int)x)){
                    return 2;
                }else {
                    return 1;
                }
            }
        }
        if(above==true&&right==true) {
            if(brick[brickRow - 1][brickCol + 1].type!=0) {
                brick[brickRow - 1][brickCol + 1].UpdateBrick(this,powerUps);
                if(Math.abs((brickRow)*HEIGHTBrick-(int)y)>Math.abs((brickCol+1)*WIDTHBrick-(int)x)){
                    return 2;
                }else {
                    return 1;
                }
            }
        }
        if(below==true&&right==true) {
            if(brick[brickRow + 1][brickCol + 1].type!=0) {
                brick[brickRow + 1][brickCol + 1].UpdateBrick(this,powerUps);
                if(Math.abs((brickRow+1)*HEIGHTBrick-(int)y)>Math.abs((brickCol+1)*WIDTHBrick-(int)x)){
                    return 2;
                }else {
                    return 1;
                }
            }
        }
        return 0;
    }
}
