package sgf.utilities;

import sgf.controller.map.MapController;
import sgf.controller.thread.GameController;
import sgf.view.EnemyController;
import sgf.view.EnemyView;
import sgf.view.GameView;
import sgf.view.MapView;

/**
 *
 */
public final class AppStart {

    private AppStart() {
    }

    /**
     * 
     * @param args
     */
    public static void main(final String[] args) {
        final GameManager gameManager = null;
        final PlayerManager playerManager = null;
        final GameController gameController = null;
        final GameView gameView = null;
        final MapController mapController = null;
        final MapView mapView = null;
        final EnemyController enemyController = null;
        final EnemyView enemyView = null;
        /**
         * Linking.
         */
        gameController.setView(gameView);
        gameView.setController(gameController);
        mapController.setView(mapView);
        mapView.setController(mapController);
        enemyController.setView(enemyView);
        enemyView.setController(enemyController);
    }
}
