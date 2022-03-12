package sgf.controller.enemy;

import sgf.controller.Controller;
import sgf.managers.EnemyManager;
import sgf.view.enemy.EnemyView;

/**
 * This controls all the waves and the movement of the enemies.
 */
public interface EnemyController extends Controller<EnemyView> {

    /**
     * Stops the waves thread.
     */
    void stopThread();

    /**
     * Restarts the waves thread.
     */
    void resumeThread();

    /**
     * Removes the enemyManager from the list of a wave.
     * @param enemyManager is the element of the list that has to be removed.
     */
    void removeEnemy(EnemyManager enemyManager);
}
