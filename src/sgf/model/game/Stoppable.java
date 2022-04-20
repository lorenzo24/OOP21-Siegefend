package sgf.model.game;

/**
 * This interface is implemented by all views and manager. We want them to be stoppable in order to stop them before exit game.
 */
public interface Stoppable {
    /**
     * Method that stops thread and view, called just before exiting the game.
     */
    void stop();
}
