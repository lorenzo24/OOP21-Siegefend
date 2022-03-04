package sgf.model;

import java.util.Optional;

/**
 * Represents a single tile on the map.
 */
public interface Tile {
    /**
     * Every tile has a type (grass, path, water...).
     * @return the {@link TileType} associated with the tile.
     */
    TileType getTileType();

    /**
     * Every tile denotes the direction of movement. If there is none, in contains an empty {@link Optional} anyway.
     * @return an {@link Optional} containing the {@link Direction} if present, {@code Optional.empty} otherwise.
     */
    Optional<Direction> getTileDirection();

    /**
     * Denotes whether a {@link Turret} can be placed on the current tile.
     * @return {@code true} if the tile can contain a {@link Turret}, {@code false} otherwise.
     */
    boolean canContainTurret();

    /**
     * Method that sets up the tile's direction. It will be useful to enemy's movement pathfinding.
     * @param direction Is the direction that the interested tile must have.
     */
    void setDirection(Direction direction);
}
