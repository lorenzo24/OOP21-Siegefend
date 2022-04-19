package sgf.model.bullet;

import sgf.model.enemies.Enemy;
import sgf.model.map.Position;

/**
 * Interface that represents a bullet.
 */
public interface Bullet {

    /**
     * Returns the id of the bullet.
     * @return the id of the bullet
     */
    int getID();

    /**
     * Returns the speed of the bullet.
     * @return the speed of the bullet
     */
    double getSpeed();

    /**
     * Returns the bullet's position.
     * @return the bullet's position
     */
    Position getPosition();

    /**
     * Returns the target's position.
     * @return the target's position
     */
    Position getTargetPosition();

    /**
     * Returns the bullet's damage.
     * @return the bullet's damage
     */
    double getDamage();

    /**
     * Returns the target of the bullet.
     * @return the target of the bullet
     */
    Enemy getTarget();

    /**
     * Moves the bullet to the given coordinate.
     * @param x the new x coordinate
     * @param y the new y coordinate
     */
    void move(double x, double y);

}
