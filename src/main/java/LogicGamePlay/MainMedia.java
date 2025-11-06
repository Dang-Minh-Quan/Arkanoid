package LogicGamePlay;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class MainMedia {
    private Media music ;
    private static MediaPlayer GameplayMusic;
    private static MediaPlayer MenuMusic;
    private AudioClip destroyBrick;
    private static AudioClip PressButton;
    private ExecutorService soundThread;
    private static boolean checkLoad = false;

    public MainMedia(){
        this.soundThread= Executors.newFixedThreadPool(1);
    }

    public void playGamePlayMusic() {
        if(checkLoad) {
            GameplayMusic.play();
        }
    }

    public static void stopGamePlayMusic() {
        if(checkLoad) {
            GameplayMusic.stop();
        }
    }

  public static void playMenuMusic() {
    if(checkLoad) {
      if (MenuMusic.getStatus() == MediaPlayer.Status.PLAYING) {
        MenuMusic.stop();
      }
      MenuMusic.play();
    }
  }

  public static void stopMenuMusic() {
      MenuMusic.stop();
  }

    public void playDestroyBrick() {
        if(checkLoad) {
            destroyBrick.play();
        }
    }

    public static void playPressButton() {
      if(checkLoad) {
        PressButton.play();
      }
    }

    public void LoadMedia() {
        soundThread.execute(()-> {
            try {
                music = new Media(getClass().getResource("/Interface/media/beach.mp3").toExternalForm());
                GameplayMusic = new MediaPlayer((music));
                GameplayMusic.setVolume(0.5);
                GameplayMusic.setCycleCount(MediaPlayer.INDEFINITE);
                music = new Media(getClass().getResource("/Interface/media/MenuMusic.mp3").toExternalForm());
                MenuMusic = new MediaPlayer((music));
                MenuMusic.setVolume(1);
                MenuMusic.setCycleCount(MediaPlayer.INDEFINITE);
                destroyBrick = new AudioClip(getClass().getResource("/Interface/media/destroyBrick.mp3").toExternalForm());
                PressButton = new AudioClip(getClass().getResource("/Interface/media/ButtonPressed.mp3").toExternalForm());
                destroyBrick.setVolume(1);
                PressButton.setVolume(1);
                checkLoad = true;
            }
            catch (Exception e){
                System.err.println("loi");
            }
        });
    }
}
