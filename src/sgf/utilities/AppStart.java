package sgf.utilities;

import java.util.ArrayList;
import java.util.List;

import sgf.controller.EnemyController;
import sgf.controller.EnemyControllerImpl;
import sgf.controller.PlayingController;
import sgf.controller.PlayingControllerImpl;
import sgf.controller.ShopController;
import sgf.controller.ShopControllerImpl;
import sgf.controller.map.MapController;
import sgf.controller.map.MapControllerImpl;
import sgf.controller.thread.GameController;
import sgf.controller.thread.GameControllerImpl;
import sgf.model.Enemy;
import sgf.model.Map;
import sgf.view.AbstractEnemyView;
import sgf.view.AbstractGameView;
import sgf.view.AbstractMapView;
import sgf.view.AbstractPlayingView;
import sgf.view.AbstractShopView;
import sgf.view.EnemyViewImpl;
import sgf.view.GameViewImpl;
import sgf.view.MapViewImpl;
import sgf.view.PlayingViewImpl;
import sgf.view.ScreenGame;
import sgf.view.ShopViewImpl;

/**
 *
 */
public final class AppStart {

    private static final int CELL_SIZE = 80;

    private AppStart() {
    }

    /**
     * 
     * @param args
     */
    public static void main(final String[] args) {
        final GameManager gameManager = null;
        final PlayerManager playerManager = null;
        final LevelManager levelManager = null;

        /*
         * At the start only the menu, settings and levels view will be created.
         * All these other views and controllers will be created when someone clicks on a level.
         */

        final MapController mapController = new MapControllerImpl(CELL_SIZE);
        final AbstractMapView mapView = new MapViewImpl(mapController.getMap(), CELL_SIZE);
        final EnemyController enemyController = new EnemyControllerImpl(mapController);
        final AbstractEnemyView enemyView = new EnemyViewImpl(mapController.getMap().getMapSize(), CELL_SIZE);
        final GameController gameController = new GameControllerImpl();
        final AbstractGameView gameView = new GameViewImpl(mapView, enemyView);
        final ShopController shopController = new ShopControllerImpl(gameManager);
        final AbstractShopView shopView = new ShopViewImpl(gameManager);
        final PlayingController playingController = new PlayingControllerImpl(gameManager, playerManager);
        final AbstractPlayingView playingView = new PlayingViewImpl(gameView, shopView);
        /**
         * Linking.
         */
        gameController.setView(gameView);
        gameView.setController(gameController);
        mapController.setView(mapView);
        mapView.setController(mapController);
        enemyController.setView(enemyView);
        enemyView.setController(enemyController);
        shopController.setView(shopView);
        shopView.setController(shopController);
        playingController.setView(playingView);
        playingView.setController(playingController);

        new ScreenGame(playingView);
    }
}
