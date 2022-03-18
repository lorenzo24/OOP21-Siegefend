package sgf.model.game;

/**
 * Class that managed the Classification.
 */
public interface Classification {

    /**
     * Update the current score.
     * @param score Is how many the score must vary.
     */
    void updateScore(int score);

    /**
     * Write the score of the player in the file.
     */
    void writeScore();

    /**
     * Read the score of the older player in the file.
     */
    void readScore();
}
