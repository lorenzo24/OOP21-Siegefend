package sgf.model.game;

/**
 * 
 *
 */
public interface Player {

    /**
     * Returns the maximum amount of HP the player can have. <br/>
     * This value is also the starting HP.
     * @return the max amount of HP of the player.
     */
    int getMaxHP();

    /**
     * Returns the current HP.
     * @return the current HP.
     */
    int getCurrentHP();

    /**
     * Sets a new HP amount.
     * @param hp the new HP amount.
     */
    void setCurrentHP(int hp);

    /**
     * Decreases current HP by the default damage taken.
     */
    void decreaseCurrentHP();

    /**
     * Decreases current HP by a specified amount of damage. Overload of decreaseCurrentHP().
     * @param amount of damage taken.
     */
    void decreaseCurrentHP(int amount);

    /**
     * @return the current money amount.
     */
    int getMoney();

    /**
     * Sets a new money amount.
     * @param money the new money amount.
     */
    void setMoney(int money);

    /**
     * Getter for score.
     * @return Player's current score.
     */
    int getScore();

    /**
     * Sets score to a new value.
     * @param score New score.
     */
    void setScore(int score);

    /**
     * Getter for player's name.
     * @return Player's name.
     */
    int getPlayerName();

    /**
     * Sets player's name.
     * @param username is the player's name.
     */
    void setPlayerName(String username);
}
