package sgf.model;

/**
 *
 */
public class EnemyImpl implements Enemy {

    private Position position;
    private double hp;
    private double completionPercentage = 0;
    private double speed;

    /**
     * Constructor for an enemy.
     * @param position Denotes the enemy's initial position.
     * @param hp Are enemy's initial health points 
     * @param speed Is the enemy's moving speed.
     */
    public EnemyImpl(final Position position, final double hp, final double speed) {
        this.position = position;
        this.hp = hp;
        this.speed = speed;
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    @Override
    public double getHP() {
        return this.hp;
    }

    @Override
    public double getCompletionPercentage() {
        return this.completionPercentage;
    }

    @Override
    public double getSpeed() {
        return this.speed;
    }

    @Override
    public void move() {
    }

}
