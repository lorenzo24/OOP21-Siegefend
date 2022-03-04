package sgf.utilities;
import sgf.controller.map.MapController;
import sgf.model.Enemy;

/**
 *
 */
public class EnemyManagerImpl implements EnemyManager {
    private static final int CELL_SIZE = 80;
    private final Enemy enemy;
    private final MapController mapController;

    /**
     * 
     * @param enemylist
     * @param mapController
     */
    public EnemyManagerImpl(final Enemy enemy, final MapController mapController) {
        this.enemy = enemy;
        this.mapController = mapController;
        this.startEnemyThread();
    }

    private void startEnemyThread() {
        final Thread gameThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        nextMovement();
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        gameThread.start();
    }
    
    private void nextMovement() {
        System.out.println(mapController.convertToGridPosition(enemy.getPosition()));        
    }
}
