package sgf.controller.shop;

import java.util.List;

import sgf.controller.Controller;
import sgf.controller.game.PlayerController;
import sgf.model.turret.Turret;
import sgf.view.shop.ShopView;

/**
 * Manages the shop.
 */
public interface ShopController extends Controller<ShopView> {

    /**
     * Returns the associated {@link PlayerController}.
     * @return the associated {@code PlayerController}
     */
    PlayerController getPlayerManager();

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
