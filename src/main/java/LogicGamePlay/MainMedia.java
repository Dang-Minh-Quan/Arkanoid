package LogicGamePlay;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainMedia {

    private static MainMedia instance;

    public static synchronized MainMedia getInstance() {
        if (instance == null) {
            instance = new MainMedia();
        }
        return instance;
    }

    private Media music;
    private MediaPlayer musicPlay;
    private AudioClip destroyBrick;
    private final ExecutorService soundThread;
    private volatile boolean isLoaded = false;

    private MainMedia() {
        this.soundThread = Executors.newSingleThreadExecutor();
        loadMedia();
    }

    public void playMusic() {
        if (isLoaded && musicPlay != null) {
            musicPlay.play();
        }
    }

    public void stopMusic() {
        if (isLoaded && musicPlay != null) {
            musicPlay.stop();
        }
    }

    public void playDestroyBrick() {
        if (isLoaded && destroyBrick != null) {
            soundThread.execute(() -> destroyBrick.play());
        }
    }

    private void loadMedia() {
        soundThread.execute(() -> {
            try {
                music = new Media(getClass().getResource("/Interface/media/beach.mp3").toExternalForm());
                musicPlay = new MediaPlayer(music);
                musicPlay.setVolume(0.5);
                musicPlay.setCycleCount(MediaPlayer.INDEFINITE);

                destroyBrick = new AudioClip(getClass().getResource("/Interface/media/destroyBrick.mp3").toExternalForm());
                destroyBrick.setVolume(1);

                isLoaded = true;
            } catch (Exception e) {
                System.err.println("Lỗi load âm thanh: " + e.getMessage());
            }
        });
    }
}
