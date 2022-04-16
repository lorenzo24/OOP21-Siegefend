package sgf.managers;

import sgf.model.bullet.Bullet;
import sgf.model.game.Pausable;

/**
 *
 */
public interface BulletManager extends Pausable {
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
