package sgf.model.game;

import java.nio.file.Path;
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
    Map<String, Pair<String, Integer>> getMapScore();

    /**
     * Add a record to the map.
     * @param date The date of the game.
     * @param name Is the name of the player.
     * @param score Is the score of the player. 
     */
    void addRecord(String date, String name, int score);

    /**
     * Get the path.
     * @return The path of the leaderboard.
     */
    Path getP();

}
