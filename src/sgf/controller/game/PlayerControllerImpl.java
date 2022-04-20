package sgf.controller.game;

import sgf.managers.LeaderboardManager;
import sgf.model.game.Player;
import sgf.view.game.PlayerView;

/**
 *
 *
 */
public class PlayerControllerImpl implements PlayerController {

    private final Player player;
    private PlayerView playerView;
    private boolean isControllerSet;
    private final LeaderboardManager leaderboard;

    /**
     * WIP constructor.
     * @param player
     * @param leaderboard To write the score when player lost.
     */
    public PlayerControllerImpl(final Player player, final LeaderboardManager leaderboard) {
        super();
        this.player = player;
        this.leaderboard = leaderboard;
    }

    @Override
    public Player getPlayer() {
        return this.player;
    }

    @Override
    public void changeHP(final int offset) {
        final int newAmount = this.player.getCurrentHP() + offset;
        if (newAmount > 0) {
            this.player.setCurrentHP(newAmount);
        } else {
            this.leaderboard.addScore(this.player.getPlayerName(), this.player.getScore());
            this.leaderboard.writeScore();
            this.playerView.loseGame();
        }
        this.playerView.update();
    }

    @Override
    public void changeMoney(final int offset) {
        final int newAmount = this.player.getMoney() + offset;
        if (newAmount >= 0) {
            this.player.setMoney(newAmount);
        }
        this.playerView.update();
    }

    @Override
    public void changeScore(final int offset) {
        final int newScore = this.player.getScore() + offset;
        if (newScore >= 0) {
            this.player.setScore(newScore);
        }
        this.playerView.update();
    }

    @Override
    public void setView(final PlayerView view) {
        if (!isControllerSet) {
            this.isControllerSet = true;
            this.playerView = view;
        }
    }

    @Override
    public void stop() {
    }
}
