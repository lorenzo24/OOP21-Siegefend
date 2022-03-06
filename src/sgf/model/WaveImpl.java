package sgf.model;

import java.util.List;

/**
 * Wave class.
 */
public class WaveImpl implements Wave {

    private final List<Enemy> enemies;
    private int enemiesCount;

    /**
     * Initialize the thi list of enemies.
     * @param enemies
     */
    public WaveImpl(final List<Enemy> enemies) {
        this.enemies = enemies;
        this.enemiesCount = enemies.size();
    }

    @Override
    public List<Enemy> getEnemies() {
        return List.copyOf(this.enemies);
    }

    @Override
    public int getRemainingEnemies() {
        return this.enemies.size();
    }

    @Override
    public void decreaseEnemiesCount() {
        this.enemiesCount--;
    }

    @Override
    public boolean isWaveOver() {
        return this.enemiesCount == 0;
    }

}
