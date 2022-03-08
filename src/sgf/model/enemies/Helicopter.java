package sgf.model.enemies;
import sgf.model.Position;

/**
 * Class that create an Tank enemy.
 */
public class Helicopter extends AbstractEnemy {
    static final double HP = 50;
    static final double SPEED = 1;

    /**
     *  Create a tank.
     * @param position Is the position in pixel occupied by the enemy.
     */
    public Helicopter(final Position position) {
        super(position, HP, SPEED, EnemyType.HELICOPTER);
    }
}
