package sgf.model;

import java.util.Optional;

/**
 * Represent a single tile on the map.
 */
public interface Tile {
    /**
     * Returns the tile's type.
     * @return the {@link TileType} associated with the tile
     */
    TileType getTileType();
    /**
     * Returns the direction of movement on the tile. If there is none, an empty {@link Optional}
     * is returned.
     * @return an {@link Optional} containing the {@link Direction} if present, {@code Optional.empty}
     * otherwise
     */
    Optional<Direction> getTileDirection();
    /**
     * Returns the position of the tile on the map.
     * @return the tile's {@link Position} in the map
     */
    Position getPosition();
    /**
     * Indicates whether a {@link Turret} can be placed on the current tile.
     * @return {@code true} if the tile can contain a {@link Turret}, otherwise {@code false}
     */
    boolean canContainTurret();
}
