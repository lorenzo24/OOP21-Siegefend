package sgf.managers;

import sgf.model.game.Player;

/**
 * Manages the player.
 */
public interface PlayerManager {

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
