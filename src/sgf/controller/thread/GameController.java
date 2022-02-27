package sgf.controller.thread;

import sgf.controller.Controller;
import sgf.view.GameView;

/**
 * This interface manages the game thread.
 */
public interface GameController extends Controller<GameView> {

    /**
     * Stops the thread.
     */
    void stopThread();

    /**
     * Restarts the thread.
     */
    void resumeThread();

    /**
     * Sets the view associated to this controller. Can only be called once.
     * @param gv the {@link GameView}
     */
    void setView(GameView gv);

}
