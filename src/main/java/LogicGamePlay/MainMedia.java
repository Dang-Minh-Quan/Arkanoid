package com.example.arkanoid;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class MainMedia {
    private Media music ;
    private MediaPlayer musicPlay;
    private AudioClip destroyBrick;
    private ExecutorService soundThread;
    private boolean checkLoad = false;

    public MainMedia(){
        this.soundThread= Executors.newFixedThreadPool(1);
    }

    public void playMusic() {
        if(checkLoad) {
            soundThread.execute(() -> {
                musicPlay.play();
            });
        }
    }

    public void playDestroyBrick() {
        if(checkLoad) {
            soundThread.execute(() -> {
                destroyBrick.play();
            });
        }
    }

    public void LoadMedia() {
        soundThread.execute(()-> {
            try {
                music = new Media(getClass().getResource("beach.mp3").toExternalForm());
                musicPlay = new MediaPlayer((music));
                musicPlay.setVolume(0.5);
                musicPlay.setCycleCount(MediaPlayer.INDEFINITE);
                destroyBrick = new AudioClip(getClass().getResource("destroyBrick.mp3").toExternalForm());
                destroyBrick.setVolume(1);
                checkLoad = true;
            }
            catch (Exception e){
                System.err.println("loi");
            }
        });
    }
}
