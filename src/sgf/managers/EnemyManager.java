package sgf.managers;

import sgf.model.enemies.Enemy;

/**
 * Rapresents a single enemy.
 */
public interface EnemyManager {

    /**
     * Returns the enemy.
     * @return the enemy.
     */
    Enemy getEnemy();

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
