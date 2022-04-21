package sgf.controller.game;

import java.awt.Dimension;

import sgf.managers.GameManager;
import sgf.model.turret.Turret;
import sgf.view.game.AbstractPlayingView;
import sgf.view.game.PlayingView;

/**
 * Managed the playing view.
 */
public class PlayingControllerImpl implements PlayingController {

    private AbstractPlayingView playingView;
    private final GameManager gameManager;
    private boolean isControllerSet;

    /**
     * Constructor that set the gameManager.
     * @param gameManager Is the gameManager.
     */
    public PlayingControllerImpl(final GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @Override
    public GameManager getGameManager() {
        return this.gameManager;
    }

    @Override
    public PlayerController getPlayerController() {
        return this.gameManager.getPlayerController();
    }

    @Override
    public Dimension getViewSize() {
        if (this.playingView != null) {
            return this.playingView.getSize();
        } else {
            return new Dimension();
        }
    }

    @Override
    public void showUpgrades(final Turret t) {
        throw new UnsupportedOperationException();

    }

    @Override
    public void hideUpgrades() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean buy(final Turret t) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean upgradeTurret(final Turret upgrade) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setView(final PlayingView view) {
        if (!isControllerSet) {
            if (view instanceof AbstractPlayingView) {
                this.isControllerSet = true;
                this.playingView = (AbstractPlayingView) view;
            } else {
                throw new IllegalArgumentException("Argument must be subclass of AbstractPlayingView");
            }
        }
    }

    @Override
    public void stop() {
    }
}
