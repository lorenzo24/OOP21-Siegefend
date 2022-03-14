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
     * Inflicts damage on the enemy.
     * @param damage the amount of inflicted damage
     */
    void damage(double damage);

    /**
     * Stops the thread.
     */
    void stopThread();

    /**
     * Restarts the thread.
     */
    void resumeThread();

    /**
     * Deletes the enemy in the list of the enemyController.
     */
    void complete();
}
