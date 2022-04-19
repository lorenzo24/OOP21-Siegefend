package sgf.controller.menu;

import sgf.controller.Controller;
import sgf.controller.game.MusicController;
import sgf.controller.game.PlayerController;
import sgf.model.game.Leaderboard;
import sgf.view.game.AbstractPlayingView;
import sgf.view.menu.MenuView;

/**
 * Manages the MenuView.
 */
public interface MenuController extends Controller<MenuView> {

    /**
     * Create all classes needed to play the game.
     * @param level the level we want to load
     * @return a playing view
     */
    AbstractPlayingView loadPlayingView(int level);

    /**
     * Returns the {@link Leaderboard}.
     * @return {@link Leaderboard}
     */
    Leaderboard getLeaderboard();

    /**
     * Returns the {@link PlayerController}.
     * @return the {@link PlayerController}.
     */
    PlayerController getPlayerController();

    /**
     * Returns the {@link MusicController}.
     * @return the {@link MusicController}
     */
    MusicController getMusicController();
}
