package sgf.helpers;

import sgf.model.level.Level;
import sgf.model.level.LevelNotFoundException;

/**
 * Interface for loading levels to play them.
 */
public interface LevelLoader {
    /**
     * Loads a level from the storage.
     * @param levelID The ID of the level
     * @return A {@link Level} object with all the informations about the level
     * @throws {@link LevelNotFoundException} if no level with the given ID can be found
     */
    Level loadLevel(int levelID);

    /**
     * It count the number of available levels.
     * @return the amount of available levels by reading the number of map files into the res folder.
     */
    int getLevelsNumber();
}
