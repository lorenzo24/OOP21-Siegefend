package sgf.model.game;

/**
 * Class that managed the Classification.
 */
public interface Classification {

    /**
     * Write the score of the player in the file.
     */
    void writeScore();

    /**
     * Read the score of the older player in the file.
     */
    void readScore();

    /**
     * Add a player in the classification.
     * @param name Is the name of the player.
     * @param points Are the point of its game.
     */
    void addPlayer(String name, String points);
}
