package sgf.model.turret;

import java.util.Optional;

import sgf.model.bullet.Bullet;
import sgf.model.enemies.Enemy;
import sgf.model.map.Position;

/**
 * Represents a turret that can be placed on the map.
 */
public interface Turret extends Cloneable {
    /**
     * Returns the ID of the turret.
     * @return the ID of the turret
     */
    int getID();

    /**
     * Returns the {@link Position} of the turret.
     * @return the position of the turret
     */
    Position getPosition();

    /**
     * Returns the range of the turret. <br />
     * The turret can only target enemies whose distance from the tower is less than its range.
     * @return the range of the turret
     */
    double getRange();

    /**
     * Sets the state of the turret.
     * @param state the new state of the turret
     */
    void setState(boolean state);

    /**
     * Checks whether the turret is attacking an {@link Enemy} or not.
     * @return {@code true} if it's attack an enemy, {@code false} otherwise
     */
    boolean isAttacking();

    /**
     * Creates a new bullet that attacks the targeted enemy.
     * @return an instance of {@code Bullet}
     */
    Bullet createBullet();

    /**
     * Returns the speed at which the turret fires bullets (specified in bullets/second).
     * @return the fire rate of the turret
     */
    double getFireRate();

    /**
     * Returns the target of the turret.
     * @return An {@link Optional} containing the enemy if present, {@code Optional.empty()} otherwise.
     */
    Optional<Enemy> getTarget();

    /**
     * Returns the price of the turret.
     * @return the price of the turret
     */
    int getPrice();

    /**
     * Creates a clone of the turret. <br />
     * Internally, the clone should be created by calling super.clone() and then
     * setting all the attributes by cloning them from the initial turret.
     * @return the cloned {@link Turret}
     * @throws CloneNotSupportedException
     */
    Turret clone() throws CloneNotSupportedException;
}
