package sgf.controller;

import java.util.List;
import java.util.Optional;

import sgf.model.Enemy;
import sgf.model.Level;
import sgf.model.Wave;

/**
 * Handles levels and their continuation.
 */
public interface LevelManager {
    /**
     * Starts the game using the given {@link Level}.
     * @param l the level to start
     */
    void startLevel(Level l);

    /**
     * Returns a list containing all the waves in the level.
     * @return a list containing all the waves in the level
     */
    List<Wave> getWaves();

    /**
     * Returns the total number of waves in the level.
     * @return the total number of waves in the level
     */
    int getTotalWaves();

    /**
     * Returns the {@link Level} currently being played.
     * @return the level currently being played
     */
    Level getCurrentLevel();

    /**
     * Returns the current {@link Wave} that is being played.
     * @return the current wave that is being played
     */
    Wave getCurrentWave();

    /**
     * Returns the number of the wave currently being played.
     * @return the number of the wave currently being played
     */
    int getCurrentWaveNumber();

    /**
     * Checks if there is a next {@link Wave} and returns it.
     * @return an {@code Optional} containing the next wave if present, {@code Optional.empty()} otherwise
     */
    Optional<Wave> getNextWave();

    /**
     * Checks if there is a next {@link Wave} in the level.
     * @return {@code true} if a wave is present, {@code false} otherwise
     */
    boolean hasNextWave();

    /**
     * Triggers the spawning of a new {@link Enemy}, using the informations in the current wave.
     */
    void spawnNextEnemy();

    /**
     * Removes an enemy from the field.
     * @param e the enemy to remove
     */
    void removeEnemy(Enemy e);

    // spawnNextEnemy and removeEnemy may not be needed here, but rather the GameController can ask for the next
    // enemy to spawn and handle the rest.
    // Optional<Enemy> getNextEnemy();
}
