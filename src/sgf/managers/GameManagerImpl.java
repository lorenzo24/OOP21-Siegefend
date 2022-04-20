package sgf.managers;

import sgf.controller.game.PlayerController;
import sgf.model.level.Level;
import sgf.model.level.Wave;

/**
 * This class is the implementation of the interface GameManager.
 */
public class GameManagerImpl implements GameManager {
    private final PlayerController playerController;
    private final LevelManager levelManager;

    /**
     * Constructor that initializes the fields.
     * @param pController is the {@link PlayerController}, useful for a getter.
     * @param levelManager is the {@link LevelManager}, useful for a getter.
     */
    public GameManagerImpl(final PlayerController pController, final LevelManager levelManager) {
        this.playerController = pController;
        this.levelManager = levelManager;
    }

    @Override
    public PlayerController getPlayerController() {
        return this.playerController;
    }

    @Override
    public LevelManager getLevelManager() {
        return this.levelManager;
    }

    @Override
    public Level getCurrentLevel() {
        return this.levelManager.getCurrentLevel();
    }

    @Override
    public Wave getCurrentWave() {
        return this.levelManager.getCurrentWave();
    }
}
