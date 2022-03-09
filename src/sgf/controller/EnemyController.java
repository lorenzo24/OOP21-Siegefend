package sgf.controller;

import sgf.utilities.EnemyManager;
import sgf.view.EnemyView;

/**
 * This, controll all the waves and the movement of the enemies.
 */
public interface EnemyController extends Controller<EnemyView> {

    /**
     *  Stops the thread of the waves.
     */
    void stopThread();

    /**
     * Restarts the thread of the waves.
     */
    void resumeThread();
    /**
     * Removes the enmyManager from the list of one wave.
     * @param enemyManager is the element of the list that has to be remove.
     */
    void removeEnemy(EnemyManager enemyManager);
}
