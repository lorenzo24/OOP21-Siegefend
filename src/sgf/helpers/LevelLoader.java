package sgf.helpers;

import sgf.model.level.Level;

/**
 * Interface for loading levels to play.
 */
public interface LevelLoader {
    /**
     * Loads a {@link Level} from the storage.
     * @param levelID The ID of the level to be loaded.
     * @return a {@link Level} object with all the informations about the level.
     */
    Level loadLevel(int levelID);

    /**
     * It counts the number of available levels.
     * @return the amount of available levels by reading the number of {@link Map} files into the res folder.
     */
    int getLevelsNumber();
}
