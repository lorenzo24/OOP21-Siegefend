package sgf.managers;

import sgf.model.game.Leaderboard;

/**
 * Interface that manages the leaderboard.
 */
public interface LeaderboardManager {

    /**
     * Writes the score of the {@link Player} in the file.
     */
    void writeScore();

    /**
     * Adds the new score of the {@link Player}.
     * @param name Is the name of the {@link Player}.
     * @param score Is the score of the {@link Player}. 
     */
    void addScore(String name, int score);

    /**
     * Returns the {@link Leaderboard}.
     * @return the {@link Leaderboard}.
     */
    Leaderboard getLeaderboard();
}
