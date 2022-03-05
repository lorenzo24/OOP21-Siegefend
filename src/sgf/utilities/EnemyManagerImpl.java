package sgf.utilities;
import java.util.Optional;

import sgf.controller.map.MapController;
import sgf.model.Direction;
import sgf.model.Enemy;
import sgf.model.GridPosition;
import sgf.model.Direction;

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
        GridPosition p = mapController.convertToGridPosition(this.enemy.getPosition());
        Optional<Direction> d = mapController.getMap().getTiles().get(p).getTileDirection();
        System.out.println(d);
        this.movement(d);
    }

    private void movement(final Optional<Direction> d) {
        if (d.isEmpty()) {
            throw new IllegalStateException();
        }
        
        switch (d.get()) {
        case UP :
            enemy.move(enemy.getPosition().getX(), enemy.getPosition().getY() - 1);
            break;
        case DOWN :
            enemy.move(enemy.getPosition().getX(), enemy.getPosition().getY() + 1);
            break;
        case LEFT :
            enemy.move(enemy.getPosition().getX() - 1, enemy.getPosition().getY());
            break;
        default:
            enemy.move(enemy.getPosition().getX() + 1, enemy.getPosition().getY());
            break;
        }
    }

}
