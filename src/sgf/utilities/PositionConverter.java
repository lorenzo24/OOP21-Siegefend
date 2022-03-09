package sgf.utilities;

import sgf.model.GridPosition;
import sgf.model.Position;

/**
 * Class converter position.
 */
public class PositionConverter {
    private final int tileSize;

    /**
     * Constructor set the fild.
     * @param tileSize Is the size of the tile Image.
     */
    public PositionConverter(final int tileSize) {
        this.tileSize = tileSize;
    }

    /**
     * Convert from GridPosition to Position.
     * @param position Is the position to be converter.
     * @return a Position.
     */
    public Position convertToPosition(final GridPosition position) {
        return new Position(position.getColumn() * this.tileSize, position.getRow() * this.tileSize);
    }

    /**
     * Convert form Position to GridPosition.
     * @param position Is the position to be converter.
     * @return a GridPosition.
     */
    public GridPosition convertToGridPosition(final Position position) {
        return new GridPosition((int) position.getY() / tileSize, (int) position.getX() / tileSize);
    }
}
