package sgf.model.enemies;

import java.util.Objects;
import sgf.model.map.Position;

/**
 * This class represents the implementation of the interface {@link Enemy}.
 */
public class EnemyImpl implements Enemy {
    private final Position position;
    private double stepsDone;
    private double hp;
    private final double maxHp;
    private double hpPercent;
    private final double speed;
    private final double points;
    private final EnemyType enemyType;

    /**
     * Creates an {@link Enemy}.
     * @param position Is the position in pixel occupied by the {@link Enemy}.
     * @param hp Is the {@link Enemy} health.
     * @param speed Is the movement speed parameter.
     * @param enemyType Denotes the type of the {@link Enemy}.
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
    public Double getSteps() {
        return this.stepsDone;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.enemyType, this.position);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EnemyImpl other = (EnemyImpl) obj;
        return this.enemyType == other.enemyType && Objects.equals(this.position, other.position);
    }

    @Override
    public String toString() {
        return "EnemyImpl [position=" + position + ", stepsDone=" + stepsDone + ", hp=" + hp + ", maxHp=" + maxHp
                + ", hpPercent=" + hpPercent + ", speed=" + speed + ", points=" + points + ", enemyType=" + enemyType
                + "]";
    }
}
