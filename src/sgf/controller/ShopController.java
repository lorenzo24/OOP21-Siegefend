package sgf.controller;

import java.util.List;

import sgf.model.Turret;
import sgf.utilities.PlayerManager;

/**
 * Manages the shop.
 */
public interface ShopController {

    /**
     * Returns the associated {@link PlayerManager}.
     * @return the associated {@code PlayerController}
     */
    PlayerManager getPlayerController();

    /**
     * Returns a list of {@link Turret}{@code s} that can be bought from the shop.
     * @return a list of turrets
     */
    List<Turret> getTurretList();

    /**
     * Attempts to buy a turret.
     * @param t the turret to buy
     * @return {@code true} if the purchase was successful, {@code false} otherwise
     */
    boolean buy(Turret t);

    /**
     * Cancels the purchase of a turret.
     * @return {@code true} if the cancellation was successful, 
     *          {@code false} if there is no purchase or if the purchase cannot be reversed 
     */
    boolean cancel();

    /**
     * Alerts the controller that the purchase has been completed successfully.
     * @return {@code true} if the completion was successful, {@code false} otherwise
     */
    boolean completePurchase();
}
