package sgf.model;

import java.util.Optional;

/**
 * 
 * Represent a single tile. All the tiles together form the {@link Map}. 
 *
 */
public interface Tile {
    /**
     * All tiles have a type. This method returns the type of a specific tile.
     * @return the tile's type.
     */
    TileType getTileType();
    /**
     * Many tiles could have a {@link Direction}, a parameter that helps the enemies
     * movement. This method returns the direction of a specific tile. 
     * @return the {@link Optional} {@link Direction} of the tile. This optional could be 
     * empty, in fact if the tile doesn't compose the path it doesn't have any direction.
     */
    Optional<Direction> getTileDirection();
    /**
     * Every tile has a specific {@link Position} in the map, so this method helps getting
     * it.
     * @return the tile's {@link Position} in the map.
     */
    Position getPosition();
    /**
     * The {@link Player} can place a {@link Turret} only in specific tiles, not
     * everywhere he wants. This method indicates if the tile can contain a turret.
     * @return true if the tile can contain a {@link Turret}, otherwise returns false.
     */
    boolean canContainTurret();
}
