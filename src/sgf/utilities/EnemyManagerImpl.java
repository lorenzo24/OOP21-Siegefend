package sgf.utilities;
import java.util.Optional;
import sgf.controller.EnemyController;
import sgf.model.Direction;
import sgf.model.ImgTileSize;
import sgf.model.Map;
import sgf.model.GridPosition;
import sgf.model.Position;
import sgf.model.enemies.Enemy;

/**
 * Class that manage each single enemy.
 */
public class EnemyManagerImpl implements EnemyManager {
    private static final int ENEMY_SPEED = 10;
    private final int imgSize = ImgTileSize.getTileSize();
    private volatile boolean threadRun = true; // Boolean that manages the thread loop.
    private final Enemy enemy;
    private final Map map;
    private final EnemyController enemyController;
    private int stepsDone;
    private Optional<Direction> lastDir = Optional.empty();
    private final PositionConverter converter; // Converter the gridPosition to Position.

    /**
     * Create an managerImpl that controll the movement of the enemy.
     * @param enemy a single enemy.
     * @param levelManager that gives the map the direction in which the enemy has to move.
     * @param enemyController the controller of the enemies.
     */
    public EnemyManagerImpl(final Enemy enemy, final LevelManager levelManager, final EnemyController enemyController) {
        this.enemy = enemy;
        this.map = levelManager.getMap();
        this.enemyController = enemyController;
        this.converter = new PositionConverter(ImgTileSize.getTileSize());
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
                        Thread.sleep(ENEMY_SPEED);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        gameThread.start();
    }

    /**
     * Control if the sprite is over the screen, and in that case it call the complete methods. 
     */
    private void checkFinalDestination() {
        final double x = this.enemy.getPosition().getX();
        final double y = this.enemy.getPosition().getY();
        if (x == -imgSize || y == -imgSize || this.endIntoMap(x)  || this.endIntoMap(y)) { // Control if the sprite is over the screen.
            this.threadRun = false; // Stop the thread.
            this.complete();
        }
    }

    private boolean endIntoMap(final double v) {
        return this.map.getMapSize() * imgSize == v; // Control if the sprite is over the screen under and right.
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
        final GridPosition p = this.converter.convertToGridPosition(this.enemy.getPosition());
        final Optional<Direction> d = this.map.getTiles().get(p).getTileDirection();
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
        final Pair<Integer, Integer> vec = dir.toUnitVector();
        enemy.move(p.getX() + vec.getX() * speed, p.getY() + vec.getY() * speed);
    }

    @Override
    public void stopThread() {
        this.threadRun = false;
    }

    @Override
    public void resumeThread() {
        this.threadRun = true;
    }

    @Override
    public Enemy getEnemy() {
        return this.enemy;
    }

    @Override
    public void complete() {
        this.enemyController.removeEnemy(this);
    }
}
