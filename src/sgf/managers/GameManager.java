package sgf.managers;

import sgf.controller.game.PlayerController;
import sgf.model.level.Level;
import sgf.model.level.Wave;

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
}
