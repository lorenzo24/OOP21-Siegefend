package sgf.controller.shop;

import java.util.List;
import java.util.Optional;

import sgf.controller.Controller;
import sgf.controller.game.PlayerController;
import sgf.model.turret.Turret;
import sgf.view.shop.ShopView;

/**
 * Interface for managing the shop.
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
     * Deselects the currently selected turret. Does nothing if no turret is selected.
     */
    void deselectTurret();

    /**
     * Sets a {@link Turret} as selected.
     * @param t a turret
     * @return {@code true} if the turret was set as selected, {@code false} otherwise
     */
    boolean trySetSelectedTurret(Turret t);

    /**
     * Returns the selected {@link Turret}.
     * @return an {@link Optional} of Turret
     */
    Optional<Turret> getSelectedTurret();

    /**
     * Verifies whether a {@link Turret} can be bought.
     * @param t a turret
     * @return {@code true} if the turret can be bought, {@code false} otherwise
     */
    boolean canBuy(Turret t);

    /**
     * Attempts to buy a turret. The method also decreases the money count of the player if the purchase is successful.
     * @return an {@link Optional} with a {@link Turret} if a turret is selected and the player has
     * enough money to buy it, an empty {@code Optional} otherwise
     */
    Optional<Turret> buy();
}
