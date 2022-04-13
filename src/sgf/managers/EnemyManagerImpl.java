package sgf.managers;
import java.util.Optional;

import sgf.controller.enemy.EnemyController;
import sgf.controller.game.PlayerController;
import sgf.helpers.ImgTileSize;
import sgf.model.enemies.Enemy;
import sgf.model.game.Pausable;
import sgf.model.map.Direction;
import sgf.model.map.GridPosition;
import sgf.model.map.Map;
import sgf.model.map.Position;
import sgf.utilities.Pair;
import sgf.utilities.PositionConverter;

/**
 * Class that manages each single enemy.
 */
public class EnemyManagerImpl implements EnemyManager, Pausable {
    private static final int ENEMY_SPEED = 10;
    private final int imgSize = ImgTileSize.getTileSize();
    private volatile boolean threadRun = true; // Boolean that manages the thread loop.
    private final Enemy enemy;
    private final Map map;
    private final EnemyController enemyController;
    private int stepsDone;
    private Optional<Direction> lastDir = Optional.empty();
    private final PositionConverter converter; // Converts the gridPosition to Position.
    private final PlayerController playerController;  //Manager of Player, used to update his stats.

    /**
     * Creates a managerImpl that controls the enemy's movement.
     * @param enemy the managed enemy
     * @param levelManager the manager of the current level
     * @param enemyController the controller of the enemy view
     * @param playerController the controller of the player
     * @param gameManager the manager of the game
     */
    public EnemyManagerImpl(final Enemy enemy, final LevelManager levelManager, final EnemyController enemyController, final PlayerController playerController, final GameManager gameManager) {
        this.enemy = enemy;
        this.map = levelManager.getMap();
        this.enemyController = enemyController;
        this.converter = new PositionConverter(ImgTileSize.getTileSize());
        this.playerController = playerController;
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
     * Checks if the sprite is over the screen, and in that case it calls the complete methods. 
     */
    private void checkFinalDestination() {
        final double x = this.enemy.getPosition().getX();
        final double y = this.enemy.getPosition().getY();
        if (x == -imgSize || y == -imgSize || this.endIntoMap(x)  || this.endIntoMap(y)) { // Checks if the sprite isn't in the limits of the screen (left and up).
            endReached();
        }
    }

    private boolean endIntoMap(final double v) {
        return this.map.getSize() * imgSize == v; // Checks if the sprite isn't in the limits of the screen (right and down).
    }

    private void nextMovement() {
        if (this.initialPart()) { // It checks if the enemy is at the start of the tile.
            this.takeDirection(); // It selects and sets up the direction.
        }
        this.stepsDone += this.enemy.getSpeed(); // Sets the step to the next position of the cell.
        if (this.stepsDone == this.imgSize) { // If it is at the end of the tile, it resets to zero.
            this.stepsDone = 0;
        }
        this.enemyMovement(this.lastDir.orElseThrow()); // Moves the enemy.
    }

    // Checks if the enemy is at the start of the tile.
    private boolean initialPart() {
        return stepsDone == 0; 
    }

    // Takes the next direction.
    private void takeDirection() {
        final GridPosition p = this.converter.convertToGridPosition(this.enemy.getPosition());
        final Optional<Direction> d = this.map.getTiles().get(p).getTileDirection();
        this.lastDir = d;
    }

    private void enemyMovement(final Direction dir) {
        final Position p =  this.enemy.getPosition(); // Takes the current position of the enemy.
        final double speed = this.enemy.getSpeed(); // Takes the speed, advancement step, of the specific enemy.
        final Pair<Integer, Integer> vec = dir.toUnitVector(); // Takes the direction vector that permits to move the enemy to the right direction.
        enemy.move(p.getX() + vec.getX() * speed, p.getY() + vec.getY() * speed); // Moves the enemy to the next position.
    }

    @Override
    public void damage(final double damage) {
        if (this.enemy.getHP() - damage <= 0) {
            unitDeath();
        }
        this.enemy.damageSuffered(damage);
    }

    @Override
    public void stop() {
        this.threadRun = false;
    }

    @Override
    public void resume() {
        this.threadRun = true;
    }

    @Override
    public Enemy getEnemy() {
        return this.enemy;
    }

    @Override
    public void disappear() {
        this.threadRun = false; // Stops the thread.
        this.enemyController.removeEnemy(this);
    }

    private void endReached() {
        this.playerController.changeHP(-1);                                         //TODO: change method so that it uses PlayerImpl.DecreaseHP()
        this.playerController.changeScore(-(int) this.enemy.getPoints());
        this.disappear();
    }

    private void unitDeath() {
        this.playerController.changeMoney((int) this.enemy.getPoints());            //TODO: change these two so that each enemy has its own money if killed.
        this.playerController.changeScore((int) this.enemy.getPoints());
        this.disappear();
    }
}
