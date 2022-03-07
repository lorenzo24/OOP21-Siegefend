package sgf.model;
import java.util.List;
/**
 * Implementation of a level.
 *
 */
public class LevelImpl implements Level {

    private final List<Wave> waves;
    private int currentWave = 0;
    private final Map map;
    private final int levelID;

    /**
     * Creation of a level.
     * @param waves Are the waves in the level.
     * @param map Is the level map.
     */
    public LevelImpl(final List<Wave> waves, final Map map) {
        this.waves = waves;
        this.map = map;
        this.levelID = 1;
    }

    @Override
    public Map getMap() {
        return this.map;
    }

    @Override
    public List<Wave> getWaves() {
        return List.copyOf(this.waves);
    }

    @Override
    public int getLevelId() {
        return this.levelID;
    }
}
