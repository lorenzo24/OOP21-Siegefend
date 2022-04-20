package sgf.model.level;
import java.util.List;

import sgf.model.map.Map;
/**
 * Implementation of a level.
 *
 */
public class LevelImpl implements Level {

    private final List<Wave> waves;
    private final Map map;
    private final int levelID;

    /**
     * Creation of a level.
     * @param waves Are the waves in the level.
     * @param map Is the level map.
     * @param levelID Is the {@link Level} ID.
     */
    public LevelImpl(final List<Wave> waves, final Map map, final int levelID) {
        this.waves = waves;
        this.map = map;
        this.levelID = levelID;
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

    @Override
    public int getNumberOfWaves() {
        return this.waves.size();
    }
}
