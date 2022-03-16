package sgf.model.level;

import java.util.List;

import sgf.model.enemies.Enemy;

/**
 * Representation of a group of {@link Enemy} objects.
 */
public interface Wave {

    /** 
     * @return A list containing all the enemies in the wave.
     */
    List<Enemy> getEnemies();

    /**
     * @return The number of remaining enemies in the wave. 
     */
    int getRemainingEnemies();

    /**
     * Decreases the number of enemies that remain in the wave.
     */
    void decreaseEnemiesCount();

    /**
     * @return true if there are no more enemies left in the wave, false otherwise.
     */
    boolean isWaveOver(); 
}
