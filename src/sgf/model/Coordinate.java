package sgf.model;

import java.util.Objects;

/**
 * 
 * Represents a coordinate, an useful way to locate every entity's {@link Position} in 
 * the map.
 *
 */
public class Coordinate {
    private final int row;
    private final int column;
    
    /**
     * This constructor creates a new instance for a coordinate.
     * @param row Is the coordinate's row.
     * @param column Is the coordinate's column.
     */
    public Coordinate(final int row, final int column) {
        this.row = row;
        this.column = column;
    }
    
    /**
     * This method returns the coordinate's row.
     * @return the row.
     */
    public int getRow() {
        return this.row;
    }
    
    /**
     * This method returns the coordinate's column.
     * @return the column.
     */
    public int getColumn() {
        return this.column;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
    
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        final Coordinate other = (Coordinate) obj;
        return row == other.row && column == other.column; 
    }
}
