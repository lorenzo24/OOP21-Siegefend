package sgf.model;

/**
 * Represents a pair of numerical coordinates. 
 * @param <T> The type of the coordinates.
 */
public class Position<T extends Number> {

    private T x;
    private T y;

    /**
     * Set the initial positions.
     * @param x
     * @param y
     */
    public Position(final T x, final T y) {
        super();
        this.x = x;
        this.y = y;
    }

    /**
     * @return x position.
     */
    public T getX() {
        return this.x;
    }

    /**
     * Sets x position.
     * @param x
     */
    public void setX(final T x) {
        this.x = x;
    }

    /**
     * @return y position.
     */
    public T getY() {
        return this.y;
    }

    /**
     * Sets y position.
     * @param y
     */
    public void setY(final T y) {
        this.y = y;
    }

    /**
     * Sets both positions of an enemy.
     * @param x
     * @param y
     */
    public void setCoordinates(final T x, final T y) {
        this.setX(x);
        this.setY(y);
    }
}
