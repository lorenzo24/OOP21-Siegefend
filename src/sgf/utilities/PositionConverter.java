package sgf.utilities;

import sgf.model.map.GridPosition;
import sgf.model.map.Position;

/**
 * Class converter position.
 */
public class PositionConverter {
    private final int tileSize;

    /**
     * Converter constructor.
     * @param tileSize Is the size of the tile Image.
     */
    public PositionConverter(final int tileSize) {
        this.tileSize = tileSize;
    }

    /**
     * Method that converts from GridPosition to Position.
     * @param position Is the position to be converted.
     * @return the corresponding Position.
     */
    public Position convertToPosition(final GridPosition position) {
        return new Position(position.getColumn() * this.tileSize, position.getRow() * this.tileSize);
    }

    /**
     * Method that converts form Position to GridPosition.
     * @param position Is the position to be converted.
     * @return the corresponding GridPosition.
     */
    public GridPosition convertToGridPosition(final Position position) {
        return new GridPosition((int) position.getY() / tileSize, (int) position.getX() / tileSize);
    }
}
