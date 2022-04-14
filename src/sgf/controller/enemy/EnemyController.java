package sgf.controller.enemy;

import java.util.List;

import sgf.controller.Controller;
import sgf.managers.EnemyManager;
import sgf.view.enemy.EnemyView;

/**
 * This controls all the waves and the movement of the enemies.
 */
public interface EnemyController extends Controller<EnemyView> {

    /**
     * Removes the enemyManager from the list of a wave.
     * @param enemyManager Is the element of the list that has to be removed.
     */
    void removeEnemy(EnemyManager enemyManager);

    /**
     * @return
     */
    List<EnemyManager> getManagerList();
}
