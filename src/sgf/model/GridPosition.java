package sgf.model;

import java.util.Objects;

/**
 * Represents a pair of integer coordinates on a grid (row and column).
 */
public class GridPosition {
    private int column;
    private int row;

    /**
     * Creates a new instance of {@code GridPosition}.
     * @param column The column in the grid
     * @param row The row in the grid
     */
    public GridPosition(final int column, final int row) {
        this.row = row;
        this.column = column;
    }

    /**
     * Returns the column of the position.
     * @return the current column of the position
     */
    public int getColumn() {
        return this.column;
    }

    /**
     * Sets a new value for the column of the position.
     * @param column The new value for the column
     */
    public void setColumn(final int column) {
        this.column = column;
    }

    /**
     * Returns the row of the position.
     * @return the current row of the position
     */
    public int getRow() {
        return this.row;
    }

    /**
     * Sets a new value for the row of the position.
     * @param row The new value for the row
     */
    public void setRow(final int row) {
        this.row = row;
    }

    /**
     * Sets new values for both the column and row of the position.
     * @param column The new value for the column
     * @param row The new value for the row
     */
    public void setCoordinates(final int column, final int row) {
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
}
