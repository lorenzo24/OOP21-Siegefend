package sgf.model.shop;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import sgf.helpers.TurretsLoader;
import sgf.model.game.Player;
import sgf.model.turret.Turret;

/**
 * Represents the shop where the {@link Player} can buy defenses.
 */
public class ShopImpl implements Shop {

    private final Map<Integer, Turret> turrets;

    /**
     * Creates a new shop object with the given turrets, prices and the player.
     * @param turretsLoader an instance of {@link TurretsLoader}
     */
    public ShopImpl(final TurretsLoader turretsLoader) {
        this.turrets = turretsLoader.getTurrets();
    }

    @Override
    public List<Turret> getAvailableTurrets() {
        return new ArrayList<>(this.turrets.values());
    }

    @Override
    public boolean canBuy(final int tid, final Player p) {
        this.checkNull(p, "player");
        final Turret t = this.turrets.get(tid);
        return t != null && p.getMoney() >= t.getPrice();
    }

    @Override
    public boolean canBuy(final Turret t, final Player p) {
        this.checkNull(t, "turret");
        this.checkNull(p, "player");
        return t.equals(turrets.get(t.getID())) && canBuy(t.getID(), p); // A check is put in place to verify that not any Turret with a specific
    }                                                                    // ID can be bought off the shop just because the ID matched one in the Map.

    private void checkNull(final Object param, final String paramName) {
        if (param == null) {
            throw new IllegalArgumentException("Null value for parameter " + paramName + " is not valid");
        }
    }
}
