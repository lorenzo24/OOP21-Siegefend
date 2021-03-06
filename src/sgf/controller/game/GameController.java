package sgf.controller.game;

import sgf.controller.Controller;
import sgf.view.game.GameView;

/**
 * This interface manages the game thread.
 */
public interface GameController extends Controller<GameView> {

    /**
     * Stops the thread.
     */
    void pause();

    /**
     * Restarts the thread.
     */
    void resume();

    /**
     * Sets the view associated to this controller. Can only be called once.
     * @param gv the {@link GameView}
     */
    void setView(GameView gv);

}
