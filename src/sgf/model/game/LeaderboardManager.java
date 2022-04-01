package sgf.model.game;

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
}
