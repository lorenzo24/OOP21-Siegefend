package sgf.model.map;

import sgf.utilities.Pair;

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
     * Methds that helps to calculate the next position of the enemy. 
     * @return The pair of the direction that has to be calculate with the position of the enemy.
     */
    public Pair<Integer, Integer> toUnitVector() {
        switch (this) {
        case UP :
            return Pair.from(0, -1);
        case RIGHT:
            return Pair.from(1, 0);
        case DOWN :
            return Pair.from(0, 1);
        case LEFT :
            return Pair.from(-1, 0);
        default :
            throw new IllegalArgumentException("Enemy direction not correct");
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
