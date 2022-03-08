package sgf.model.enemies;

import sgf.model.Position;

/**
 * Class that create an Tank enemy.
 */
public class Tank extends EnemyImpl {
    static final double HP = 200;
    static final double SPEED = 1;

    /**
     *  Create a tank.
     * @param position Is the position in pixel occupied by the enemy.
     */
    public Tank(final Position position) {
        super(position, HP, SPEED, EnemyType.TANK);
    }
}
