package Media;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

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
    private AudioClip GameOver;
    private AudioClip Win;
    private AudioClip PowerUp;
    private final ScheduledExecutorService soundThread = Executors.newScheduledThreadPool(2);
    private volatile boolean isLoaded = false;
    private static AudioClip PressButton;
    public static boolean Muted = false;

    public MainMedia() {
        LoadMedia();
    }

    public void muteAllMedia() {
      if (!Muted) {
        GameplayMusic.setVolume(0.0);
        MenuMusic.setVolume(0.0);
        BackGround.setVolume(0.0);
        destroyBrick.setVolume(0.0);
        PressButton.setVolume(0.0);
        PowerUp.setVolume(0.0);
        Win.setVolume(0.0);
        GameOver.setVolume(0.0);

        Muted = true;
      }
    }

  public void unmuteAllMedia() {
    if (Muted) {
      GameplayMusic.setVolume(0.1);
      MenuMusic.setVolume(0.3);
      BackGround.setVolume(1.0);
      destroyBrick.setVolume(1.0);
      PressButton.setVolume(1.0);
      PowerUp.setVolume(2.0);
      Win.setVolume(2.0);
      GameOver.setVolume(3.0);

      Muted = false;
    }
  }

  public static boolean isMuted() {
    return Muted;
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

  public void playPowerUp() {
    if (isLoaded && destroyBrick != null) {
      PowerUp.play();
    }
  }


  public void playPressButton() {
        if (isLoaded && destroyBrick != null) {
            PressButton.play();
        }
    }

  public void playGameOver() {
    if (isLoaded && destroyBrick != null) {
      GameOver.play();
    }
  }

  public void playWin() {
    if (isLoaded && destroyBrick != null) {
      Win.play();
    }
  }

  public void LoadMedia() {
    if (!isLoaded) {
      media = new Media(getClass().getResource("/Interface/media/beach.mp3").toExternalForm());
      GameplayMusic = new MediaPlayer((media));
      GameplayMusic.setVolume(0.1);
      GameplayMusic.setCycleCount(MediaPlayer.INDEFINITE);
      media = new Media(getClass().getResource("/Interface/media/MenuMusic.mp3").toExternalForm());
      MenuMusic = new MediaPlayer((media));
      MenuMusic.setVolume(0.3);
      MenuMusic.setCycleCount(MediaPlayer.INDEFINITE);
      media = new Media(getClass().getResource("/Interface/media/BackGround.mp4").toExternalForm());
      BackGround = new MediaPlayer(media);
      BackGround.setCycleCount(MediaPlayer.INDEFINITE);
      BackGroundView = new MediaView(BackGround);
      BackGroundView.setFitWidth(WIDTH);
      BackGroundView.setFitHeight(HEIGHT + HEIGHTBar);
      BackGroundView.setPreserveRatio(false);
      destroyBrick = new AudioClip(getClass().getResource("/Interface/media/destroyBrick.mp3").toExternalForm());
      PressButton = new AudioClip(getClass().getResource("/Interface/media/ButtonPressed.mp3").toExternalForm());
      GameOver = new AudioClip(getClass().getResource("/Interface/media/GameOver.mp3").toExternalForm());
      Win = new AudioClip(getClass().getResource("/Interface/media/Win.mp3").toExternalForm());
      PowerUp = new AudioClip(getClass().getResource("/Interface/media/PowerUp.mp3").toExternalForm());
      destroyBrick.setVolume(1);
      PressButton.setVolume(1);
      GameOver.setVolume(3);
      Win.setVolume(2);
      PowerUp.setVolume(2);
      isLoaded = true;
    }
  }
}
