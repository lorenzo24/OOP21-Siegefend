package sgf.view.game;

import sgf.controller.game.PlayerController;
import sgf.view.View;

/**
 * 
 *
 */
public interface PlayerView extends View<PlayerController> {
    /**
     * 
     */
    void update();

    /**
     * Close game when the player loses. 
     */
    void loseGame();
}
