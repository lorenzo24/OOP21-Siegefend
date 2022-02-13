package sgf.model;

/**
 * Creates instances of the {@link Bullet} interface.
 */
public interface BulletFactory {
    /**
     * Creates a new {@link Bullet} instance with an initial position, target and other parameters.
     * @param startPos The initial position of the bullet
     * @param target The target of the bullet
     * @return a {@link Bullet} instance
     */
    Bullet createBullet(Position startPos, Position target);
}
