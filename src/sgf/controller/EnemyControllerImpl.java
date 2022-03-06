package sgf.controller;
import java.util.List;

import sgf.controller.map.MapController;
import sgf.model.Enemy;
import sgf.utilities.EnemyManager;
import sgf.utilities.EnemyManagerImpl;
import sgf.utilities.LevelManager;
import sgf.view.EnemyView;

/**
 * Class Enemy thread.
 */
public class EnemyControllerImpl implements EnemyController {

    private boolean isControllerSet;
    private EnemyView enemyView;
    private List<Enemy> enemyList;
    private final EnemyManager enemyManager;
    private final LevelManager levelManager;

    /**
     * 
     * @param mapController
     * @param levelManager
     */
    public EnemyControllerImpl(final MapController mapController, final LevelManager levelManager) {
        this.levelManager = levelManager;
        this.enemyManager = new EnemyManagerImpl(this.enemyList.get(0), mapController);
    }

    @Override
    public void setView(final EnemyView view) {
        if (!isControllerSet) {
            this.isControllerSet = true;
            this.enemyView = view;
            this.enemyView.setList(this.enemyList);
        }
    }
}
