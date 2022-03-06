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

    @Override
    public void startLevel(Level l) {

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
    public void spawnNextEnemy() {
        // TODO Auto-generated method stub

    }

    @Override
    public void removeEnemy(Enemy e) {
        // TODO Auto-generated method stub

    }

}
