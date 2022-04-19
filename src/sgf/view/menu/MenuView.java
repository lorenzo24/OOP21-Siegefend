package sgf.view.menu;

import sgf.controller.menu.MenuController;
import sgf.view.View;

/**
 * Interface of the view of the menu.
 */
public interface MenuView extends View<MenuController> {

    /**
     * Shows the panel containing the levels of the game and that lets the user input his username. 
     */
    void showLevelPicker();

    /**
     * Shows the options panel. 
     */
    void showOptions();

    /**
     * Shows the leaderboard panel.
     */
    void showLeaderboard();

    /**
     * Shows the credits panel.
     */
    void showCredits();
}
