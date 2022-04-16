package sgf.model.shop;

import java.util.List;

import sgf.model.game.Player;
import sgf.model.turret.Turret;

/**
 * Represents the shop where the {@link Player} can buy defenses.
 */
public interface Shop {
    /**
     * Returns a list containing all the turrets that can be bought.
     * @return a {@link List} of {@link Turret} objects
     */
    List<Turret> getAvailableTurrets();

    /**
     * Checks whether the {@link Turret} with the given id can be bought in the shop and if the given {@link Player} has enough money to buy it.
     * @param tid the id of the turret
     * @param p the player
     * @return {@code true} if the turret is available to be bought and the player has enough money, {@code false} otherwise
     */
    boolean canBuy(int tid, Player p);

    /**
     * Checks whether the {@link Turret} can be bought in the shop and if the given {@link Player} has enough money to buy it.
     * @param t the turret to buy
     * @param p the player
     * @return {@code true} if the turret is available to be bought and the player has enough money, {@code false} otherwise
     */
    boolean canBuy(Turret t, Player p);
}
