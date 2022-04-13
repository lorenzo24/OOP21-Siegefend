package sgf.model.game;

/**
 * Represents an element with threads that can be paused.
 */
public interface Pausable {

    /**
     * Stops all threads that can be stopped within the element.
     */
    void stop();

    /**
     * Resumes all threads that can be stopped within the element.
     */
    void resume();
}
