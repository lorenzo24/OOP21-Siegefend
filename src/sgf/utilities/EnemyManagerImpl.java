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
        if (this.initialPart()) { // It control if the enemy is at the start of the tile.
            this.takeDirection(); // It select and setting up the direction.
        }
        this.stepsDone += this.enemy.getSpeed(); // Set the step to the next position of the cell.
        if (this.stepsDone == this.imgSize) { // If ti are at the end of the cell, it reset to zero.
            this.stepsDone = 0;
        }
        this.enemyMovement(this.lastDir.orElseThrow()); // Maove the enemy.
    }

    // Control if the enemy is at the start of the tile.
    private boolean initialPart() {
        return stepsDone == 0; 
    }

    // Take the next direction.
    private void takeDirection() {
        final GridPosition p = this.converter.convertToGridPosition(this.enemy.getPosition());
        final Optional<Direction> d = this.map.getTiles().get(p).getTileDirection();
        this.lastDir = d;
    }

    private void enemyMovement(final Direction dir) {
        final Position p =  this.enemy.getPosition(); // Take the current position of the enemy.
        final double speed = this.enemy.getSpeed(); // Take the speed, advancement step, of the specific enemy.
        final Pair<Integer, Integer> vec = dir.toUnitVector(); // Take the direction vector that permit to move the enemy to the right direction.
        enemy.move(p.getX() + vec.getX() * speed, p.getY() + vec.getY() * speed); // Move the enemy to the next position.
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
