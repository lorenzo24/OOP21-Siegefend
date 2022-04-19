package sgf.controller.enemy;

import java.util.ArrayList;
import java.util.List;

import sgf.controller.game.PlayerController;
import sgf.managers.EnemyManager;
import sgf.managers.EnemyManagerImpl;
import sgf.managers.GameManager;
import sgf.managers.LeaderboardManager;
import sgf.managers.LevelManager;
import sgf.model.enemies.Enemy;
import sgf.model.game.Player;
import sgf.utilities.LockClass;
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
    private final PlayerController playerManager;  //Manager of Player, needed by EnemyManager.
    private final LeaderboardManager leaderboard;
    private final GameManager gameManager;
    private Thread waveThread;

    /**
     * Sets the levelManager to load enemies and get map.
     * @param levelManager Is the manager of the current level.
     * @param playerManager Is the manager of the player.
     * @param leaderboard Is the leaderboard manager.
     * @param gameManager Is the game Manager.
     */
    public EnemyControllerImpl(final LevelManager levelManager, final GameManager gameManager, final PlayerController playerManager, final LeaderboardManager leaderboard) {
        this.leaderboard = leaderboard;
        this.levelManager = levelManager;
        this.playerManager = playerManager;
        this.gameManager = gameManager;
        this.managerList = new ArrayList<>();
        this.startRunWaves(); // Thread method.
    }

    private void startRunWaves() {
        if (waveThread == null) {
            waveThread = new Thread(new Runnable() {

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
        }
        waveThread.start();
    }

    // Checks if the level is finished.
    private void checkIfStopThread() {
        final Player player = this.playerManager.getPlayer();
        if (!this.levelManager.hasNextWave() && this.managerList.isEmpty() || player.getCurrentHP() == 0) {
            this.threadRun = false;
            this.leaderboard.addScore(player.getPlayerName(), player.getScore());
            this.leaderboard.writeScore();
            this.enemyView.winGame();
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
        this.managerList.add(new EnemyManagerImpl(enemy, this.levelManager, this, this.playerManager, this.gameManager)); // Creates a managerList of the enemy that has been cretaed.
    }

    @Override
    public void setView(final EnemyView view) {
        if (!isControllerSet) {
            this.isControllerSet = true;
            this.enemyView = view;
        }
    }

    @Override
    public void removeEnemy(final EnemyManager enemyManager) {
        LockClass.getEnemySemaphore().acquireUninterruptibly();
        this.managerList.remove(enemyManager);
        LockClass.getEnemySemaphore().release();
    }

    @Override
    public void stop() {
        this.threadRun = false;
    }

    @Override
    public List<EnemyManager> getManagerList() {
        return this.managerList;
    }
}
