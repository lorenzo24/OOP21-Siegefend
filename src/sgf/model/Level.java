package sgf.model;

import java.util.List;

/**
 * Represents a level of play.
 */
public interface Level {

    /**
     * Method that returns the {@link Map} of the level.
     * @return the level's {@link Map}.
     */
    Map getMap();

    /**
     * Returns a list containing all the waves of enemies in the level.
     * @return a {@link List} containing all the waves.
     */
    List<Wave> getWaves();

    /**
     * Simple getter for the field that contains the level ID.
     * @return the ID of the level.
     */
    int getLevelId();
}
