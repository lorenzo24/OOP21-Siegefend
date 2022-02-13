package sgf.model;
/**
 * 
 * This class represents a simple map implementation.
 *
 */
public class MapImpl implements Map {
    private java.util.Map<GridPosition, Tile> tiles;
    
    @Override
    public Tile getTileFromGridPosition(final GridPosition position) {
        return this.tiles.get(position);
    }

    @Override
    public Tile getTileFromPosition(final Position position) {
        // TODO Auto-generated method stub
        return null;
    }

}
