package sgf.managers;

import sgf.model.bullet.Bullet;

/**
 * Interface of bullet Manager.
 */
public interface BulletManager {
    /**
     * Returns the {@link Bullet} associated with the bullet manager.
     * @return a bullet
     */
    Bullet getBullet();

    /**
     * Eliminates the {@link Bullet} associated with this instance.
     */
    void eliminate();
}
