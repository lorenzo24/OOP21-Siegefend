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
     * Returns the amount of health points the enemy has left.
     * @return the health points of the enemy
     */
    double getHP();

    /**
     * Returns If an enemy has reached the path destination.
     * @return If an enemy has reached the path destination.
     */
    boolean isWin();

    /**
     * Set when an enemy win.
     * @param win set the field true.
     */
    void setWin(boolean win);

    /**
     * Returns the speed at which the enemy moves on the map.
     * @return the speed of the enemy
     */
    double getSpeed();

    /**
     * Returns the type of the enemy.
     * @return the enemy type.
     */
    EnemyType getEnemyType();

    /**
     * Returns the enemy id.
     * @return the enemy id.
     */
    int getID();

    /**
     * Triggers the enemy to start moving.
     * @param x coordinate.
     * @param y coordinate.
     */
    void move(double x, double y);
}
