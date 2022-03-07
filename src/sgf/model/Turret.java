package sgf.model;

import java.util.Optional;

import sgf.model.enemies.Enemy;

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
     * Instructs the turret to start attacking. <br />
     * If no target is present, the turret immediately goes into idle mode.
     */
    void attack();

    /**
     * Puts the turret in idle mode.
     */
    void idle();

    /**
     * Checks whether the turret is attacking an {@link Enemy} or not.
     * @return {@code true} if it's attack an enemy, {@code false} otherwise
     */
    boolean isAttacking();

    /**
     * Instructs the turret to fire a {@link Bullet} at the targeted {@link Position}.
     * @param target The target position
     */
    void fireAt(Position target);

    /**
     * Returns the speed at which the turret fires bullets (specified in bullets/second).
     * @return the fire rate of the turret
     */
    double getFireRate();

    /**
     * Tries to find a target {@link Enemy} within the range of the turret.
     * @return An {@link Optional} containing the enemy if successful, {@code Optional.empty()} otherwise.
     */
    Optional<Enemy> findTarget();

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
