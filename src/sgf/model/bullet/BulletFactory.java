package sgf.model.bullet;

import sgf.model.enemies.Enemy;
import sgf.model.map.Position;

/**
 * Creates instances of the {@link Bullet} interface.
 */
public interface BulletFactory {
    /**
     * Creates a new {@link Bullet} instance with an initial position, target and other parameters.
     * @param id the id of the bullet (normally the id of the turret which fires it).
     * @param speed the speed of the bullet
     * @param startPosition the initial position of the bullet
     * @param damage the damage of the bullet
     * @param enemyTarget The target of the bullet
     * @return a {@link Bullet} instance
     */
    Bullet createBullet(int id, double speed, Position startPosition, double damage, Enemy enemyTarget);
}
