package sgf.model.game;

/**
 * Represents an element with threads that can be paused.
 */
public interface Pausable {

    /**
     * Pauses all threads that can be stopped within the element.
     */
    void pause();

    /**
     * Resumes all threads that can be stopped within the element.
     */
    void resume();
}
