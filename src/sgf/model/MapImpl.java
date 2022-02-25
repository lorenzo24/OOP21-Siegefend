package sgf.model;

import java.util.HashMap;


/**
 * This class represents a simple map logic with its grid structure.
 */
public class MapImpl implements Map {
    private final java.util.Map<GridPosition, Tile> tiles;      // Contains the links between all the grid positions and the correspondent tiles.
    private int mapSize;        // Is the number of tiles that a side of the grid has.

    /**
     * Simple constructor.
     */
    public MapImpl() {
        this.tiles = new HashMap<>();
    }

    @Override
    public int getMapSize() {
        return this.mapSize;
    }

    @Override
    public java.util.Map<GridPosition, Tile> getTiles() {
        return tiles;
    }

    @Override
    public Tile getTileFromGridPosition(final GridPosition position) {
        return this.tiles.get(position);
    }

    @Override
    public Tile getTileFromPosition(final Position position) {  // TODO Maybe to be deleted?
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setMapSize(final int newMapSize) {
        this.mapSize = newMapSize;
    }
}
