package sgf.model.enemies;

import sgf.model.map.Position;

/**
 * This class represents the implementation of the interface Enemy.
 */
public class EnemyImpl implements Enemy {
    private final Position position;
    private long stepsDone;
    private double hp;
    private final double maxHp;
    private double hpPercent;
    private final double speed;
    private final double points;
    private final EnemyType enemyType;

    /**
     * Creates an enemy.
     * @param position Is the position in pixel occupied by the enemy.
     * @param hp Is the enemy's health.
     * @param speed Is the movement speed parameter.
     * @param enemyType Denotes the type of the enemy.
     */
    public EnemyImpl(final Position position, final double hp, final double speed, final EnemyType enemyType) {
        this.position = new Position(position);
        this.hp = hp;
        this.maxHp = hp;
        this.speed = speed;
        this.enemyType = enemyType;
        this.points = hp * speed;
        this.stepsDone = 0;
        this.calculateHp();
    }

    private void calculateHp() {
        this.hpPercent = this.hp / this.maxHp;
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
    public double getSpeed() {
        return this.speed;
    }

    @Override
    public void move(final double x, final double y) {
        this.position.setCoordinates(x, y);
        this.stepsDone++;
    }

    @Override
    public EnemyType getEnemyType() {
        return this.enemyType;
    }

    @Override
    public double getPercentHp() {
        return this.hpPercent;
    }

    @Override
    public void damageSuffered(final double damage) {
        this.hp -= damage;
        this.calculateHp();
    }

    @Override
    public double getPoints() {
        return this.points;
    }

    @Override
    public long getSteps() {
        return this.stepsDone;
    }
}
