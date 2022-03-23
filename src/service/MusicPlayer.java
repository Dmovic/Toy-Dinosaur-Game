package service;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MusicPlayer implements Runnable {
    File soundFile;
    Thread thread;
    boolean circulate;

    public MusicPlayer(String filePath, boolean circulate) throws FileNotFoundException {
        this.circulate = circulate;
        soundFile = new File(filePath);
        if (!soundFile.exists()) {
            throw new FileNotFoundException(filePath + " not found! ");
        }
    }

    @Override
    public void run() {
        byte[] auBuffer = new byte[1024 * 128];
        do {
            AudioInputStream audioInputStream = null;   // audio input stream
            SourceDataLine auline = null;   // fixed source data line
            try {
                // get audio input from local file
                audioInputStream = AudioSystem.getAudioInputStream(soundFile);
                AudioFormat format = audioInputStream.getFormat();  // get audio format

                DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

                auline = (SourceDataLine) AudioSystem.getLine(info);
                auline.open(format);
                auline.start();
                int byteCount = 0;
                while (byteCount != -1) {
                    byteCount = audioInputStream.read(auBuffer, 0, auBuffer.length);
                    if (byteCount >= 0) {
                        auline.write(auBuffer, 0, byteCount);
                    }
                }
            } catch (UnsupportedAudioFileException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            } finally {
                auline.drain();
                auline.close();
            }
        } while (circulate);
    }

    public void play() {
        thread = new Thread(this);
        thread.start();
    }

    public void stop() {
        thread.stop();
    }
}
