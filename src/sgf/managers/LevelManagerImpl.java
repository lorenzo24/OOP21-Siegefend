package sgf.managers;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import sgf.model.enemies.Enemy;
import sgf.model.level.Level;
import sgf.model.level.Wave;
import sgf.model.map.Map;

/**
 * Manager of a level: it says what is the next wave and the next enemy of the single and current wave.
 */
public class LevelManagerImpl implements LevelManager {

    private final Level level;
    private final Map map;
    private final Iterator<Wave> waveIter;
    private Iterator<Enemy> enemyIter;
    private Optional<Wave> currentWave;

    /**
     * Initializes the field for the single level. 
     * @param level Is the current level.
     */
    public LevelManagerImpl(final Level level) {
        this.level = level;
        this.map = level.getMap(); // Gets the map from the current level.
        this.waveIter = level.getWaves().iterator(); // Gets the waves from the current level.
        this.loadWave();
    }

    private void loadWave() {
        if (this.waveIter.hasNext()) { // If there is an other wave.
            this.currentWave = Optional.of(this.waveIter.next()); // Takes the new wave. 
            this.enemyIter = this.getCurrentWave().getEnemies().iterator(); // Sets up the enemy iterator.
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public List<Wave> getWaves() {
        return List.copyOf(this.level.getWaves());
    }

    @Override
    public int getTotalWaves() {
        return this.level.getNumberOfWaves();
    }

    @Override
    public Level getCurrentLevel() {
        return this.level;
    }

    @Override
    public Wave getCurrentWave() {
        return this.currentWave.orElseThrow();
    }

    @Override
    public void nextWave() {
        this.loadWave();
    }

    @Override
    public boolean hasNextWave() {
        return this.waveIter.hasNext();
    }

    @Override
    public Optional<Enemy> getNextEnemy() {
        if (this.hasNextEnemy()) {
            return Optional.of(this.enemyIter.next());
        }
        return Optional.empty(); 
    }

    @Override
    public boolean hasNextEnemy() {
        return this.enemyIter.hasNext();
    }

    @Override
    public Map getMap() {
        return this.map;
    }
}
