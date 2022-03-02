package sgf.controller;
import java.util.List;

import sgf.controller.map.MapController;
import sgf.model.Enemy;
import sgf.model.EnemyImpl;
import sgf.model.EnemyType;
import sgf.model.GridPosition;
import sgf.model.Map;
import sgf.model.Position;
import sgf.view.EnemyView;

/**
 * Class Enemy thread.
 */
public class EnemyControllerImpl implements EnemyController {

    private final List<Enemy> enemyList;
    private boolean isControllerSet;
    //private final MapController mapController;
    private EnemyView enemyView;

    /**
     * 
     * @param enemylist
     */
    public EnemyControllerImpl(final List<Enemy> enemylist) {
        //this.mapController = mapController;
        this.enemyList = enemylist;
        enemylist.add(new EnemyImpl(0, new Position(20, 50), 100, 100, EnemyType.TANK)); // TO DELETE.
        this.startEnemyThread();
    }

    private void startEnemyThread() {
        final Thread gameThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        for (final var enemy : enemyList) {
                            enemy.move();
                        }
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        gameThread.start();
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
