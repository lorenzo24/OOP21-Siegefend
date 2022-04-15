package sgf.utilities;
import sgf.controller.game.MusicController;
import sgf.controller.game.MusicControllerImpl;
import sgf.controller.menu.MenuController;
import sgf.controller.menu.MenuControllerImpl;
import sgf.helpers.LevelLoader;
import sgf.helpers.LevelLoaderImpl;
import sgf.managers.LeaderboardManager;
import sgf.managers.LeaderboardManagerImpl;
import sgf.view.ScreenGame;
import sgf.view.menu.AbstractMenuView;
import sgf.view.menu.MenuViewImpl;

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
