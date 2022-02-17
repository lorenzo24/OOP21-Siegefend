package sgf.model;

import java.util.Optional;

/**
 * 
 * Class representing the implementation a {@link Map}'s {@link Tile}.
 *
 */
public class TileImpl implements Tile {
    private final TileType tileType;
    private final Position position;
    private final Optional<Direction> tileDirection;

    /**
     * Constructor for the creation of a {@link Tile}.
     * @param tileType Is the type that describes the tile.
     * @param position Is the tile's {@link Position} in the {@link Map}. 
     * @param tileDirection Describes in which {@link Direction} must proceed the {@link Enemy}'s movement.
     */
    public TileImpl(final TileType tileType, final Position position, final Optional<Direction> tileDirection) {
        this.tileType = tileType;
        this.position = position;
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
    public Position getPosition() {
        return this.position;
    }

    @Override
    public boolean canContainTurret() {
        return this.tileType == TileType.GRASS;
    }
}
