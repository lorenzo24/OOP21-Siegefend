package sgf.utilities;

import java.util.List;
import java.util.Optional;

import sgf.model.Level;
import sgf.model.Map;
import sgf.model.Wave;
import sgf.model.enemies.Enemy;

/**
 * Handles levels and their continuation.
 */
public interface LevelManager {

    /**
     * Returns a list containing all the waves in the level.
     * @return a list containing all the waves in the level
     */
    List<Wave> getWaves();

    /**
     * Return the map of the level.
     * @return the map of the level.
     */
    Map getMap();

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
    /*int getCurrentWaveNumber();*/

    /**
     * Checks if there is a next {@link Wave} and sets it as active.
     */
    void nextWave();

    /**
     * Checks if there is a next {@link Wave} in the level.
     * @return {@code true} if a wave is present, {@code false} otherwise
     */
    boolean hasNextWave();


    /**
     * Triggers the spawning of a new {@link Enemy}, using the informations in the current wave.
     * @return the next enemy.
     */
    Optional<Enemy> getNextEnemy();

    /**
     * Checks if there is a next {@link Wave} in the level.
     * @return {@code true} if a wave is present, {@code false} otherwise
     */
    boolean hasNextEnemy();
}
