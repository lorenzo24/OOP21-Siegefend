package sgf.model;

import java.util.List;

/**
 *
 */
public class LevelImp implements Level {

    private final List<Wave> waves;

    /**
     * Creation of a level.
     * @param waves Are the waves in the level.
     */
    public LevelImp(final List<Wave> waves) {
        this.waves = waves;
    }

    @Override
    public Map getMap() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Wave> getWaves() {
        return List.copyOf(this.waves);
    }

}
