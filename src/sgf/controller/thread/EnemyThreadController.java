package sgf.controller.thread;
import java.util.List;
import sgf.model.Enemy;

/**
 * Class Enemy thread.
 */
public class EnemyThreadController {

    private final List<Enemy> enemyList;

    /**
     * 
     * @param enemylist
     */
    public EnemyThreadController(final List<Enemy> enemylist) {
        this.enemyList = enemylist;
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
}
