package sgf.model;

/**
 * Represents a pair of spatial coordinates on the map. 
 */
public class Position {

    private double x;
    private double y;

    /**
     * Set the initial positions.
     * @param x
     * @param y
     */
    public Position(final double x, final double y) {
        super();
        this.x = x;
        this.y = y;
    }

    /**
     * @return x position.
     */
    public double getX() {
        return this.x;
    }

    /**
     * Sets x position.
     * @param x
     */
    public void setX(final double x) {
        this.x = x;
    }

    /**
     * @return y position.
     */
    public double getY() {
        return this.y;
    }

    /**
     * Sets y position.
     * @param y
     */
    public void setY(final double y) {
        this.y = y;
    }

    /**
     * Sets both positions of an enemy.
     * @param x
     * @param y
     */
    public void setCoordinates(final double x, final double y) {
        this.setX(x);
        this.setY(y);
    }
}
