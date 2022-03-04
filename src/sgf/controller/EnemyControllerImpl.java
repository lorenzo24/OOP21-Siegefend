package sgf.controller;
import java.util.List;

import sgf.controller.map.MapController;
import sgf.model.Enemy;
import sgf.model.EnemyImpl;
import sgf.model.EnemyType;
import sgf.view.EnemyView;

/**
 * Class Enemy thread.
 */
public class EnemyControllerImpl implements EnemyController {

    private final List<Enemy> enemyList;
    private static final int CELL_SIZE = 80;
    private boolean isControllerSet;
    private final MapController mapController;
    private EnemyView enemyView;

    /**
     * 
     * @param enemylist
     * @param mapController
     */
    public EnemyControllerImpl(final List<Enemy> enemylist, final MapController mapController) {
        this.enemyList = enemylist;
        this.mapController = mapController;
        enemylist.add(new EnemyImpl.Builder(0, EnemyType.TANK)
                .position(mapController.convertAGridPosition(mapController.getMap().getStartTile())) // prendere direttamente la posizione iniziale in map controller.
                .hp(100)
                .speed(100)
                .build());
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
