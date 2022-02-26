package sgf.controller;

import sgf.controller.thread.GameControllerImpl;

/**
 * Class with the task of starting the game.
 */
public class StartGame {
    /**
     * Starting point for the game.
     * @param args is the default parameter for main method.
     */
    public static void main(final String[] args) {
       final GameControllerImpl gController = new GameControllerImpl(); 
    }
}
