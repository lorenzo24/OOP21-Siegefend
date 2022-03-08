package sgf.controller;
import java.util.ArrayList;
import java.util.List;
import sgf.controller.map.MapController;
import sgf.model.enemies.Enemy;
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
    private EnemyView enemyView;
    private final LevelManager levelManager;
    private final List<EnemyManager> managerList;

    /**
     * 
     * @param levelManager
     */
    public EnemyControllerImpl(final LevelManager levelManager) {
        this.levelManager = levelManager;
        this.levelManager.nextWave();
        this.managerList = new ArrayList<>();
        this.startRunWaves();
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
                        checkIfStopThread();
                    }
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

    private void checkIfStopThread() {
        if (!this.levelManager.hasNextWave() && this.managerList.isEmpty()) {
            this.threadRun = false;
        }
    }

    private void loadNextWave() {
        if (this.managerList.isEmpty() && this.levelManager.hasNextWave()) {
            this.levelManager.nextWave();
            this.loadNextEnemy();
        }
    }

    private void loadNextEnemy() {
        final Enemy enemy = this.levelManager.getNextEnemy().orElseThrow();
        this.managerList.add(new EnemyManagerImpl(enemy, this.levelManager, this));
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
