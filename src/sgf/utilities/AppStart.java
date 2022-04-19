package sgf.utilities;
import sgf.controller.game.MusicController;
import sgf.controller.game.MusicControllerImpl;
import sgf.controller.menu.MenuController;
import sgf.controller.menu.MenuControllerImpl;
import sgf.helpers.LevelLoader;
import sgf.helpers.LevelLoaderImpl;
import sgf.managers.LeaderboardManager;
import sgf.managers.LeaderboardManagerImpl;
import sgf.model.game.Player;
import sgf.model.game.PlayerImpl;
import sgf.view.ScreenGame;
import sgf.view.menu.AbstractMenuView;
import sgf.view.menu.MenuViewImpl;

/**
 * Utility class for starting the application.
 */
public final class AppStart {

    private AppStart() {
    }

    /**
     * 
     * @param args
     */
    public static void main(final String[] args) {
        final LeaderboardManager leaderboardManager = new LeaderboardManagerImpl();
        /* ** */
        final Player player = new PlayerImpl();
        //final PlayerController playerController = new PlayerControllerImpl(player, leaderboardManager);
        /* ** */
        final MusicController m = new MusicControllerImpl();
        final LevelLoader levelLoader = new LevelLoaderImpl();
        final MenuController menuController = new MenuControllerImpl(leaderboardManager, player);
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
