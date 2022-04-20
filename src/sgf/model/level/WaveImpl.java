package sgf.model.level;

import java.util.List;

import sgf.model.enemies.Enemy;

/**
 * Wave class.
 */
public class WaveImpl implements Wave {

    private final List<Enemy> enemies;

    /**
     * Initializes the list of enemies.
     * @param enemies The enemies list.
     */
    public WaveImpl(final List<Enemy> enemies) {
        this.enemies = enemies;
    }

    @Override
    public List<Enemy> getEnemies() {
        return List.copyOf(this.enemies);
    }
}
