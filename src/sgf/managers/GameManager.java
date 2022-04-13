package sgf.managers;

import sgf.controller.game.PlayerController;
import sgf.model.game.GameStatus;
import sgf.model.game.Pausable;
import sgf.model.level.Level;
import sgf.model.level.Wave;
import sgf.model.turret.Turret;

/**
 * Handles the coordination of the various game components.
 */
public interface GameManager {

    /**
     * Returns the associated {@link PlayerController}.
     * @return the associated {@code PlayerController}
     */
    PlayerController getPlayerController();

    /**
     * Returns the manager for the current level.
     * @return a {@link LevelManager}
     */
    LevelManager getLevelManager();

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
     * Adds a {@link Pausable} to a collection of elements for pausing and resuming.
     * @param p the {@code Pausable} object
     */
    void register(Pausable p);

    /**
     * Removes the {@link Pausable} to a collection of elements for pausing and resuming.
     * @param p the {@code Pausable} object
     */
    void deregister(Pausable p);

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
