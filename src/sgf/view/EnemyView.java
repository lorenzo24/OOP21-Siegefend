package sgf.view;

import java.util.List;
import sgf.controller.EnemyController;
import sgf.model.enemies.Enemy;
import sgf.utilities.EnemyManager;

/**
 * 
 */
public interface EnemyView extends View<EnemyController> {

    /**
     *  Set the list with the new group of enemy.
     * @param enemies new list of enemies Manager.
     */
    void setList(List<EnemyManager> enemies);
}
