package sgf.model.enemies;

import sgf.model.map.Position;

/**
 * Interface of factory class that create enemies.
 */
public interface EnemyFactory {

    /**
     * Create an Helicopter in the start position.
     * @param position Is the start position.
     * @return the enemy.
     */
    Enemy createHelicopter(Position position);

    /**
     * Create a Plane in the start position.
     * @param position Is the start position.
     * @return the enemy.
     */
    Enemy createPlane(Position position);

    /**
     * Create a Tank in the start position.
     * @param position Is the start position.
     * @return the enemy.
     */
    Enemy createTank(Position position);

    /**
     * Generate a General helicopter.
     * @param position Is the position that has to spone.
     * @param hp Is the life.
     * @param speed Is the speed of movement.
     * @return the enemy.
     */
    Enemy createGeneralHelicopter(Position position, double hp, double speed);

    /**
     * Generate a General plane.
     * @param position Is the position that has to spone.
     * @param hp Is the life.
     * @param speed Is the speed of movement.
     * @return the enemy.
     */
    Enemy createGeneralPlane(Position position, double hp, double speed);

    /**
     * Generate a General tank.
     * @param position Is the position that has to spone.
     * @param hp Is the life.
     * @param speed Is the speed of movement.
     * @return the enemy.
     */
    Enemy createGeneralTank(Position position, double hp, double speed);
}
