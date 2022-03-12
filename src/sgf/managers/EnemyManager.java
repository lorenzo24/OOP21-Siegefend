package sgf.managers;

import sgf.model.enemies.Enemy;

/**
 * Rapresent the single enemy.
 */
public interface EnemyManager {

    /**
     * Return the enemy.
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
     * Delete the enemy in the list of the enemyController.
     */
    void complete();
}
