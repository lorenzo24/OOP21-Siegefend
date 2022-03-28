package sgf.model.enemies;

import sgf.model.map.Position;

/**
 * Represents an enemy that can spawn on the map.
 */
public interface Enemy {

    /** 
     * Returns the current {@link Position} of the enemy.
     * @return the position of the enemy.
     */
    Position getPosition();

    /** 
     * Returns the amount of health points the enemy has left.
     * @return the health points of the enemy.
     */
    double getHP();

    /**
     * Returns the speed at which the enemy moves on the map.
     * @return the speed of the enemy.
     */
    double getSpeed();

    /**
     * Returns the type of the enemy.
     * @return the enemy type.
     */
    EnemyType getEnemyType();

    /**
     * Triggers the enemy to start moving.
     * @param x coordinate.
     * @param y coordinate.
     */
    void move(double x, double y);

    /**
     * Take the percentual of enemy Life. 
     * @return the percentual of enemy life.
     */
    double getPercentHp();

    /**
     * Method that decrise the enemy life. 
     * @param damage Is the damage that the enemy has recived.
     */
    void damageSuffered(double damage);
}
