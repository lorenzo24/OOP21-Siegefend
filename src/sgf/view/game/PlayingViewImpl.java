package sgf.view.game;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import sgf.controller.game.PlayingController;
import sgf.view.shop.AbstractShopView;

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
    private boolean isControllerSet;

    /**
     * 
     * @param gameView
     * @param shopView
     */
    public PlayingViewImpl(final AbstractGameView gameView, final AbstractShopView shopView) {
        this.gameView = gameView;
        this.shopView = shopView;
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
            this.add(this.gameView, BorderLayout.CENTER);
            this.add(shopView, BorderLayout.SOUTH);
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
}
