package sgf.managers;

import sgf.model.game.Player;

/**
 *
 *
 */
public class PlayerManagerImpl implements PlayerManager {

    private final Player player;

    /**
     * WIP constructor.
     * @param player
     */
    public PlayerManagerImpl(final Player player) {
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
    }

    @Override
    public void changeMoney(final int offset) {
        final int newAmount = this.player.getMoney() + offset;
        if (newAmount >= 0) {
            this.player.setMoney(newAmount);
        }
    }

    @Override
    public void changeScore(final int offset) {
        final int newScore = this.player.getScore() + offset;
        if (newScore >= 0) {
            this.player.setScore(newScore);
        }
    }

}
