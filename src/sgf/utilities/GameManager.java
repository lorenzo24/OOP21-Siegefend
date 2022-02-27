package sgf.utilities;

import sgf.model.GameStatus;
import sgf.model.Level;
import sgf.model.Turret;
import sgf.model.Wave;

/**
 * Handles the coordination of the various game components.
 */
public interface GameManager {
    /**
     * Starts the {@link Level} to play.
     * @param l the level to play
     */
    void startLevel(Level l);

    /**
     * Returns the {@link Level} that is being played.
     * @return the level being played
     */
    Level getCurrentLevel();

    /**
     * Returns the {@link Wave} that is being executed.
     * @return the wave being executed
     */
    Wave getCurrentWave();

    /**
     * Checks whether the game is paused or not.
     * @return {@code true} if the game is paused, {@code false} otherwise
     */
    boolean isPaused();

    /**
     * Pauses the game.
     */
    void pause();

    /**
     * Resumes the game.
     */
    void unpause();

    /**
     * Returns the status of the game that is being played.
     * @return the {@link GameStatus} of the game
     */
    GameStatus getCurrentStatus();

    /**
     * Purchases a turret and updates the player's money count.
     * @param t The turret to purchase
     * @return {@code true} if the purchase is successful, {@code false} otherwise
     */
    boolean purchase(Turret t);

    /**
     * Purchases an upgrade for the given turret and updates the player's money count.
     * @param t The turret to upgrade
     * @return {@code true} if the purchase is successful, {@code false} otherwise
     */
    boolean purchaseUpgrade(Turret t);
}
