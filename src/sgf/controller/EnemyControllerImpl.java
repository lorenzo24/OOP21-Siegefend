package sgf.controller;
import java.util.ArrayList;
import java.util.List;

import sgf.controller.map.MapController;
import sgf.model.Enemy;
import sgf.utilities.EnemyManager;
import sgf.utilities.EnemyManagerImpl;
import sgf.utilities.LevelManager;
import sgf.view.EnemyView;

/**
 * Class Waves thread that spone enemies of the waves.
 */
public class EnemyControllerImpl implements EnemyController {
    private boolean isControllerSet;
    private volatile boolean threadRun = true; // Boolean that manages the thread loop.
    private final MapController mapController;
    private EnemyView enemyView;
    private final List<Enemy> enemyList;
    private final LevelManager levelManager;
    private final List<EnemyManager> managerList;

    /**
     * 
     * @param levelManager
     * @param mapController
     */
    public EnemyControllerImpl(final LevelManager levelManager, final MapController mapController) {
        this.levelManager = levelManager;
        this.levelManager.getNextWave();
        this.enemyList = new ArrayList<>();
        this.managerList = new ArrayList<>();
        this.mapController = mapController;
        this.startRunWaves();
    }

    private void startRunWaves() {
        final Thread waveThread = new Thread(new Runnable() {

            @Override
            public void run() {
                while (threadRun) {
                    if (levelManager.hasNextEnemy()) {
                        final Enemy enemy = levelManager.getNextEnemy().get();
                        enemyList.add(enemy);
                        managerList.add(new EnemyManagerImpl(enemy, mapController));
                    } else {
                        if (levelManager.hasNextWave()) {
                            levelManager.getNextWave();
                        }
                        if (enemyList.isEmpty()) {
                            threadRun = false;
                        }
                    }
                    checkFinish();
                    try {
                        Thread.sleep(4000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        waveThread.start();
    }

    @Override
    public void setView(final EnemyView view) {
        if (!isControllerSet) {
            this.isControllerSet = true;
            this.enemyView = view;
            this.enemyView.setList(this.enemyList);
        }
    }

    // TODO CHiedi a bertu come migliorare.
    private void checkFinish() {
        final List<Enemy> list = new ArrayList<>();
        for (final Enemy enemy : this.enemyList) {
            if (enemy.isWin()) {
                list.add(enemy);
            }
        }
        this.enemyList.removeAll(list);

        final List<EnemyManager> list2 = new ArrayList<>();
        for (final EnemyManager enemy : this.managerList) {
            if (enemy.isWin()) {
                list2.add(enemy);
            }
        }
        this.managerList.removeAll(list2);
    }

    @Override
    public void stopThread() {
        this.threadRun = false;
    }

    @Override
    public void resumeThread() {
        this.threadRun = true;
    }
}
