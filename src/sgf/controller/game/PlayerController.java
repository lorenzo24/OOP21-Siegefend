package sgf.controller.game;

import sgf.controller.Controller;
import sgf.model.game.Player;
import sgf.view.game.PlayerView;

/**
 * Manages the player.
 */
public interface PlayerController extends Controller<PlayerView> {

    /**
     * Returns the {@link Player} associated.
     * @return the player associated
     */
    Player getPlayer();

    /**
     * Changes the player's health by an offset value.
     * @param offset the change in health
     */
    void changeHP(int offset);

    /**
     * Changes the player's money amount by an offset value.
     * @param offset the change in money amount
     */
    void changeMoney(int offset);

    /**
     * Changes the player's score by an offset value.
     * @param offset the change in score
     */
    void changeScore(int offset);
}
