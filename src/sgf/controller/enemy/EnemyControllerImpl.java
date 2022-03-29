package sgf.controller.enemy;

import java.util.ArrayList;
import java.util.List;
import sgf.managers.EnemyManager;
import sgf.managers.EnemyManagerImpl;
import sgf.managers.LevelManager;
import sgf.model.enemies.Enemy;
import sgf.model.enemies.LockClass;
import sgf.view.enemy.EnemyView;

/**
 * Class Waves thread that spawns enemies of the waves.
 */
public class EnemyControllerImpl implements EnemyController {
    private static final int THREAD_SPEED = 3000; 
    private boolean isControllerSet;
    private volatile boolean threadRun = true; // Boolean that manages the thread loop.
    private EnemyView enemyView;
    private final LevelManager levelManager;
    private final List<EnemyManager> managerList; // List of enemyyManager of enemy that is moving in the game.

    /**
     * Sets the levelManager to load enemies and get map.
     * @param levelManager Is the manager of the current level.
     */
    public EnemyControllerImpl(final LevelManager levelManager) {
        this.levelManager = levelManager;
        this.managerList = new ArrayList<>();
        this.startRunWaves(); // Thread method.
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
                        checkIfStopThread(); // Checks if the level is over.
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

    // Checks if the level is finished.
    private void checkIfStopThread() {
        if (!this.levelManager.hasNextWave() && this.managerList.isEmpty()) {
            this.threadRun = false;
        }
    }

    private void loadNextWave() {
        // Checks if the previous wave is over and in case loads the next wave.
        if (this.managerList.isEmpty() && this.levelManager.hasNextWave()) {
            this.levelManager.nextWave();
            this.loadNextEnemy();
        }
    }

    private void loadNextEnemy() {
        final Enemy enemy = this.levelManager.getNextEnemy().orElseThrow();
        this.managerList.add(new EnemyManagerImpl(enemy, this.levelManager, this)); // Creates a managerList of the enemy that has been cretaed.
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
        LockClass.getSemaphore().acquireUninterruptibly();
        this.managerList.remove(enemyManager);
        LockClass.getSemaphore().release();
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
