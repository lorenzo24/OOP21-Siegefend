package sgf.view.enemy;

import sgf.controller.enemy.EnemyController;
import sgf.view.View;

/**
 * Enemy view.
 */
public interface EnemyView extends View<EnemyController> {

    /**
     * Informs player that wins the game.
     */
    void winGame();
}
