package sgf.model;

import java.util.Optional;

/**
 * Class representing the implementation a {@link Map}'s {@link Tile}.
 */
public class TileImpl implements Tile {
    private final TileType tileType;    // Type of the tile (grass, water, path...).
    private Optional<Direction> tileDirection;  // Field to be passed to the enemy for its movement.

    /**
     * Constructor for the creation of a {@link Tile}.
     * @param tileType Is the type that describes the tile.
     */
    public TileImpl(final TileType tileType) {
        this.tileType = tileType;
        this.tileDirection = Optional.empty();
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

    @Override
    public void setDirection(final Direction direction) {
        this.tileDirection = Optional.of(direction);
    }
}
