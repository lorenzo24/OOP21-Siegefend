package sgf.managers;

import java.util.List;
import java.util.Optional;

import sgf.model.enemies.Enemy;
import sgf.model.level.Level;
import sgf.model.level.Wave;
import sgf.model.map.Map;

/**
 * Handles levels and their continuation.
 */
public interface LevelManager {

    /**
     * Returns a list containing all the waves in the level.
     * @return the list of level's wawes.
     */
    List<Wave> getWaves();

    /**
     * Returns the map of the level.
     * @return the level's map.
     */
    Map getMap();

    /**
     * Returns the total number of waves in the level.
     * @return the level's total number of waves.
     */
    int getTotalWaves();

    /**
     * Returns the {@link Level} currently being played.
     * @return the current level.
     */
    Level getCurrentLevel();

    /**
     * Returns the current {@link Wave} that is being played.
     * @return the current wave.
     */
    Wave getCurrentWave();

    /**
     * Checks if there is a following {@link Wave} and sets it as active.
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
     * Checks if there is a following {@link Wave} in the level.
     * @return {@code true} if a wave is present, {@code false} otherwise
     */
    boolean hasNextEnemy();
}
