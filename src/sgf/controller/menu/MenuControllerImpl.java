package sgf.controller.menu;

import java.util.List;

import sgf.controller.bullet.BulletController;
import sgf.controller.enemy.EnemyController;
import sgf.controller.enemy.EnemyControllerImpl;
import sgf.controller.game.GameController;
import sgf.controller.game.GameControllerImpl;
import sgf.controller.game.MusicController;
import sgf.controller.game.MusicControllerImpl;
import sgf.controller.game.PlayerController;
import sgf.controller.game.PlayerControllerImpl;
import sgf.controller.game.PlayingController;
import sgf.controller.game.PlayingControllerImpl;
import sgf.controller.map.MapController;
import sgf.controller.map.MapControllerImpl;
import sgf.controller.shop.ShopController;
import sgf.controller.shop.ShopControllerImpl;
import sgf.controller.turret.TurretController;
import sgf.controller.turret.TurretControllerImpl;
import sgf.helpers.LevelLoader;
import sgf.helpers.LevelLoaderImpl;
import sgf.helpers.MapLoaderImpl;
import sgf.helpers.TurretsLoader;
import sgf.helpers.TurretsLoaderImpl;
import sgf.helpers.WavesLoaderImpl;
import sgf.managers.GameManager;
import sgf.managers.GameManagerImpl;
import sgf.managers.LeaderboardManager;
import sgf.managers.LeaderboardManagerImpl;
import sgf.managers.LevelManager;
import sgf.managers.LevelManagerImpl;
import sgf.model.game.Player;
import sgf.model.game.PlayerImpl;
import sgf.model.level.Level;
import sgf.model.level.LevelImpl;
import sgf.model.level.Wave;
import sgf.model.map.Map;
import sgf.model.shop.Shop;
import sgf.model.shop.ShopImpl;
import sgf.utilities.LockClass;
import sgf.view.bullet.BulletView;
import sgf.view.enemy.AbstractEnemyView;
import sgf.view.enemy.EnemyViewImpl;
import sgf.view.game.AbstractGameView;
import sgf.view.game.AbstractPlayerView;
import sgf.view.game.AbstractPlayingView;
import sgf.view.game.GameViewImpl;
import sgf.view.game.PlayerViewImpl;
import sgf.view.game.PlayingViewImpl;
import sgf.view.map.AbstractMapView;
import sgf.view.map.MapViewImpl;
import sgf.view.menu.AbstractMenuView;
import sgf.view.menu.MenuView;
import sgf.view.menu.MenuViewImpl;
import sgf.view.shop.AbstractShopView;
import sgf.view.shop.ShopViewImpl;
import sgf.view.turret.AbstractTurretView;
import sgf.view.turret.TurretViewImpl;

/**
 * 
 * 
 *
 */
public class MenuControllerImpl implements MenuController {

    private boolean isControllerSet;
    private AbstractMenuView menuView;
    private final LeaderboardManager leaderboard;

    /**
     * 
     * @param leaderboard
     */
    public MenuControllerImpl(final LeaderboardManager leaderboard) {
        this.leaderboard = leaderboard;
    }

    @Override
    public void setView(final MenuView view) {
        if (view instanceof AbstractMenuView) {
            if (!isControllerSet) {
                this.isControllerSet = true;
                this.menuView = (AbstractMenuView) view;
            }
        } else {
            throw new IllegalArgumentException();
        }

    }

    @Override
    public AbstractPlayingView loadPlayingView(final int levelNum) {
        final Player player = new PlayerImpl("DEFAULT");
        final PlayerController playerController = new PlayerControllerImpl(player, leaderboard);
        final Map map = new MapLoaderImpl(levelNum).getMap();  // 1 to be generalized.
        final List<Wave> waves = new WavesLoaderImpl(map, levelNum).getWaves();      // 1 to be generalized.
        final Level level = new LevelImpl(waves, map);
        final LevelManager levelManager = new LevelManagerImpl(level);
        final GameManager gameManager = new GameManagerImpl(playerController, levelManager);
        final MapController mapController = new MapControllerImpl(map);
        final TurretsLoader tLoader = new TurretsLoaderImpl(); // Test loader.
        final Shop shop = new ShopImpl(tLoader);
        /*
         * At the start only the menu, settings and levels view will be created.
         * All these other views and controllers will be created when someone clicks on a level.
         */
        final AbstractPlayerView playerView = new PlayerViewImpl();
        final AbstractMapView mapView = new MapViewImpl(map);
        final EnemyController enemyController = new EnemyControllerImpl(levelManager, gameManager, playerController, leaderboard);
        final AbstractEnemyView enemyView = new EnemyViewImpl(map.getSize());
        final ShopController shopController = new ShopControllerImpl(gameManager, shop);
        final AbstractShopView shopView = new ShopViewImpl();
        final BulletController bulletController = null;
        final BulletView bulletView = null;     // Use AbstractBulletView as type once created.
        final TurretController turretController = new TurretControllerImpl(map, shopController, LockClass.getTurretSemaphore(), enemyController, gameManager, bulletController);
        final AbstractTurretView turretView = new TurretViewImpl(map, LockClass.getTurretSemaphore());
        final GameController gameController = new GameControllerImpl(gameManager);
        final AbstractGameView gameView = new GameViewImpl(mapView, enemyView, turretView);
        final PlayingController playingController = new PlayingControllerImpl(gameManager);
        final AbstractPlayingView playingView = new PlayingViewImpl(gameView, shopView, playerView, turretView);


        /**
         * Linking.
         */
        gameController.setView(gameView);
        gameView.setController(gameController);
        mapController.setView(mapView);
        mapView.setController(mapController);
        enemyController.setView(enemyView);
        enemyView.setController(enemyController);
        turretController.setView(turretView);
        turretView.setController(turretController);
        shopController.setView(shopView);
        shopView.setController(shopController);
        playingController.setView(playingView);
        playingView.setController(playingController);
        playerController.setView(playerView);
        playerView.setController(playerController);

        shopView.start();
        playerView.start();
        mapView.start();
        enemyView.start();
        turretView.start();
        gameView.start();
        playingView.start();

        return playingView;
    }

    @Override
    public void stopController() {
        // TODO Auto-generated method stub
    }

}