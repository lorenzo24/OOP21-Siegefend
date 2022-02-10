package sgf.model;

/**
 * Interface that represent an enemy.
 */
public interface Enemy {

    /** 
     * @return The positions of the enemy. 
     */
    Position getPosition();

    /** 
     * @return The health points.
     */
    double getHP();

    /**
     * @return The percentage of the completed route.
     */
    double getCompletionPercentage();

    /**
     * @return the enemy speed.
     */
    double getSpeed();

    /**
     *  Moves the enemy. 
     */
    void move();
}
