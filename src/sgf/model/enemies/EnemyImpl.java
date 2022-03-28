package sgf.model.enemies;

import sgf.model.map.Position;

/**
 * This class represents the implementation of the interface Enemy.
 */
public class EnemyImpl implements Enemy {
    private final Position position;
    private double hp;
    private final double maxHp;
    private final double hpPercent;
    private final double speed;
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
        this.hpPercent = this.calculateHp();
    }

    private double calculateHp() {
        return this.hp / this.maxHp;
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
    }

    @Override
    public EnemyType getEnemyType() {
        return this.enemyType;
    }

    @Override
    public void setHP(final double hp) {
        this.hp = hp;
    }

    @Override
    public double getPercentHp() {
        return this.hpPercent;
    }
}
