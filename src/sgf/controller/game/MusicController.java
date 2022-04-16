package sgf.controller.game;
/**
 * Interface for the music controller.
 */
public interface MusicController {

    /**
     * Method that puts the music on.
     */
    void musicOn();

    /**
     * Method that puts the music off.
     */
    void musicOff();

    /**
     * Method that checks if any music is playing.
     * @return {@code true} if music is playing, {@code false} otherwise
     */
    boolean isMusicPlaying();

}
