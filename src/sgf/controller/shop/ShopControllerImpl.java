package sgf.controller.shop;

import java.util.List;
import java.util.Optional;

import sgf.helpers.SimpleTurretsLoader;
import sgf.managers.GameManager;
import sgf.managers.PlayerManager;
import sgf.model.turret.Turret;
import sgf.view.shop.ShopView;

/**
 *
 */
public class ShopControllerImpl implements ShopController {

    private final GameManager gameManager;
    /* private final Shop shop */
    private final List<Turret> turrets; // remove
    private Turret selectedTurret;
    private ShopView shopView;
    private boolean isControllerSet;

    /**
     * Creates a new shop controller instance.
     * @param gameManager the game controller
     */
    public ShopControllerImpl(final GameManager gameManager/*, Shop shop */) {
        this.gameManager = gameManager;
        // this.shop = shop;
        this.turrets = new SimpleTurretsLoader().getTurrets(); // remove
    }

    @Override
    public PlayerManager getPlayerManager() {
        return this.gameManager.getPlayerManager();
    }

    @Override
    public List<Turret> getTurretList() {
        return List.copyOf(this.turrets); // remove
        /* return this.shop.getAvailableTurrets(); */
    }

    @Override
    public Optional<Turret> getSelectedTurret() {
        return Optional.ofNullable(this.selectedTurret);
    }

    @Override
    public void deselectTurret() {
        this.selectedTurret = null;
    }

    @Override
    public boolean trySetSelectedTurret(final Turret t) {
        if (t == null) {
            throw new IllegalArgumentException("Cannot pass null as value for the parameter t.");
        }
        if (this.canBuy(t)) {   // The turret may be set as selected only if it as an option in the shop and if the player has enough money.
            this.selectedTurret = t;
            return true;
        }
        return false;
    }

    @Override
    public boolean canBuy(final Turret t) {
        if (t != null) {
            return this.turrets.contains(t) && this.gameManager.getPlayerManager().getPlayer().getMoney() >= t.getPrice();
        }
        throw new IllegalArgumentException("Cannot pass null as value for the parameter t.");
    }

    @Override
    public Optional<Turret> buy() {
        if (this.selectedTurret == null) {
            return Optional.empty();
        }
        if (this.canBuy(this.selectedTurret)) {
            this.gameManager.getPlayerManager().changeMoney(-this.selectedTurret.getPrice());
            final Turret out = this.selectedTurret;
            this.deselectTurret();
            this.shopView.turretDeselected();
            this.shopView.enableAll();
            return Optional.of(out);
        }
        return Optional.empty(); // If the player doesn't have enough money, the purchase fails.
    }

    @Override
    public void setView(final ShopView view) {
        if (!isControllerSet) {
            this.isControllerSet = true;
            this.shopView = view;
        }
    }

}
