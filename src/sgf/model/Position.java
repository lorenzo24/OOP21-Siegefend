package sgf.model;

/**
 * Represents a pair of spatial coordinates on the map. 
 */
public class Position {

    private double x;
    private double y;

    /**
     * Creates a new instance of {@code Position}.
     * @param x The x coordinate of the position.
     * @param y The y coordinate of the position.
     */
    public Position(final double x, final double y) {
        super();
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the x coordinate of the position.
     * @return the x coordinate of the position
     */
    public double getX() {
        return this.x;
    }

    /**
     * Sets the x coordinate of the position.
     * @param x The new value for the x coordinate
     */
    public void setX(final double x) {
        this.x = x;
    }

    /**
     * Returns the y coordinate of the position.
     * @return y position.
     */
    public double getY() {
        return this.y;
    }

    /**
     * Sets the y coordinate of the position.
     * @param y The new value for the y coordinate
     */
    public void setY(final double y) {
        this.y = y;
    }

    /**
     * Sets both coordinates of the position.
     * @param x The x coordinate of the position
     * @param y The y coordinate of the position
     */
    public void setCoordinates(final double x, final double y) {
        this.setX(x);
        this.setY(y);
    }
}
