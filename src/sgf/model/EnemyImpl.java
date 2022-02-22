package sgf.model;

/**
 *
 */
public class EnemyImpl implements Enemy {
    private final int iD;
    private Position position;
    private double hp;
    private double completionPercentage = 0; // NON SERVE
    private double speed;
    private int enemyType;

    /**
     * 
     * @param iD
     * @param position
     * @param hp
     * @param speed
     * @param enemyType
     */
    public EnemyImpl(final int iD, final Position position, final double hp, final double speed, final int enemyType) {
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
        this.setPosition(this.position.getX(), this.position.getY() + 1);
    }

}
