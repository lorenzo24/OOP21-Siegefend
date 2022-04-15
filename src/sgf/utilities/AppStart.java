package sgf.utilities;
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
import sgf.controller.menu.MenuController;
import sgf.controller.menu.MenuControllerImpl;
import sgf.controller.shop.ShopController;
import sgf.controller.shop.ShopControllerImpl;
import sgf.controller.turret.TurretController;
import sgf.controller.turret.TurretControllerImpl;
import sgf.helpers.MapLoaderImpl;
import sgf.helpers.TurretsLoaderImpl;
import sgf.helpers.TurretsLoader;
import sgf.helpers.WavesLoaderImpl;
import sgf.helpers.LevelLoader;
import sgf.helpers.LevelLoaderImpl;
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
import sgf.view.ScreenGame;
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
import sgf.view.menu.MenuViewImpl;
import sgf.view.shop.AbstractShopView;
import sgf.view.shop.ShopViewImpl;
import sgf.view.turret.AbstractTurretView;
import sgf.view.turret.TurretView;
import sgf.view.turret.TurretViewImpl;

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
        final MusicController m = new MusicControllerImpl();
        final LeaderboardManager leaderboard = new LeaderboardManagerImpl();
        final LevelLoader levelLoader = new LevelLoaderImpl();
        final MenuController menuController = new MenuControllerImpl(leaderboard);
        final AbstractMenuView menuView = new MenuViewImpl(levelLoader);


        /**
         * Linking.
         */
        menuController.setView(menuView);
        menuView.setController(menuController);


        menuView.start();

        new ScreenGame(menuView);
    }
}
