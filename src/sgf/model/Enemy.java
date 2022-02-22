package sgf.model;

/**
 * Represents an enemy that can spawn on the map.
 */
public interface Enemy {

    /** 
     * Returns the current {@link Position} of the enemy.
     * @return the position of the enemy
     */
    Position getPosition();

    /**
     * Set the new position of an enemy.
     * @param position Is the position of the enemy.
     */
    void setPosition(Position position);

    /** 
     * Returns the amount of health points the enemy has left.
     * @return the health points of the enemy
     */
    double getHP();

    /**
     * Returns how much of the route has already been covered by the enemy.
     * @return the percentage of the completed route
     */
    double getCompletionPercentage();

    /**
     * Returns the speed at which the enemy moves on the map.
     * @return the speed of the enemy
     */
    double getSpeed();

    /**
     *  Triggers the enemy to start moving.
     */
    void move();
}
