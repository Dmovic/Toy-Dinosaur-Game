package service;

import java.io.FileNotFoundException;

public class Sound {
    public static final String DIR = "music/";
    public static final String BACKGROUND = "background.wav";
    public static final String JUMP = "jump.wav";
    public static final String HIT = "hit.wav";

    /**
     * play sound
     * @param file name of music file
     * @param circulate if loop
     */
    private static void play(String file, boolean circulate) {
        try {
            MusicPlayer player = new MusicPlayer(file, circulate);
            player.play();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void jump() {
        play(DIR + JUMP, false);
    }

    public static void hit() {
        play(DIR + HIT, false);
    }

    public static void background() {
        play(DIR + BACKGROUND, true);
    }
}
