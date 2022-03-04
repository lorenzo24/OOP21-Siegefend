package sgf.controller;
import java.util.List;

import sgf.model.Enemy;
import sgf.model.EnemyImpl;
import sgf.model.EnemyType;
import sgf.model.GridPosition;
import sgf.model.Map;
import sgf.model.Position;
import sgf.utilities.MapLoader;
import sgf.view.EnemyView;

/**
 * Class Enemy thread.
 */
public class EnemyControllerImpl implements EnemyController {

    private final List<Enemy> enemyList;
    private static final int CELL_SIZE = 80;
    private boolean isControllerSet;
    private final MapLoader mapLoader;
    private EnemyView enemyView;

    /**
     * 
     * @param enemylist
     * @param mapLoader
     */
    public EnemyControllerImpl(final List<Enemy> enemylist, final MapLoader mapLoader) {
        this.enemyList = enemylist;
        this.mapLoader = mapLoader;
        enemylist.add(new EnemyImpl.Builder(0, this.initialPosition(mapLoader.getMap().getStartTile()), EnemyType.TANK)
                .hp(100)
                .speed(100)
                .build());
        this.startEnemyThread();
    }

    private Position initialPosition( final GridPosition p) {
    final Map map = mapLoader.getMap();
    return new Position(p.getColumn()*CELL_SIZE, p.getRow()*CELL_SIZE);
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
