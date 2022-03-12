package sgf.model.map;

/**
 * Represents the game map. The map is made has a structure of matrix of tiles.
 */
public interface Map {
    /**
     * The map has a size, which is the number of tiles of each sides.
     * @return the map size.
     */
    int getMapSize();

    /**
     * The map has a field that contains the links between grid positions and tiles. This method returns it.
     * @return the field tiles in map implementation class.
     */
    java.util.Map<GridPosition, Tile> getTiles();

    /**
     * This method helps getting a {@link Tile} from a grid position.
     * @param position Is the position in the grid of the interested {@link Tile}.
     * @return the tile from the given grid position.
     */
    Tile getTileFromGridPosition(GridPosition position);

    /**
     * This method helps getting a {@link Tile} from a given {@link Position}.
     * @param position Is the position of the interested {@link Tile}.
     * @return the tile from the given {@link Position}.
     */
    Tile getTileFromPosition(Position position);

    /**
     * The map has e reference to the tile from which the enemies movement start.
     * @return the GridPosition of the tile from which starts the enemies movement.
     */
    GridPosition getStartTile();

    /**
     * The map has also a field that denotes which is the tile that represents the end of the enemies movement.
     * @return the GridPosition of the tile that denotes the end of the enemies movement.
     */
    GridPosition getEndTile();

    /**
     * Standard setter for the field that represents the tile from which the enemies star to move.
     * @param row Is the row of the tile into the grid.
     * @param column Is the column of the tile into the grid.
     */
    void setStartTile(int row, int column);

    /**
     * Standard setter for the field that represents the tile in which the enemies movement stops.
     * @param row Is the row of the tile into the grid.
     * @param column Is the column of the tile into the grid.
     */
    void setEndTile(int row, int column);

    /**
     * This method sets up the map size.
     * @param newMapSize Is the new size that the map must have.
     */
    void setMapSize(int newMapSize);
}
