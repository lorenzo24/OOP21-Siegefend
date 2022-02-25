package sgf.model;

import java.util.Optional;

/**
 * Class representing the implementation a {@link Map}'s {@link Tile}.
 */
public class TileImpl implements Tile {
    private final TileType tileType;    // Type of the tile (grass, water, path...).
    private final Optional<Direction> tileDirection;

    /**
     * Constructor for the creation of a {@link Tile}.
     * @param tileType Is the type that describes the tile.
     * @param tileDirection Describes in which {@link Direction} must proceed the {@link Enemy}'s movement.
     */
    public TileImpl(final TileType tileType, final Optional<Direction> tileDirection) {
        this.tileType = tileType;
        this.tileDirection = tileDirection;
    }

    @Override
    public TileType getTileType() {
        return this.tileType;
    }

    @Override
    public Optional<Direction> getTileDirection() {
        return this.tileDirection;
    }

    @Override
    public boolean canContainTurret() {
        return this.tileType == TileType.GRASS;
    }
}
