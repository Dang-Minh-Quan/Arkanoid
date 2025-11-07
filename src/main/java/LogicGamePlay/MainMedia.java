package LogicGamePlay;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static LogicGamePlay.Specifications.*;
import static javafx.util.Duration.ZERO;


public class MainMedia {

    private static MainMedia instance;

    public static synchronized MainMedia getInstance() {
        if (instance == null) {
            instance = new MainMedia();
        }
        return instance;
    }

    private Media media;
    private MediaPlayer GameplayMusic;
    private MediaPlayer MenuMusic;
    private MediaPlayer BackGround;
    private MediaView BackGroundView;
    private AudioClip destroyBrick;
    private final ScheduledExecutorService soundThread = Executors.newScheduledThreadPool(2);
    private volatile boolean isLoaded = false;
    private static AudioClip PressButton;

    public MainMedia() {
        LoadMedia();
    }

    public void playGamePlayMusic() {
        if (isLoaded && GameplayMusic != null) {
            stopMenuMusic();
            GameplayMusic.play();
        }
    }

    public void stopGamePlayMusic() {
        if (isLoaded && GameplayMusic != null) {
            GameplayMusic.stop();
            GameplayMusic.seek(ZERO);

        }
    }

    public void playMenuMusic() {
        if (isLoaded && MenuMusic != null) {
            stopGamePlayMusic();
            if (MenuMusic.getStatus() == MediaPlayer.Status.PLAYING) {
                MenuMusic.stop();
            }
            MenuMusic.play();
        }
    }

    public void stopMenuMusic() {
        if (isLoaded && MenuMusic != null) {
            MenuMusic.stop();
            MenuMusic.seek(ZERO);
        }
    }

    public MediaView getBackGroundView() {
        return BackGroundView;
    }

    public void ViewBackGrounnd() {
        if (isLoaded && BackGround != null) {
            BackGround.play();
        }
    }

    public void playDestroyBrick() {
        if (isLoaded && destroyBrick != null) {
            soundThread.execute(() -> destroyBrick.play());
        }
    }

    public void playPressButton() {
        if (isLoaded && destroyBrick != null) {
            PressButton.play();
        }
    }

    public void LoadMedia() {
        if (!isLoaded) {
            media = new Media(getClass().getResource("/Interface/media/beach.mp3").toExternalForm());
            GameplayMusic = new MediaPlayer((media));
            GameplayMusic.setVolume(0.5);
            GameplayMusic.setCycleCount(MediaPlayer.INDEFINITE);
            media = new Media(getClass().getResource("/Interface/media/MenuMusic.mp3").toExternalForm());
            MenuMusic = new MediaPlayer((media));
            MenuMusic.setVolume(1);
            MenuMusic.setCycleCount(MediaPlayer.INDEFINITE);
            media = new Media(getClass().getResource("/Interface/media/BackGround2.mp4").toExternalForm());
            BackGround = new MediaPlayer(media);
            BackGround.setCycleCount(MediaPlayer.INDEFINITE);
            BackGroundView = new MediaView(BackGround);
            BackGroundView.setFitWidth(WIDTH);
            BackGroundView.setFitHeight(HEIGHT + HEIGHTBar);
            BackGroundView.setPreserveRatio(false);
            destroyBrick = new AudioClip(getClass().getResource("/Interface/media/destroyBrick.mp3").toExternalForm());
            PressButton = new AudioClip(getClass().getResource("/Interface/media/ButtonPressed.mp3").toExternalForm());
            destroyBrick.setVolume(1);
            PressButton.setVolume(1);
            isLoaded = true;
        }
    }
}
