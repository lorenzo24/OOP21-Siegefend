package sgf.model.enemies;

import sgf.model.Position;

/**
 * Class that create varios enemy type.
 */
public class EnemyFactoryImpl implements EnemyFactory {
    // Constant of parameters of the enemies.
    static final double HP_PLANE = 100;
    static final double SPEED_PLANE = 1;
    static final double HP_HELICOPTER = 50;
    static final double SPEED_HELICOPTER = 2;
    static final double HP_TANK = 200;
    static final double SPEED_TANK = 1;

    @Override
    public Enemy createHelicopter(final Position position) {
        return new EnemyImpl(position, HP_PLANE, SPEED_PLANE, EnemyType.HELICOPTER);
    }

    @Override
    public Enemy createPlane(final Position position) {
        return new EnemyImpl(position, HP_HELICOPTER, SPEED_HELICOPTER, EnemyType.PLANE);
    }

    @Override
    public Enemy createTank(final Position position) {
        return new EnemyImpl(position, HP_TANK, SPEED_TANK, EnemyType.TANK);
    }

    @Override
    public Enemy createGeneralHelicopter(final Position position, final double hp, final double speed) {
        return new EnemyImpl(position, hp, speed, EnemyType.HELICOPTER);
    }

    @Override
    public Enemy createGeneralPlane(final Position position, final double hp, final double speed) {
        return new EnemyImpl(position, hp, speed, EnemyType.PLANE);
    }

    @Override
    public Enemy createGeneralTank(final Position position, final double hp, final double speed) {
        return new EnemyImpl(position, hp, speed, EnemyType.TANK);
    }
}
