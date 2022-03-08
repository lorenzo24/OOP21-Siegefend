package sgf.controller;

import java.util.List;

import sgf.model.Turret;
import sgf.utilities.GameManager;
import sgf.utilities.PlayerManager;
import sgf.utilities.SimpleTurretsLoader;
import sgf.view.ShopView;

/**
 *
 */
public class ShopControllerImpl implements ShopController {

    private final GameManager gameManager;
    private final List<Turret> turrets;
    private Turret selectedTurret;
    private ShopView shopView;
    private boolean isControllerSet;
    /**
     * Creates a new shop controller instance.
     * @param gameManager the game controller
     */
    public ShopControllerImpl(final GameManager gameManager) {
        this.gameManager = gameManager;
        this.turrets = new SimpleTurretsLoader().getTurrets();
    }

    @Override
    public PlayerManager getPlayerController() {
        return this.gameManager.getPlayerManager();
    }

    @Override
    public List<Turret> getTurretList() {
        return List.copyOf(this.turrets);
    }

    @Override
    public boolean buy(final Turret t) {
        if (this.turrets.contains(t) && this.gameManager.getPlayerManager().getPlayer().getCurrentMoney() >= t.getPrice()) {
            this.selectedTurret = t;
            return true;
        }
        return false;
    }

    @Override
    public boolean cancel() {
        if (this.selectedTurret != null) {
            this.selectedTurret = null;
            this.completePurchase();
            return true;
        }
        return false;
    }

    @Override
    public boolean completePurchase() {
        if (this.selectedTurret != null) {
            this.gameManager.getPlayerManager().changeMoney(-this.selectedTurret.getPrice());
            this.selectedTurret = null;
            this.shopView.enableAll();
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
