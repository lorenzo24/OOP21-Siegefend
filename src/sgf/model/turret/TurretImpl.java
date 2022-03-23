package sgf.model.turret;

import java.util.Optional;

import sgf.model.bullet.Bullet;
import sgf.model.bullet.BulletFactory;
import sgf.model.bullet.BulletFactoryImpl;
import sgf.model.enemies.Enemy;
import sgf.model.map.Position;

/**
 * Class that represents a simple turret.
 */
public class TurretImpl implements Turret {

    private final int id;
    private final Position position;
    private final double range;
    private final int price;
    private final double fireRate;
    private Enemy target;
    private boolean state;
    private final double bulletSpeed;
    private final double bulletDamage;
    private final BulletFactory bulletFactory;
    // TODO: private TurretStats stats; classe con stats delle torrette.

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
        this.position = new Position(position);
        this.range = range;
        this.price = price;
        this.fireRate = fireRate;
        this.bulletSpeed = bulletSpeed;
        this.bulletDamage = bulletDamage;
        this.bulletFactory = new BulletFactoryImpl();
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
        return this.bulletFactory.createBullet(this.bulletSpeed, this.position, this.bulletDamage, this.target);
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
    public Turret clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

}
