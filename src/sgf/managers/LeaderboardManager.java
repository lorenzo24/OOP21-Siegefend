package sgf.managers;

import sgf.model.game.Leaderboard;

/**
 * Interface that managed the leaderboard.
 */
public interface LeaderboardManager {

    /**
     * Write the score of the player in the file.
     */
    void writeScore();

    /**
     * Add the new score of the player.
     * @param name Is the name of the player.
     * @param score Is the score of the player. 
     */
    void addScore(String name, int score);

    /**
     * Returns the leaderboard.
     * @return the leaderboard.
     */
    Leaderboard getLeaderboard();
}
