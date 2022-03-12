package sgf.model.map;

import java.util.HashMap;

/**
 * This class represents a simple map logic with its grid structure.
 */
public class MapImpl implements Map {
    private final java.util.Map<GridPosition, Tile> tiles;      // Contains the links between all the grid positions and the correspondent tiles.
    private int mapSize;        // Is the number of tiles that form a side of the grid.
    // Tiles useful to create enemies.
    private GridPosition startTile;
    private GridPosition endTile;

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
        return this.tiles;
    }

    @Override
    public Tile getTileFromGridPosition(final GridPosition position) {
        return this.tiles.get(position);
    }

    @Override
    public Tile getTileFromPosition(final Position position) {  // TODO Maybe to be deleted?
        return null;
    }

    @Override
    public GridPosition getStartTile() {
        return this.startTile;
    }

    @Override
    public GridPosition getEndTile() {
        return this.endTile;
    }

    @Override
    public void setMapSize(final int newMapSize) {
        this.mapSize = newMapSize;
    }

    @Override
    public void setStartTile(final int row, final int column) {
        this.startTile = new GridPosition(row, column);
    }

    @Override
    public void setEndTile(final int row, final int column) {
        this.endTile = new GridPosition(row, column);
    }
}
