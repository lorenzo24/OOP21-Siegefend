package sgf.controller;

import sgf.model.Turret;
import sgf.utilities.GameManager;
import sgf.utilities.PlayerManager;

/**
 * 
 */
public class PlayingControllerImpl implements PlayingController {

    private final GameManager gameManager;
    private final PlayerManager playerManager;

    /**
     * 
     */
    public PlayingControllerImpl() {
        this.gameManager = null;
        // this.gameManager = new GameManagerImpl();
        this.playerManager = null;
        // this.playerManager = new PlayerManagerImpl();
    }

    @Override
    public GameManager getGameManager() {
        return this.gameManager;
    }

    @Override
    public PlayerManager getPlayerManager() {
        return this.playerManager;
    }

    @Override
    public void showUpgrades(final Turret t) {
        // TODO Auto-generated method stub

    }

    @Override
    public void hideUpgrades() {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean buy(final Turret t) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean upgradeTurret(final Turret upgrade) {
        // TODO Auto-generated method stub
        return false;
    }
}
