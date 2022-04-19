package sgf.utilities;

import sgf.model.map.GridPosition;
import sgf.model.map.Position;

/**
 * Class for converting from {@link Position} to {@link GridPosition} and vice versa.
 */
public class PositionConverter {
    private final int tileSize;

    /**
     * Creates a new instance of the class with a certain tile size.
     * @param tileSize the size of a tile on the map
     */
    public PositionConverter(final int tileSize) {
        this.tileSize = tileSize;
    }

    /**
     * Converts a {@link GridPosition} to a {@link Position}.
     * @param position the {@code GridPosition} to convert
     * @return the corresponding {@code Position}
     */
    public Position convertToPosition(final GridPosition position) {
        return new Position(position.getColumn() * this.tileSize, position.getRow() * this.tileSize);
    }

    /**
     * Converts a {@link Position} to a {@link GridPosition}.
     * @param position the {@code Position} to convert
     * @return the corresponding GridPosition.
     */
    public GridPosition convertToGridPosition(final Position position) {
        return new GridPosition((int) position.getY() / tileSize, (int) position.getX() / tileSize);
    }
}
