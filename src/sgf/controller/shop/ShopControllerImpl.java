package sgf.controller.shop;

import java.util.List;
import java.util.Optional;

import sgf.controller.game.PlayerController;
import sgf.managers.GameManager;
import sgf.model.shop.Shop;
import sgf.model.turret.Turret;
import sgf.view.shop.AbstractShopView;
import sgf.view.shop.ShopView;

/**
 * Controller for managing the shop view.
 */
public class ShopControllerImpl implements ShopController {

    private final GameManager gameManager;
    private final Shop shop;
    private Turret selectedTurret;
    private AbstractShopView shopView;
    private boolean isViewSet;

    /**
     * Creates a new shop controller instance.
     * @param gameManager the game controller
     * @param shop an instance of {@link Shop}
     */
    public ShopControllerImpl(final GameManager gameManager, final Shop shop) {
        this.gameManager = gameManager;
        this.shop = shop;
    }

    @Override
    public PlayerController getPlayerManager() {
        return this.gameManager.getPlayerController();
    }

    @Override
    public List<Turret> getTurretList() {
        return this.shop.getAvailableTurrets();
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
        return this.shop.canBuy(t, this.getPlayerManager().getPlayer());
    }

    @Override
    public Optional<Turret> buy() {
        if (this.selectedTurret == null) {
            return Optional.empty();
        }
        if (this.canBuy(this.selectedTurret)) {
            this.gameManager.getPlayerController().changeMoney(-this.selectedTurret.getPrice());
            final Turret out = this.selectedTurret;
            this.deselectTurret();
            this.shopView.turretDeselected();
            this.shopView.enableAll();
            this.shopView.repaint();
            return Optional.of(out);
        }
        return Optional.empty(); // If the player doesn't have enough money, the purchase fails.
    }

    @Override
    public void setView(final ShopView view) {
        if (!isViewSet) {
            this.isViewSet = true;
            if (view instanceof AbstractShopView) {
                this.shopView = (AbstractShopView) view;
            } else {
                throw new IllegalArgumentException();
            }
        }
    }

    @Override
    public void stop() {
    }

}
