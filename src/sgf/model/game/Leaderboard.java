package sgf.model.game;

import java.nio.file.Path;
import java.util.Date;
import java.util.Map;

import sgf.utilities.Pair;

/**
 * Interface for the leaderboard.
 */
public interface Leaderboard {

    /**
     * Map of the classification.
     * @return The classification map.
     */
    Map<Date, Pair<String, Integer>> getMapScore();

    /**
     * Add a record to the map.
     * @param date The date of the game.
     * @param player Are the name and the score. 
     */
    void addRecord(Date date, Pair<String, Integer> player);

    /**
     * Get the path.
     * @return The path of the leaderboard.
     */
    Path getP();

}
