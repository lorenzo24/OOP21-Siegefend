package sgf.view.enemy;

import java.util.List;

import sgf.controller.enemy.EnemyController;
import sgf.managers.EnemyManager;
import sgf.view.View;

/**
 * Enemy view.
 */
public interface EnemyView extends View<EnemyController> {

    /**
     * Sets the list with the new group of enemy.
     * @param enemies The new list of enemies Manager.
     */
    void setList(List<EnemyManager> enemies);
}
