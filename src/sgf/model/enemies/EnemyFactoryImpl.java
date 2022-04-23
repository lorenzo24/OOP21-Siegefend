package sgf.model.enemies;

import sgf.model.map.Position;

/**
 * Class that creates various {@link Enemy} types.
 */
public class EnemyFactoryImpl implements EnemyFactory {
    // Constant of parameters of the enemies.
    static final double MULTIPLIER = 0.1; 
    static final double HP_PLANE = 100;
    static final double SPEED_PLANE = 2;
    static final double HP_HELICOPTER = 150;
    static final double SPEED_HELICOPTER = 1;
    static final double HP_TANK = 225;
    static final double SPEED_TANK = 1;

    @Override
    public Enemy createHelicopter(final Position position, final int waveNumber) {
        return new EnemyImpl(position, this.calculateDynamicHP(HP_HELICOPTER, waveNumber), SPEED_HELICOPTER, EnemyType.HELICOPTER);
    }

    @Override
    public Enemy createPlane(final Position position, final int waveNumber) {
        return new EnemyImpl(position, this.calculateDynamicHP(HP_PLANE, waveNumber), SPEED_PLANE, EnemyType.PLANE);
    }

    @Override
    public Enemy createTank(final Position position, final int waveNumber) {
        return new EnemyImpl(position, this.calculateDynamicHP(HP_TANK, waveNumber), SPEED_TANK, EnemyType.TANK);
    }

    @Override
    public Enemy createDynamicHelicopter(final Position position, final double hp, final double speed) {
        return new EnemyImpl(position, hp, speed, EnemyType.HELICOPTER);
    }

    @Override
    public Enemy createDynamicPlane(final Position position, final double hp, final double speed) {
        return new EnemyImpl(position, hp, speed, EnemyType.PLANE);
    }

    @Override
    public Enemy createGeneralTank(final Position position, final double hp, final double speed) {
        return new EnemyImpl(position, hp, speed, EnemyType.TANK);
    }

    private double calculateDynamicHP(final double originalHP, final int n) {
        return originalHP * (1 + (MULTIPLIER * n));
    }
}
