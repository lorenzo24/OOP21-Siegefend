package sgf.model.game;

/**
 * 
 *
 */
public interface Player {

    /**
     * Returns the maximum amount of HP the player can have. <br/>
     * This value is also the starting HP.
     * @return the max amount of HP of the player
     */
    int getMaxHP();

    /**
     * Returns the current HP.
     * @return the current HP
     */
    int getCurrentHP();

    /**
     * Sets a new HP amount.
     * @param hp the new HP amount
     */
    void setCurrentHP(int hp);

    /**
     * @return the current money amount
     */
    int getCurrentMoney();

    /**
     * Sets a new money amount.
     * @param money the new money amount
     */
    void setCurrentMoney(int money);
}
