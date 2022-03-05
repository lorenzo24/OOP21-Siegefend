package sgf.controller;
import java.util.ArrayList;
import java.util.List;

import sgf.controller.map.MapController;
import sgf.model.Enemy;
import sgf.model.EnemyImpl;
import sgf.model.EnemyType;
import sgf.utilities.EnemyManager;
import sgf.utilities.EnemyManagerImpl;
import sgf.view.EnemyView;

/**
 * Class Enemy thread.
 */
public class EnemyControllerImpl implements EnemyController {

    private boolean isControllerSet;
    private EnemyView enemyView;
    private List<Enemy> enemyList;
    private final EnemyManager enemyManager;

    /**
     * 
     * @param mapController
     */
    public EnemyControllerImpl(final MapController mapController) {
        this.enemyList = new ArrayList<>();
        enemyList.add(new EnemyImpl.Builder(0, EnemyType.TANK)
                .position(mapController.convertToPosition(mapController.getMap().getStartTile())) // prendere direttamente la posizione iniziale in map controller.
                .hp(100)
                .speed(1)
                .build());
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
