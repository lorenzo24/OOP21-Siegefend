package sgf.model;

/**
 * Handles upgrades and selling of turrets.
 */
public interface TurretController {

    /*
     * It might be better to have a different class/interface named Upgrade which
     * contains the new turret (or just the new parameters (Stats) ) which also points
     * to the next upgrade like in a linked list, and then rewrite the methods in this interface 
     * around that.
     */

    /**
     * Returns the current level of the {@link Turret}.
     * @return the current level of the turret.
     */
    int getCurrentUpgradeLevel();

    /**
     * Returns the cost of the last performed upgrade or the initial cost
     * of the turret.
     * @return the cost of the last upgrade or the cost of the turret itself
     */
    int getCurrentUpgradePrice();

    /**
     * Returns the cost of the next upgrade.
     * @return the cost of the next upgrade if present, -1 otherwise.
     */
    int getNextUpgradePrice();

    /**
     * Returns the {@link Turret} with the upgraded statistics.
     * @return the new turret with upgraded statistics
     */
    Turret getNextUpgrade();

    /**
     * Returns whether the upgrade can be purchased or not. 
     * <br />
     * <br />
     * This interface might not be the best place for this method as it involves checking the player's money.
     * @return {@code true} if the upgrade is possible, {@code false} otherwise
     */
    boolean canPurchaseUpgrade();

    /**
     * Sells the turret and returns the amount of money gained from the selling.
     * <br />
     * <br />
     * This interface might not be the best place for this method as it should trigger the removal of the turret
     * from the map.
     * @return the amount of money received from the selling
     */
    int sell();
}
