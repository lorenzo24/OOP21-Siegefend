package sgf.model.turret;

import java.util.Objects;
import java.util.Optional;

import sgf.model.bullet.Bullet;
import sgf.model.bullet.BulletFactory;
import sgf.model.bullet.BulletFactoryImpl;
import sgf.model.enemies.Enemy;
import sgf.model.enemies.EnemyImpl;
import sgf.model.map.Position;

/**
 * Class that represents a simple turret.
 */
public class TurretImpl implements Turret, Cloneable {

    private final int id;
    private Position position;
    private final double range;
    private final int price;
    private final double fireRate;
    private Enemy target;
    private boolean state;
    private final double bulletSpeed;
    private final double bulletDamage;
    private final BulletFactory bulletFactory;
    private double angle;

    /**
     * Creates a new instance of Turret.
     * @param id the id of the turret
     * @param position the initial {@code Position} of the turret
     * @param range the range of the turret
     * @param price the price of the turret
     * @param fireRate the fire rate of the turret
     */
    public TurretImpl(final int id, final Position position, final double range, final int price, final double fireRate, final double bulletSpeed, final double bulletDamage) {
        this.id = id;
        this.position = position != null ? new Position(position) : null;
        this.range = range;
        this.price = price;
        this.fireRate = fireRate;
        this.bulletSpeed = bulletSpeed;
        this.bulletDamage = bulletDamage;
        this.bulletFactory = new BulletFactoryImpl();
        this.angle = Math.PI / 4;
    }

    @Override
    public int getID() {
        return this.id;
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    @Override
    public void setPosition(final Position p) {
        this.position = new Position(p);
    }

    @Override
    public double getRange() {
        return this.range;
    }

    @Override
    public void setState(final boolean state) {
        this.state = state;
    }

    @Override
    public boolean isAttacking() {
        return this.state;
    }

    @Override
    public Bullet createBullet() {
        return this.bulletFactory.createBullet(this.id, this.bulletSpeed, this.position, this.bulletDamage, this.target);
    }

    @Override
    public double getFireRate() {
        return this.fireRate;
    }

    @Override
    public Optional<Enemy> getTarget() {
        return Optional.ofNullable(this.target);
    }

    @Override
    public int getPrice() {
        return this.price;
    }

    @Override
    public TurretImpl clone() throws CloneNotSupportedException {
        return (TurretImpl) super.clone();
    }

    @Override
    public Turret getClone() {
        try {
            return this.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        throw new UnsupportedOperationException("Class " + this.getClass().getName() + " does not support cloning.");
    }

    @Override
    public double getAngle() {
        return this.angle;
    }

    @Override
    public void setAngle(final double angle) {
        this.angle = angle;
    }

    @Override
    public void setTarget(final Enemy target) {
        this.target = target;
    }

    @Override
    public int hashCode() {
        return Objects.hash(angle, bulletDamage, bulletFactory, bulletSpeed, fireRate, id, position, price, range,
                state, target);
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
        final TurretImpl other = (TurretImpl) obj;
        return Double.doubleToLongBits(angle) == Double.doubleToLongBits(other.angle)
                && Double.doubleToLongBits(bulletDamage) == Double.doubleToLongBits(other.bulletDamage)
                && Objects.equals(bulletFactory, other.bulletFactory)
                && Double.doubleToLongBits(bulletSpeed) == Double.doubleToLongBits(other.bulletSpeed)
                && Double.doubleToLongBits(fireRate) == Double.doubleToLongBits(other.fireRate) && id == other.id
                && Objects.equals(position, other.position) && price == other.price
                && Double.doubleToLongBits(range) == Double.doubleToLongBits(other.range) && state == other.state
                && Objects.equals(target, other.target);
    }

    @Override
    public String toString() {
        return "TurretImpl [ID=" + id + ", position=" + position + ", range=" + range + ", price=" + price
                + ", fireRate=" + fireRate + ", bulletSpeed=" + bulletSpeed + ", bulletDamage=" + bulletDamage + "]";
    }
}
