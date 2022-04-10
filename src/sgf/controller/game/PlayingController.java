package sgf.controller.game;

import java.awt.Dimension;

import sgf.controller.Controller;
import sgf.managers.GameManager;
import sgf.model.turret.Turret;
import sgf.view.game.PlayingView;

/**
 * Manages the view containing the game, the shop (and the upgrade shop).
 */
public interface PlayingController extends Controller<PlayingView> {

    /**
     * Returns the {@link GameManager}.
     * @return the game manager
     */
    GameManager getGameManager();       // TODO: remove?

    /**
     * Returns the manager for the player.
     * @return the manager for the player
     */
    PlayerController getPlayerManager();   // TODO: remove?

    /**
     * Returns the size of the view. 
     * @return the size of the view
     */
    Dimension getViewSize();

    /**
     * Returns the controller for the shop.
     * @return the controller for the shop
     */
    //UpgradeController getUpgradeController();

    /**
     * Shows the upgrade view for the selected turret.
     * @param t The selected turret
     */
    void showUpgrades(Turret t);        // TODO: remove?

    /**
     * Removes the upgrade view displayed.
     */
    void hideUpgrades();        // TODO: remove?

    /**
     * Attempts to buy a turret from the shop.
     * @param t the turret to buy
     * @return {@code true} if the purchase was successful, {@code false} otherwise
     */
    boolean buy(Turret t);      // TODO: remove?

    /**
     * Upgrades the selected turret with the chosen upgrade.
     * @param upgrade The selected upgrade
     * @return {@code true} if the purchase/upgrade was successful, {@code false} otherwise
     */
    boolean upgradeTurret(Turret upgrade);      // TODO: remove?
}
