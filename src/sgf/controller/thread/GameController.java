package sgf.controller.thread;

/**
 * This interface manages the game thread.
 */
public interface GameController {

    /**
     * Stops the thread.
     */
    void stopThread();

    /**
     * Restarts the thread.
     */
    void resumeThread();

}
