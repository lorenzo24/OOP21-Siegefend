package sgf.model;

/**
 * Represents the game's state.
 */
public enum GameStatus {
    /**
     * The user is playing.
     */
    PLAYING,
    /**
     * Game pause.
     */
    PAUSED,
    /**
     * Victory of the player.
     */
    WON,
    /**
     * Loss of the player.
     */
    LOST;
}
