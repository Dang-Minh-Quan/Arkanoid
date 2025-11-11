package PowerUp;

import Ball.*;
import Paddle.*;
import LogicGamePlay.Render;

import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;

public class PowerUpManager {
    private final ConcurrentLinkedQueue<PowerUp> activePowerUp =
            new ConcurrentLinkedQueue<>();
    private int activeBall = 0;
    private int activePaddle = 0;

    private final ScheduledExecutorService Sheduler = Executors.newScheduledThreadPool(2);

    private final Render render;

    public PowerUpManager(Render render) {
        this.render = render;
    }

    public void applyPowerUp(PowerUp powerUp, AtomicReference<Paddle> paddle, List<Ball> balls) {
        powerUp.Activate(balls, paddle);
        activePowerUp.add(powerUp);

        if ("bonus_point".equals(powerUp.type)) {
            render.showBonusImage(
                    paddle.get().x + paddle.get().width / 2,
                    paddle.get().y - 10
            );
        }

        switch (powerUp.type) {
            case "infinity ball", "explosive ball":
                activeBall++;
                break;
            case "long paddle", "gun paddle":
                activePaddle++;
                break;
        }
        if(powerUp.TimePowerUp == 0){
            powerUp.StopPowerUp(balls, paddle);
            activePowerUp.remove(powerUp);
        } else {
            Sheduler.schedule(() -> {
                switch (powerUp.type) {
                    case "infinity ball", "explosive ball":
                        activeBall--;
                        if (activeBall == 0) {
                            powerUp.StopPowerUp(balls, paddle);
                            activePowerUp.remove(powerUp);
                        }
                        break;
                    case "long paddle", "gun paddle":
                        activePaddle--;
                        if (activePaddle == 0) {
                            powerUp.StopPowerUp(balls, paddle);
                            activePowerUp.remove(powerUp);
                        }
                        break;
                    default:
                        powerUp.StopPowerUp(balls, paddle);
                        activePowerUp.remove(powerUp);
                }
            }, powerUp.TimePowerUp, TimeUnit.SECONDS);
        }
    }

    public void stop(AtomicReference<Paddle> paddle, List<Ball> balls) {
        activePaddle = 0;
        activeBall = 0;
        for (int j = -1; j < 6; j++) {
            String k = new String();
            switch (j) {
                case -1:
                    k = "blind";
                    break;
                case 0:
                    k = "infinity ball";
                    break;
                case 1:
                    k = "long paddle";
                    break;
                case 2:
                    k = "multi ball";
                    break;
                case 3:
                    k = "explosive ball";
                    break;
                case 4:
                    k = "bonus point";
                    break;
                case 5:
                    k = "gun paddle";
                    break;
            }
            PowerUp powerUp = new PowerUp(k);
            powerUp.StopPowerUp(balls, paddle);
        }
        activePowerUp.clear();
    }

    public void shutDown() {
        Sheduler.shutdown();
    }
}
