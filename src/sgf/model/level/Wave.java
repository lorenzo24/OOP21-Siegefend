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
}
