package sgf.model.bullet;

import sgf.model.enemies.Enemy;
import sgf.model.map.Position;

/**
 * Class that creates bullets.
 */
public class BulletFactoryImpl implements BulletFactory {

    @Override
    public Bullet createBullet(final double speed, final Position startPosition, final double damage, final Enemy enemyTarget) {
        return new BulletImpl(speed, startPosition, damage, enemyTarget);
    }

}
