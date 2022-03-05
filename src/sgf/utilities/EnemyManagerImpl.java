package sgf.utilities;
import java.util.Optional;

import sgf.controller.map.MapController;
import sgf.model.Direction;
import sgf.model.Enemy;
import sgf.model.ImgTileSize;
import sgf.model.GridPosition;
import sgf.model.Position;

/**
 * Class that manage each single enemy.
 */
public class EnemyManagerImpl implements EnemyManager {
    private final int imgSize = ImgTileSize.getTileSize();
    private volatile boolean threadRun = true; // Boolean that manages the thread loop.
    private final Enemy enemy;
    private final MapController mapController;
    private int stepsDone;
    private Optional<Direction> lastDir = Optional.empty();

    /**
     * Create an managerImpl that controll the movement of the enemy.
     * @param enemy a single enemy.
     * @param mapController the controller of the map that say what is the direction.
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
                while (threadRun) {
                    try {
                        nextMovement();
                        checkFinalDestination();
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        gameThread.start();
    }

    private void checkFinalDestination() {
        final double x = this.enemy.getPosition().getX();
        final double y = this.enemy.getPosition().getY();
        if (x == -imgSize || y == -imgSize || this.endIntoMap(x)  || this.endIntoMap(y)) {
            this.threadRun = false;
        }
    }

    private boolean endIntoMap(final double v) {
        return this.mapController.getMap().getMapSize() * imgSize == v; 
    }

    private void nextMovement() {
        if (this.initialPart()) {
            this.takeDirection();
        }
        stepsDone += this.enemy.getSpeed();
        if (stepsDone == imgSize) {
            stepsDone = 0;
        }
        this.movement(lastDir);
    }

    private boolean initialPart() {
        return stepsDone == 0;
    }

    private void takeDirection() {
        final GridPosition p = mapController.convertToGridPosition(this.enemy.getPosition());
        final Optional<Direction> d = mapController.getMap().getTiles().get(p).getTileDirection();
        this.lastDir = d;
    }

    private void movement(final Optional<Direction> d) {
        if (d.isEmpty()) {
            throw new IllegalStateException("Enemy position is empty");
        }
        this.enemyMovement(d.get());
    }

    private void enemyMovement(final Direction dir) {
        final Position p =  this.enemy.getPosition();
        final double speed = this.enemy.getSpeed();
        switch (dir) {
        case UP :
            enemy.move(p.getX(), p.getY() - speed);
            break;
        case DOWN :
            enemy.move(p.getX(), p.getY() + speed);
            break;
        case LEFT :
            enemy.move(p.getX() - speed, p.getY());
            break;
        case RIGHT:
            enemy.move(p.getX() + speed, p.getY());
            break;
        default:
            throw new IllegalArgumentException("Enemy direction not correct");
        }
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
