package sgf.managers;

import java.util.ArrayList;
import java.util.List;
import sgf.controller.game.PlayerController;
import sgf.model.game.Stoppable;
import sgf.model.level.Level;
import sgf.model.level.Wave;

/**
 * This class is the implementation of the interface GameManager.
 */
public class GameManagerImpl implements GameManager {
    private final PlayerController playerController;
    private final LevelManager levelManager;
    private final List<Stoppable> stoppableList;

    /**
     * Constructor that initializes the fields.
     * @param pController
     * @param levelManager
     */
    public GameManagerImpl(final PlayerController pController, final LevelManager levelManager) {
        this.stoppableList = new ArrayList<>();
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
