package sgf.controller;

import java.util.List;
import sgf.model.Turret;
import sgf.utilities.GameManager;
import sgf.utilities.PlayerManager;
import sgf.utilities.TurretsLoaderImpl;
import sgf.view.ShopView;

/**
 *
 */
public class ShopControllerImpl implements ShopController {

    private final GameManager gameController;
    private final List<Turret> turrets;
    private Turret selectedTurret;
    private ShopView shopView;
    private boolean isControllerSet;
    /**
     * Creates a new shop controller instance.
     * @param gameController the game controller
     */
    public ShopControllerImpl(final GameManager gameController) {
        this.gameController = gameController;
        this.turrets = new TurretsLoaderImpl().getTurrets();
    }

    @Override
    public PlayerManager getPlayerController() {
        return this.gameController.getPlayerManager();
    }

    @Override
    public List<Turret> getTurretList() {
        return List.copyOf(this.turrets);
    }

    @Override
    public boolean buy(final Turret t) {
        if (this.turrets.contains(t) && this.gameController.getPlayerManager().getPlayer().getCurrentMoney() >= t.getPrice()) {
            this.selectedTurret = t;
            return true;
        }
        return false;
    }

    @Override
    public boolean cancel() {
        if (this.selectedTurret != null) {
            this.selectedTurret = null;
            return true;
        }
        return false;
    }

    @Override
    public boolean completePurchase() {
        if (this.selectedTurret != null) {
            this.gameController.getPlayerManager().changeMoney(-this.selectedTurret.getPrice());
            this.selectedTurret = null;
            return true;
        }
        return false;
    }

    @Override
    public void setView(final ShopView view) {
        if (!isControllerSet) {
            this.isControllerSet = true;
            this.shopView = view;
        }
    }

}
