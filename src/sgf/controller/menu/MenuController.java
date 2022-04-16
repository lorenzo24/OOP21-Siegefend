package sgf.controller.menu;

import sgf.controller.Controller;
import sgf.model.game.Leaderboard;
import sgf.view.game.AbstractPlayingView;
import sgf.view.menu.MenuView;

/**
 * 
 * 
 *
 */
public interface MenuController extends Controller<MenuView> {
    AbstractPlayingView loadPlayingView(int level);
    Leaderboard getLeaderboard();
}
