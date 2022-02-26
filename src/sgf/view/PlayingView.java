package sgf.view;

import java.util.List;

import javax.swing.JPanel;

import sgf.controller.PlayingController;
import sgf.controller.PlayingControllerImpl;

/**
 * View that contains the game, shop (and upgrade) views.
 */
public class PlayingView extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = 57452928064051033L;

    private static final int MAP_SIZE = 600;

    private final PlayingController playingController;
    private final GameView gameView;
    private final ShopView shopView;

    public PlayingView() {
        this.playingController = new PlayingControllerImpl();
        this.gameView = new GameView(null, MAP_SIZE, List.of());
        this.shopView = new ShopView(this.playingController.getGameManager());
    }
}
