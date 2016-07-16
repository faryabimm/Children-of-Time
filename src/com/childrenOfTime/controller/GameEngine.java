package com.childrenOfTime.controller;

import com.childrenOfTime.model.ChildrenOfTime;
import com.childrenOfTime.model.Player;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by mohammadmahdi on 5/7/16.
 */
public class GameEngine {

    public static final Toolkit DEFAULT_TOOLKIT = Toolkit.getDefaultToolkit();
    public static boolean themeMusiceIsPlaying = false;

    private static Clip themeMusicClip;
    public static boolean musicPlayBackAllowed = true;


    private ChildrenOfTime childrenOfTime = ChildrenOfTime.getInstance();

    public void startGame() throws IOException, FontFormatException {
        SwingUtilities.invokeLater(ChildrenOfTime::showLoadingScreen);
//        childrenOfTime.startSinglePlayerMode();
    }

    public static void main(String[] args) {
        GameEngine gameEngine = new GameEngine();
        try {
            gameEngine.startGame();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FontFormatException e) {
            e.printStackTrace();
        }
    }

    public static void stopThemeMusic() {

    }


    public static void playThemeMusic() {
        if (!GameEngine.themeMusiceIsPlaying && musicPlayBackAllowed) {
            GameEngine.themeMusiceIsPlaying = true;
            try {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
                        new File("src/ui/music/theme_music.wav").getAbsoluteFile());
                themeMusicClip = AudioSystem.getClip();
                themeMusicClip.open(audioInputStream);
                themeMusicClip.loop(20);
                themeMusicClip.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
