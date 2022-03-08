package sgf.model.enemies;

import sgf.model.Position;

/**
 * Class that create an Tank enemy.
 */
public class Plane extends EnemyImpl {
    static final double HP = 100;
    static final double SPEED = 2;

    /**
     *  Create a tank.
     * @param position Is the position in pixel occupied by the enemy.
     */
    public Plane(final Position position) {
        super(position, HP, SPEED, EnemyType.PLANE);
    }
}
