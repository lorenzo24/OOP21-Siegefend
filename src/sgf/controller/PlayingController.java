package sgf.controller;

import sgf.model.Turret;
import sgf.utilities.GameManager;
import sgf.utilities.PlayerManager;
import sgf.view.PlayingView;

/**
 * Manages the view containing the game, the shop (and the upgrade shop).
 */
public interface PlayingController extends Controller<PlayingView> {

    /**
     * Returns the {@link GameManager}.
     * @return the game manager
     */
    GameManager getGameManager();

    /**
     * Returns the manager for the player.
     * @return the manager for the player
     */
    PlayerManager getPlayerManager();

    /**
     * Returns the controller for the shop.
     * @return the controller for the shop
     */
    //UpgradeController getUpgradeController();

    /**
     * Shows the upgrade view for the selected turret.
     * @param t The selected turret
     */
    void showUpgrades(Turret t);

    /**
     * Removes the upgrade view displayed.
     */
    void hideUpgrades();

    /**
     * Attempts to buy a turret from the shop.
     * @param t the turret to buy
     * @return {@code true} if the purchase was successful, {@code false} otherwise
     */
    boolean buy(Turret t);

    /**
     * Upgrades the selected turret with the chosen upgrade.
     * @param upgrade The selected upgrade
     * @return {@code true} if the purchase/upgrade was successful, {@code false} otherwise
     */
    boolean upgradeTurret(Turret upgrade);
}
