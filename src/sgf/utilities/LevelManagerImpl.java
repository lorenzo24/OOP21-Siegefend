package sgf.utilities;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import sgf.model.Level;
import sgf.model.Wave;
import sgf.model.enemies.Enemy;

/**
 * Manager of one level it say what is the next wave and the next enemy of the single wave.
 */
public class LevelManagerImpl implements LevelManager {

    private final Level level;
    private final List<Wave> waveList;
    private final Iterator<Wave> waveIter;
    private Iterator<Enemy> enemyIter;
    private Optional<Wave> currentWave;

    /**
     * Initialize the fild for the single level. 
     * @param level Is the current level.
     */
    public LevelManagerImpl(final Level level) {
        this.level = level;
        this.waveList = level.getWaves();
        waveIter = waveList.iterator();
    }

    @Override
    public List<Wave> getWaves() {
        return this.level.getWaves();
    }

    @Override
    public int getTotalWaves() {
        return this.waveList.size();
    }

    @Override
    public Level getCurrentLevel() {
        return this.level;
    }

    @Override
    public Wave getCurrentWave() {
        return this.currentWave.get();
    }

    @Override
    public Optional<Wave> getNextWave() {
        if (waveIter.hasNext()) {
            this.currentWave = Optional.of(this.waveIter.next());
            this.enemyIter = this.getCurrentWave().getEnemies().iterator();
        } else {
            this.currentWave = Optional.empty();
        }
        return this.currentWave;
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
}
