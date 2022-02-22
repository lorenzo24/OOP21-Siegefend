package sgf.controller;

import javax.swing.JPanel;

import sgf.model.Enemy;
import sgf.model.EnemyImpl;
import sgf.model.Position;
import sgf.view.EnemyView;
import sgf.view.MapCreator;

/**
 *
 */
public class EnemyController {
    private static final int SLEEP_TIME = 10;
    private volatile boolean threadRun = true;
    private EnemyView enemyView;

    private Enemy enemy = new EnemyImpl(0, new Position(50, 50), 100, 0, 0);

    /**
     * 
     * @param pannel
     */
    public EnemyController(final MapCreator pannel) {
        this.enemyView = new EnemyView(pannel);
        this.startEnemyThread();
    }

    private void startEnemyThread() {
        final Thread enemyThread = new Thread(new Runnable() {
            @Override
            public void run() {
                int ups = 0;
                long lastTime = System.currentTimeMillis();
                while (threadRun) {
                    // Print how many update has been done in 1 second.
                    if (System.currentTimeMillis() - lastTime >= 1000) {
                        enemyView.drawEnemy(enemy);
                        enemy.setPosition(new Position(enemy.getPosition().getX()+1, enemy.getPosition().getY()+1 ));
                        ups = 0;
                        lastTime = System.currentTimeMillis();
                    }
                    try {
                        Thread.sleep(SLEEP_TIME);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        enemyThread.start();
    }

    /**
     * Temporary stops the thread.
     */
    public void pauseMapThread() {
        this.threadRun = false;
    }

    /**
     * Resume the thread.
     */
    public void resumeMapThread() {
        this.threadRun = true;
    }
}
