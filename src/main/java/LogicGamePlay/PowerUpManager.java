package LogicGamePlay;

import java.util.List;
import java.util.concurrent.*;

public class PowerUpManager {
    private final ConcurrentLinkedQueue<PowerUp> activePowerUp =
            new ConcurrentLinkedQueue<>();
    private int activeBall = 0;
    private int activePaddle = 0;

    private final ScheduledExecutorService Sheduler = Executors.newScheduledThreadPool(2);

    public void applyPowerUp(PowerUp powerUp,Paddle paddle,List<Ball> balls){
        powerUp.Activate(balls,paddle);
        activePowerUp.add(powerUp);
        switch (powerUp.type){
            case 0,3:
                activeBall++;
            case 1,5:
                activePaddle++;
        }
        Sheduler.schedule(()->{
            switch (powerUp.type){
                case 0,3:
                    activeBall--;
                    if(activeBall == 0){
                        powerUp.StopPowerUp(balls,paddle);
                        activePowerUp.remove(powerUp);
                    }
                case 1,5:
                    activePaddle--;
                    if(activePaddle == 0){
                        powerUp.StopPowerUp(balls,paddle);
                        activePowerUp.remove(powerUp);
                    }
            }
        },powerUp.TimePowerUp, TimeUnit.SECONDS);
    }

    public void stop(Paddle paddle,List<Ball> balls){
        activePaddle = 0;
        activeBall = 0;
        for (int j = -1;j < 6;j++){
            PowerUp powerUp = new PowerUp(j);
            powerUp.StopPowerUp(balls,paddle);
        }
        activePowerUp.clear();
    }

    public void shutDown(){
        Sheduler.shutdown();
    }
}
