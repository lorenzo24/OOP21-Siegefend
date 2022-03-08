package sgf.utilities;

import sgf.model.GridPosition;
import sgf.model.Position;

/**
 * Class converter.
 */
public class PositionConverter {
    private final int tileSize;
    /**
     * Constructor.
     * @param tileSize is.
     */
    public PositionConverter(final int tileSize) {
        this.tileSize = tileSize;
    }

    /**
     * Convert.
     * @param position
     * @return a position.
     */
    public Position convertToPosition(final GridPosition position) {
        return new Position(position.getColumn() * this.tileSize, position.getRow() * this.tileSize);
    }

    /**
     * Convert.
     * @param position
     * @return a GridPosition.
     */
    public GridPosition convertToGridPosition(final Position position) {
        return new GridPosition((int) position.getY() / tileSize, (int) position.getX() / tileSize);
    }
}
