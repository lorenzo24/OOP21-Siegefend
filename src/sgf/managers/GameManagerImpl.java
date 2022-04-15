package sgf.managers;

import java.util.ArrayList;
import java.util.List;

import sgf.controller.game.PlayerController;
import sgf.controller.game.PlayerControllerImpl;
import sgf.controller.turret.TurretController;
import sgf.model.game.GameStatus;
import sgf.model.game.Pausable;
import sgf.model.game.Player;
import sgf.model.level.Level;
import sgf.model.level.Wave;
import sgf.model.map.GridPosition;

/**
 * This class is the implementation of the interface GameManager.
 */
public class GameManagerImpl implements GameManager {
    private final PlayerController playerController;
    private final LevelManager levelManager;
    private GameStatus gameStatus;
    private final List<Pausable> pausables;

    /**
     * Constructor that initializes the fields.
     * @param pController
     * @param levelManager
     */
    public GameManagerImpl(final PlayerController pController, final LevelManager levelManager) {
        this.pausables = new ArrayList<>();
        this.playerController = pController;
        this.levelManager = levelManager;
        this.gameStatus = GameStatus.PLAYING;
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

    @Override
    public boolean isPaused() {
        return this.gameStatus == GameStatus.PAUSED;
    }

    @Override
    public void pause() {
        this.gameStatus = GameStatus.PAUSED;
        this.pausables.stream().forEach(p -> p.pause());
    }

    @Override
    public void unpause() {
        this.gameStatus = GameStatus.PLAYING;
        this.pausables.stream().forEach(p -> p.resume());
    }

    @Override
    public GameStatus getCurrentStatus() {
        return this.gameStatus;
    }

    @Override
    public void register(final Pausable p) {
        this.pausables.add(p);
    }

    @Override
    public void deregister(final Pausable p) {
        this.pausables.remove(p);
    }
}
