package sgf.model.bullet;

import sgf.model.enemies.Enemy;
import sgf.model.map.Position;

/**
 * Implementation of a bullet that follows a target.
 */
public class BulletImpl implements Bullet {

    private final double speed;
    private final Position position;
    private final double damage;
    private Enemy target;

    /**
     * Creates a new instance of Bullet.
     * @param speed the speed of the bullet
     * @param position the initial position of the bullet
     * @param damage the damage of the bullet
     * @param target the target of the bullet
     */
    public BulletImpl(final double speed, final Position position, final double damage, final Enemy target) {
        this.speed = speed;
        this.position = new Position(position);
        this.damage = damage;

        if (target == null) {
            throw new IllegalArgumentException("Target must not be null!");
        } else {
            this.target = target;
        }
    }


    @Override
    public double getSpeed() {
        return this.speed;
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    @Override
    public Position getTargetPosition() {
        return this.target.getPosition();
    }

    @Override
    public double getDamage() {
        return this.damage;
    }

    @Override
    public Enemy getTarget() {
        return this.target;
    }

    @Override
    public void move(final double x, final double y) {
        this.position.setCoordinates(x, y);
    }

}
