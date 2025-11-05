package LogicGamePlay;

import java.util.List;

import Interface.GamePlayController;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

import static LogicGamePlay.Specifications.*;

public class PowerUp extends AnimationClass {
    private boolean active = true;

    private Image image;
    private Circle HitBoxPowerUp;
    int checkTimePowerUp=TimePowerUp;
    boolean checkActivate=false;

    public PowerUp(Image spriteSheet, int x, int y) {
        super(spriteSheet, x, y,0,speedPU, RADIUSPU,RADIUSPU,4,4,5);
        this.image = spriteSheet;
        type = (int)(Math.random()*PU)%PU;
        //type = 2;
        HitBoxPowerUp = new Circle(x, y, width, Color.BLACK);
    }

    public void update() {
        if (!active) return;
        y += vy;
        if (y > 720) active = false;
        Update();
    }

    public void checkStopPowerUp(List<Ball> balls, Paddle paddle) {
        if(checkTimePowerUp==0) {
            switch (type) {
                case 0:
                    for (int i=0;i<balls.size();i++) {
                        balls.get(i).type = 0;
                    }
                    break;
                case 1:
                    paddle.type = 0;
                    paddle.Update();
                    paddle.setPaddle(paddleWidthOriginal,paddle.x + paddleWidthOriginal/2);
                    break;
                case 3:
                    for (int i=0;i<balls.size();i++) {
                        balls.get(i).type = 0;
                    }
                    break;
                case 4:
                    blind = false;
                    break;
            }
        }
        checkTimePowerUp--;
    }

    public int UpdatePU(List<Ball>balls, Paddle paddle,  List<PowerUp> powerUps) {
        y=y+vy;
        HitBoxPowerUp.setCenterY(y);
        if (checkActivate == false) {
            if (Shape.intersect(HitBoxPowerUp, paddle.getPaddle()).getBoundsInLocal().getWidth() > 0) {
                Activate(balls, paddle, powerUps);
                return 1;
            }
            if (y == HEIGHT + RADIUSPU) {
                return 2;
            }
        }
        return 0;
    }

    private void Activate(List<Ball>balls,Paddle paddle,List<PowerUp> powerUps){
        switch (type){
            case 0:
                removePowerUp(balls,paddle,powerUps,0);
                removePowerUp(balls,paddle,powerUps,3);
                for (int i=0;i<balls.size();i++) {
                    balls.get(i).type = 2;
                }
                break;
            case 1:
                removePowerUp(balls,paddle,powerUps,type);
                paddle.type=1;
                paddle.Update();
                int xx = paddleWidthOriginal/2;
                if(paddle.width + paddle.x + xx > WIDTH){
                    xx = xx + (paddle.width + paddle.x + paddleWidthOriginal/2 -WIDTH);
                } else {
                    if(paddle.x - xx < 0){
                        xx = xx - (paddle.x - paddleWidthOriginal/2 );
                    }
                }
                paddle.setPaddle(paddleWidthOriginal*2,paddle.x-xx);
                break;
            case 2:
                Ball newBall = new Ball();
                balls.add(newBall);
                checkTimePowerUp = -1;
                break;
            case 3:
                removePowerUp(balls,paddle,powerUps,0);
                removePowerUp(balls,paddle,powerUps,3);
                for (int i=0;i<balls.size();i++) {
                    balls.get(i).type = 1;
                }
                break;
            case 4:
                blind = true;
                checkTimePowerUp = checkTimePowerUp / 10;
                break;
        }
    }

    private void removePowerUp(List<Ball>balls,Paddle paddle,List<PowerUp> powerUps,int Type){
        for (int i=0;i<powerUps.size();i++){
            PowerUp p=powerUps.get(i);
            if(p.type==Type&&p.checkActivate==true){
                powerUps.get(i).checkTimePowerUp=0;
                powerUps.get(i).checkStopPowerUp(balls,paddle);
                powerUps.remove(i);
                break;
            }
        }
    }

    public void render(GraphicsContext gc) {
        if (!active || image == null) return;
        super.draw(gc);
    }

    public boolean isActive() {
        return active;
    }
}