package sgf.controller.game;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Controller for the music that manages the tracks random choice.
 */
public class MusicControllerImpl implements MusicController {
    private static final int SONGS_NUMBER = 3;
    private static final int  DURATION = 3000;
    private boolean musicOn;
    private Clip c;

    /**
     * Constructor that makes the sound beginning with first sound. 
     */
    public MusicControllerImpl() {
        this.musicOn = true;
        this.play("sound1");
        this.thread();
    }

    @Override
    public void musicOn() {
        this.c.start();
        this.musicOn = true;
    }

    @Override
    public void musicOff() {
        this.musicOn = false;
        this.c.stop();
    }

    // This method open an audio stream and start the music file.
    private void play(final String fileName) {
        final String musicFile = "res" + File.separator + fileName + ".wav";
        final File file = new File(musicFile);
            try (AudioInputStream a = AudioSystem.getAudioInputStream(file)) {
                c = AudioSystem.getClip();
                c.open(a);
                c.start();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
            }
    }

    // Thread that plays and changes music while music is on.
    private void thread() {
        final Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (musicOn) {
                    if (!c.isRunning()) {
                        changeMusic();
                    }
                    try {
                        Thread.sleep(DURATION);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t.start();
    }

    private void changeMusic() {
        final Random r = new Random();
        this.play("sound" + (r.nextInt(SONGS_NUMBER) + 1));
    }
}
