package sgf.model;

import java.util.List;
/**
 * Implementation of a level.
 *
 */
public class LevelImp implements Level {

    private final List<Wave> waves;
    private int currentWave = 0;
    private final Map map;

    /**
     * Creation of a level.
     * @param waves Are the waves in the level.
     * @param map Is the level map.
     */
    public LevelImp(final List<Wave> waves, final Map map) {
        this.waves = waves;
        this.map = map;
    }

    @Override
    public Map getMap() {
        return this.map;
    }

    @Override
    public List<Wave> getWaves() {
        return List.copyOf(this.waves);
    }

}
