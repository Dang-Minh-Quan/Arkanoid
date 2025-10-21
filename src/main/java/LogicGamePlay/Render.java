package LogicGamePlay;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

import static LogicGamePlay.Specifications.*;

public class Render {
    public void renderGame(MainImage image,ArrayList<Ball>balls,Ball ball, Paddle paddle, Brick[][] brick, Pane pane, ArrayList<PowerUp> powerUps) {
        pane.getChildren().clear();
        renderBackGround(pane,image);
        renderBrick(brick, pane);
        renderPaddle(paddle, pane);
        renderBall(ball,pane);
        renderBalls(balls,pane);
        renderPowerUp(powerUps,pane);
        renderBar(pane,image);
    }

    private void renderPowerUp(ArrayList<PowerUp> powerUps, Pane pane) {
        for (int i=0;i<powerUps.size();i++) {
            if(powerUps.get(i).checkActivate==false) {
                pane.getChildren().add(powerUps.get(i).getHitBoxPowerUp());
            }
        }
    }

    private void renderBackGround(Pane pane,MainImage image) {
        Rectangle backGround = new Rectangle(0, 0, WIDTH, HEIGHT);
        backGround.setFill(new ImagePattern(image.getBackgroud1()));
        pane.getChildren().add(backGround);
    }

    private void renderBar(Pane pane,MainImage image) {
        Rectangle bar = new Rectangle(0, HEIGHT, WIDTH, HEIGHTBar);
        bar.setFill(new ImagePattern(image.getBar()));
        pane.getChildren().add(bar);
    }

    private void renderBrick(Brick[][] brick, Pane pane) {
        Pane newBrickPane = Paint.paintBrick(brick, COL, ROW);
        pane.getChildren().add(newBrickPane);
    }

    private void renderPaddle(Paddle paddle, Pane pane) {
        Rectangle paddleImage = new Rectangle(paddle.x , paddle.y,
                paddle.width, paddle.height);
        paddleImage.setFill(Color.BLACK);
        paddleImage.setStroke(Color.BLUE);
        pane.getChildren().add(paddleImage);
    }

    private void renderBall(Ball ball, Pane pane){
        ball.RenderTail(pane);
        pane.getChildren().addAll(ball.getBall());
    }

    private void renderBalls(ArrayList<Ball>balls, Pane pane) {
        for (int i = 0; i < balls.size(); i++) {
            balls.get(i).RenderTail(pane);
            pane.getChildren().addAll(balls.get(i).getBall());
        }
    }
}
