package sgf.controller;

import sgf.view.EnemyView;

/**
 * This, controll all the waves and the movement of the enemies.
 */
public interface EnemyController extends Controller<EnemyView> {

    /**
     *  Stop the thread of the waves.
     */
    void stopThread();

    /**
     * Restart the thread of the waves.
     */
    void resumeThread();
}
