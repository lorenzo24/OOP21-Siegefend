package sgf.controller.game;

import sgf.model.game.Player;
import sgf.view.game.PlayerView;

/**
 *
 *
 */
public class PlayerControllerImpl implements PlayerController {

    private final Player player;
    private PlayerView playerView;
    private boolean isControllerSet = false;

    /**
     * WIP constructor.
     * @param player
     */
    public PlayerControllerImpl(final Player player) {
        super();
        this.player = player;
    }

    @Override
    public Player getPlayer() {
        return this.player;
    }

    @Override
    public void changeHP(final int offset) {                            //TODO: change these "copy-paste-rename" methods into something better.
        final int newAmount = this.player.getCurrentHP() + offset;      //TODO: when the HP is changed, check if 0 or less, if 0 the player loses.
        if (newAmount >= 0) {
            this.player.setCurrentHP(newAmount);
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

}
