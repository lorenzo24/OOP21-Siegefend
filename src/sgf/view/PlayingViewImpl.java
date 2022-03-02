package sgf.view;

import java.awt.BorderLayout;

import sgf.controller.PlayingController;

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
        this.setLayout(new BorderLayout());
        this.add(this.gameView, BorderLayout.CENTER);
        this.add(shopView, BorderLayout.SOUTH);
    }

    @Override
    public void setController(final PlayingController controller) {
        if (!isControllerSet) {
            this.isControllerSet = true;
            this.playingController = controller;
        }
    }
}
