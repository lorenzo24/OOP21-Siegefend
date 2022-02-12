package sgf.model;

/**
 * Represents a cardinal direction.
 */
public enum Direction {
    /**
     * Direction up.
     */
    UP(0),
    /**
     * Direction right.
     */
    RIGHT(1),
    /**
     * Direction down.
     */
    DOWN(2),
    /**
     * Direction left.
     */
    LEFT(3);

    private final int dir;

    Direction(final int dir) {
        this.dir = dir;
    }

    private static Direction fromValue(final int dir) {
        if (dir < 4) {
            return values()[dir];
        } else {
            throw new IllegalArgumentException("Invalid value for direction");
        }
    }

    /**
     * Rotates the current direction by 90 degrees clockwise.
     * @param d The current direction
     * @return the new direction
     */
    public Direction rotateClockWise(final Direction d) {
        return Direction.fromValue((d.dir + 1) % 4);
    }

    /**
     * Rotates the current direction by 90 degrees counterclockwise.
     * @param d The current direction
     * @return the new direction
     */
    public Direction rotateCounterClockWise(final Direction d) {
        return Direction.fromValue((d.dir + 3) % 4);
    }
}
