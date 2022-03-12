package sgf.model.enemies;

import sgf.model.map.Position;

/**
 * Interface of factory class that creates enemies.
 */
public interface EnemyFactory {

    /**
     * Creates an Helicopter in the start position.
     * @param position Is the start position.
     * @return the enemy.
     */
    Enemy createHelicopter(Position position);

    /**
     * Creates a Plane in the start position.
     * @param position Is the start position.
     * @return the enemy.
     */
    Enemy createPlane(Position position);

    /**
     * Creates a Tank in the start position.
     * @param position Is the start position.
     * @return the enemy.
     */
    Enemy createTank(Position position);

    /**
     * Generates a dynamic helicopter (with dynamic parameters).
     * @param position Is the spawning position.
     * @param hp Is the helicopter's life.
     * @param speed Is the helicopter's speed of movement.
     * @return the enemy.
     */
    Enemy createDynamicHelicopter(Position position, double hp, double speed);

    /**
     * Generates a dynamic plane (with dynamic parameters).
     * @param position Is the spawning position.
     * @param hp Is the life.
     * @param speed Is the speed of movement.
     * @return the enemy.
     */
    Enemy createDynamicPlane(Position position, double hp, double speed);

    /**
     * Generate a dynamic tank (with dynamic parameters).
     * @param position Is the spawning position.
     * @param hp Is the life.
     * @param speed Is the speed of movement.
     * @return the enemy.
     */
    Enemy createGeneralTank(Position position, double hp, double speed);
}
