package sgf.managers;

import sgf.model.enemies.Enemy;

/**
 * Represents a single enemy.
 */
public interface EnemyManager {

    /**
     * Returns the enemy.
     * @return the enemy.
     */
    Enemy getEnemy();

    /**
     * Deletes the enemy in the list of the enemyController.
     */
    void disappear();

    /**
     * Stop the thread.
     */
    void stopThread();
}
