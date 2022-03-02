package sgf.utilities;

import sgf.model.Level;

/**
 *
 */
public interface LevelLoader {

    /**
     * Returns the level with the given number.
     * @param level the level number
     * @return a {@link Level} instance
     */
    Level getLevel(int level);
}
