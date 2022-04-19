package sgf.model.game;

import java.nio.file.Path;
import java.util.Map;

import sgf.utilities.Pair;

/**
 * Interface of the leaderboard.
 */
public interface Leaderboard {

    /**
     * Map of the leaderboard.
     * @return The leaderboard map.
     */
    Map<String, Pair<String, Integer>> getMapScore();

    /**
     * Add a record to the map.
     * @param date The date of the game.
     * @param name Is the name of the {@link Player}.
     * @param score Is the score of the {@link Player}.
     */
    void addRecord(String date, String name, int score);

    /**
     * Gets the path.
     * @return The path of the leaderboard.
     */
    Path getPath();

}
