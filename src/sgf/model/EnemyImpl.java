package sgf.model;

/**
 * This class represents the implementation of the interface Enemy.
 */
public class EnemyImpl implements Enemy {
    private final int iD;       // Still not used.
    private Position position;
    private double hp;
    private double speed;
    private EnemyType enemyType;
    private double completionPercentage = 0; // To be implemented soon.

    /**
     * Constructor for an enemy.
     * @param iD Is the identifier for the enemy.
     * @param position Is the position in pixel occupied by the enemy.
     * @param hp Is the enemy's health.
     * @param speed Is the movement speed parameter.
     * @param enemyType Denotes the type of the enemy.
     */
    public EnemyImpl(final int iD, final Position position, final double hp, final double speed, final EnemyType enemyType) {
        this.iD = iD;
        this.position = position;
        this.hp = hp;
        this.speed = speed;
        this.enemyType = enemyType;
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    private void setPosition(final double x, final double y) {
        this.position = new Position(x, y);
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
        this.setPosition(this.position.getX() + 1, this.position.getY()); 
    }

    @Override
    public EnemyType getEnemyType() {
        return this.enemyType;
    }

    @Override
    public int getID() {
        return this.iD;
    }

}
