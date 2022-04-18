package sgf.model.bullet;

import sgf.model.enemies.Enemy;
import sgf.model.map.Position;

/**
 * Class that creates bullets.
 */
public class BulletFactoryImpl implements BulletFactory {

    @Override
    public Bullet createBullet(final int id, final double speed, final Position startPosition, final double damage, final Enemy enemyTarget) {
        return new BulletImpl(id, speed, startPosition, damage, enemyTarget);
    }

}
