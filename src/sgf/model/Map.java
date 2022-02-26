package sgf.model;

/**
 * Represents the game map. The map is made of tiles.
 */
public interface Map {
    /**
     * The map has a size, which is the number of tiles of each sides.
     * @return the map size.
     */
    int getMapSize();

    /**
     * The map has a field that contains the links between grid positions and tiles. This method returns it.
     * @return The field tiles in map implementation class.
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
     * This methods sets up the map size.
     * @param newMapSize Is the new size that the map must have.
     */
    void setMapSize(int newMapSize);
}
