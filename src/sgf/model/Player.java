package sgf.model;

/**
 * 
 *
 */
public interface Player {
    /**
     * Returns the current HP.
     * @return the current HP.
     */
    int getCurrentHP();

    /**
     * Sets a new HP amount.
     * @param hp the new HP amount
     */
    void setCurrentHP(int hp);

    /**
     * @return the current money amount.
     */
    int getCurrentMoney();

    /**
     * Sets a new money amount.
     * @param money the new money amount
     */
    void setCurrentMoney(int money);
}
