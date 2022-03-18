package sgf.model.shop;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import sgf.model.game.Player;
import sgf.model.turret.Turret;

/**
 * Represents the shop where the {@link Player} can buy defenses.
 */
public class ShopImpl implements Shop {

    private final Map<Turret, Integer> turrets;
    private final Player player;

    /**
     * Creates a new shop object with the given turrets, prices and the player.
     * @param turrets A map which associates a price to each turret
     * @param player The player of the game
     */
    public ShopImpl(final Map<Turret, Integer> turrets, final Player player) {
        this.turrets = Map.copyOf(turrets);
        this.player = player;
    }

    @Override
    public List<Turret> getAvailableTurrets() {
        return new ArrayList<>(turrets.keySet());
    }

    @Override
    public boolean purchase(final Turret t) {
        /*
         * In hindsight, TurretController will probably need to have a reference to the current Turret
         * and its next level, so we may need a new class which holds a list of all the upgrades of a turret,
         * something like TurretUpgradeList.
         */
        if (this.turrets.containsKey(t)) {
            return player.getCurrentMoney() >= t.getPrice();
        } else {
            return false; // Maybe exception?
        }
    }
}
