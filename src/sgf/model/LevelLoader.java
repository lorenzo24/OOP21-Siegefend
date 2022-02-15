package sgf.model;

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
}
