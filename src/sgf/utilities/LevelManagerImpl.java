package sgf.utilities;

import java.util.List;
import java.util.Optional;

import sgf.model.Enemy;
import sgf.model.Level;
import sgf.model.Wave;

/**
 * 
 *
 */
public class LevelManagerImpl implements LevelManager {

    private final Level level; 

    /**
     * 
     * @param level
     */
    public LevelManagerImpl(final Level level) {
        this.level = level;
    }

    @Override
    public void startLevel() {
        // DELETE
    }

    @Override
    public List<Wave> getWaves() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getTotalWaves() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Level getCurrentLevel() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Wave getCurrentWave() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getCurrentWaveNumber() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Optional<Wave> getNextWave() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean hasNextWave() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Optional<Enemy> getNextEnemy() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean hasNextEnemy() {
        // TODO Auto-generated method stub
        return false;
    }

}
