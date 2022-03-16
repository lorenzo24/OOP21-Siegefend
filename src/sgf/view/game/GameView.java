package sgf.view.game;

import sgf.controller.game.GameController;
import sgf.view.View;

/**
 * 
 */
public interface GameView extends View<GameController> {

    /**
     * Updates the view of the game.
     */
    void update();
}
