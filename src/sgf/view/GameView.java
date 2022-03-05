package sgf.view;

import sgf.controller.thread.GameController;

/**
 * 
 */
public interface GameView extends View<GameController> {

    /**
     * Updates the view of the game.
     */
    void update();
}
