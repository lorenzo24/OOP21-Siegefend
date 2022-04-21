package sgf.view.game;

import sgf.controller.game.GameController;
import sgf.view.View;

/**
 * View of the game.
 */
public interface GameView extends View<GameController> {

    /**
     * Updates the view of the game.
     */
    void update();
}
