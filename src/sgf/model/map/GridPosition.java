package sgf.model.map;

import java.util.Objects;

/**
 * Represents a pair of integer coordinates on a grid (row and column).
 */
public class GridPosition {
    private int row;
    private int column;

    /**
     * Basic constructor.
     * @param row The coordinate as row in the grid.
     * @param column The coordinate as column in the grid.
     */
    public GridPosition(final int row, final int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * Creates a new instance of {@code GridPosition} from another instance of GridPosition.
     * @param gpos the instance of GridPosition to copy
     */
    public GridPosition(final GridPosition gpos) {
        this(gpos.row, gpos.column);
    }

    /**
     * Method that gives the column of the {@link Position}.
     * @return the current column of the position.
     */
    public int getColumn() {
        return this.column;
    }

    /**
     * Method that sets a new value for the column of the {@link Position}.
     * @param column The new value for the column.
     */
    public void setColumn(final int column) {
        this.column = column;
    }

    /**
     * Method that gives the row of the {@link Position}.
     * @return the current row of the position.
     */
    public int getRow() {
        return this.row;
    }

    /**
     * Sets a new value for the row of the {@link Position}.
     * @param row The new value for the row
     */
    public void setRow(final int row) {
        this.row = row;
    }

    /**
     * Sets new values for both the column and row of the {@link Position}.
     * @param column The new value for the column
     * @param row The new value for the row
     */
    public void setCoordinates(final int row, final int column) {
        this.setColumn(column);
        this.setRow(row);
    }

    // Standard methods to compare grid positions.
    @Override
    public int hashCode() {
        return Objects.hash(column, row);
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
        final GridPosition other = (GridPosition) obj;
        return column == other.column && row == other.row;
    }

    @Override
    public String toString() {
        return "GridPosition [column=" + column + ", row=" + row + "]";
    }

}
