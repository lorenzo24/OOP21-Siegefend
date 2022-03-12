package sgf.controller.enemy;
import java.util.ArrayList;
import java.util.List;

import sgf.managers.EnemyManager;
import sgf.managers.EnemyManagerImpl;
import sgf.managers.LevelManager;
import sgf.model.enemies.Enemy;
import sgf.view.enemy.EnemyView;

/**
 * Class Waves thread that spawns waves enemies.
 */
public class EnemyControllerImpl implements EnemyController {
    private static final int THREAD_SPEED = 3000; 
    private boolean isControllerSet;
    private volatile boolean threadRun = true; // Boolean that manages the thread loop.
    private EnemyView enemyView;
    private final LevelManager levelManager;
    private final List<EnemyManager> managerList; // List of eenmyManager of enemy that are moving in the game.

    /**
     * Set the levelManager to loads enemies and gets map.
     * @param levelManager Is the manager of the actual level.
     */
    public EnemyControllerImpl(final LevelManager levelManager) {
        this.levelManager = levelManager;
        this.managerList = new ArrayList<>();
        this.startRunWaves(); // Methods of the thread.
    }

    private void startRunWaves() {
        final Thread waveThread = new Thread(new Runnable() {

            @Override
            public void run() {
                while (threadRun) {
                    if (levelManager.hasNextEnemy()) {
                        loadNextEnemy();
                    } else {
                        loadNextWave();
                        checkIfStopThread(); // Control if the level is finished.
                    }
                    try {
                        Thread.sleep(THREAD_SPEED);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        waveThread.start();
    }

    // Control if the level is finished.
    private void checkIfStopThread() {
        if (!this.levelManager.hasNextWave() && this.managerList.isEmpty()) {
            this.threadRun = false;
        }
    }

    private void loadNextWave() {
        // Control if the previus wave is finished and load the next wave.
        if (this.managerList.isEmpty() && this.levelManager.hasNextWave()) {
            this.levelManager.nextWave();
            this.loadNextEnemy();
        }
    }

    private void loadNextEnemy() {
        final Enemy enemy = this.levelManager.getNextEnemy().orElseThrow();
        this.managerList.add(new EnemyManagerImpl(enemy, this.levelManager, this)); // Create an managerList of the enemy that has been cretaed.
    }

    @Override
    public void setView(final EnemyView view) {
        if (!isControllerSet) {
            this.isControllerSet = true;
            this.enemyView = view;
            this.enemyView.setList(this.managerList);
        }
    }

    @Override
    public void removeEnemy(final EnemyManager enemyManager) {
        this.managerList.remove(enemyManager);
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
