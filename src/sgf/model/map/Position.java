package sgf.model.map;

import java.util.Objects;

/**
 * Represents a pair of spatial coordinates on the map. 
 */
public class Position {

    private double x;
    private double y;

    /**
     * Creates a new instance of {@code Position}.
     * @param x The x coordinate of the position
     * @param y The y coordinate of the position
     */
    public Position(final double x, final double y) {
        super();
        this.x = x;
        this.y = y;
    }

    /**
     * Creates a new instance of {@code Position} from another
     * instance of position.
     * @param pos the instance of position to copy
     */
    public Position(final Position pos) {
        this(pos.x, pos.y);
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

    /**
     * Calculates the distance from another position.
     * @param pos the other position
     * @return the distance
     */
    public double distanceTo(final Position pos) {
        return Math.sqrt(Math.pow(pos.getX() - this.getX(), 2) + Math.pow(pos.getY() - this.getY(), 2));
    }

    /**
     * Returns the angle between two positions in radians.
     * @param target the other position
     * @return the angle in radians
     */
    public double getAngle(final Position target) {
        return Math.atan2(target.getY() - this.getY(), target.getX() - this.getX());
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Position other = (Position) obj;
        return Double.doubleToLongBits(x) == Double.doubleToLongBits(other.x)
                && Double.doubleToLongBits(y) == Double.doubleToLongBits(other.y);
    }

    @Override
    public String toString() {
        return "Position [x=" + x + ", y=" + y + "]";
    }
}
