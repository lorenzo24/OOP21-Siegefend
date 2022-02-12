package sgf.model;
/**
 * 
 * Represents the game map. The map is made of tiles.
 *
 */
public interface Map {
    /**
     * This method helps getting a {@link Tile} from a given coordinates.
     * @param coordinates Is the coordinates of the interested {@link Tile}.
     * @return the tile from the given coordinates.
     */
    Tile getTileFromCoordinates(Coordinate coordinates);
    /**
     * This method helps getting a {@link Tile} from a given {@link Position}.
     * @param position Is the position of the interested {@link Tile}.
     * @return the tile from the given {@link Position}.
     */
    Tile getTileFromPosition(Position position);
}
