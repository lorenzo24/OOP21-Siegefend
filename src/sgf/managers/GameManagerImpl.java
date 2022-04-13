package sgf.managers;

import sgf.controller.game.PlayerController;
import sgf.controller.game.PlayerControllerImpl;
import sgf.model.game.GameStatus;
import sgf.model.game.Player;
import sgf.model.level.Level;
import sgf.model.level.Wave;
import sgf.model.turret.Turret;
/**
 * This class is the implementation of the interface GameManager.
 */
public class GameManagerImpl implements GameManager {
    private final PlayerController playerController;
    private final LevelManager levelManager;
    private GameStatus gameStatus;

    /**
     * Constructor that initializes the fields.
     * @param player is the player useful in order to create its manager.
     * @param level is the level useful in order to create its manager
     */
    public GameManagerImpl(final Player player, final Level level) {
        this.playerController = new PlayerControllerImpl(player);
        this.levelManager = new LevelManagerImpl(level);
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
    }

    @Override
    public void unpause() {
        this.gameStatus = GameStatus.PLAYING;
    }

    @Override
    public GameStatus getCurrentStatus() {
        return this.gameStatus;
    }

    @Override
    public boolean purchase(Turret t) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean purchaseUpgrade(Turret t) {
        // TODO Auto-generated method stub
        return false;
    }
}
