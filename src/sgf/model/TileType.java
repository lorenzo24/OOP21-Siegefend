package sgf.model;
/**
 * 
 * Describes the various and different types of a {@link Tile} in the {@link Map}.
 *
 */
public enum TileType {
    /**
     * This type denotes that the {@link Tile} is the beginning of the path, that is the
     * tile from which the enemies will appear.
     */
    START_PATH,
    /**
     * This type denotes that the {@link Tile} composes the path.
     */
    PATH,
    /**
     * This type denotes that the {@link Tile} represents the end of the path, that is 
     * the tile in which the enemies will disappear.
     */
    END_PATH,
    /**
     * This type denotes that on the {@link Tile} you can place a {@link Turret}.
     */
    BUILD,
    /**
     * This type denotes that on the {@link Tile} you cannot place a {@link Turret}.
     */
    NO_BUILD;
}
