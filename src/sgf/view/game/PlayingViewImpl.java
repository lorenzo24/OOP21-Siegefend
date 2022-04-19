package sgf.view.game;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Label;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import sgf.controller.game.PlayingController;
import sgf.view.bullet.AbstractBulletView;
import sgf.view.shop.AbstractShopView;
import sgf.view.turret.AbstractTurretView;

/**
 * View that contains the game, shop (and upgrade) views.
 */
public class PlayingViewImpl extends AbstractPlayingView {

    /**
     * 
     */
    private static final long serialVersionUID = 3857488179024724379L;

    private PlayingController playingController;
    private final AbstractGameView gameView;
    private final AbstractShopView shopView;
    private final AbstractPlayerView playerView;
    private final AbstractTurretView turretView;
    private final AbstractBulletView bulletView;
    private boolean isControllerSet;


    /**
     * 
     * @param gameView
     * @param shopView
     * @param playerView
     * @param turretView
     * @param bulletView
     */
    public PlayingViewImpl(final AbstractGameView gameView, final AbstractShopView shopView, final AbstractPlayerView playerView, final AbstractTurretView turretView, final AbstractBulletView bulletView) {
        this.gameView = gameView;
        this.shopView = shopView;
        this.playerView = playerView;
        this.turretView = turretView;
        this.bulletView = bulletView;
        this.setVisible(false);
    }

    @Override
    public void setController(final PlayingController controller) {
        if (!isControllerSet) {
            this.isControllerSet = true;
            this.playingController = controller;
        }
    }

    @Override
    public void start() {
        if (isControllerSet) {
            this.setLayout(new BorderLayout());
            this.add(this.playerView, BorderLayout.NORTH);
            this.add(this.gameView, BorderLayout.CENTER);
            this.add(this.shopView, BorderLayout.SOUTH);
            this.addComponentListener(new ComponentListener() {
                @Override
                public void componentShown(final ComponentEvent e) { }

                @Override
                public void componentResized(final ComponentEvent e) {
                    final int size = (int) (PlayingViewImpl.this.getSize().getHeight() / 7);
                    PlayingViewImpl.this.shopView.setItemImgSize(new Dimension(size, size));
                }

                @Override
                public void componentMoved(final ComponentEvent e) { }

                @Override
                public void componentHidden(final ComponentEvent e) { }
            });
            this.setVisible(true);
        } else {
            throw new IllegalStateException("Cannot invoke start() if the controller has not been set.");
        }
    }

    @Override
    public void stop() {
        this.setVisible(false);
    }
}
