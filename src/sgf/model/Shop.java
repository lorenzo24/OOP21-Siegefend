package sgf.model;

import java.util.List;

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
     * Tries to purchase one of the available turrets in the shop.
     * @param t The {@link Turret} to be bought
     * @return {@code true} if the purchase was successful, {@code false} otherwise
     * @throws @{@link IllegalArgumentException} if the {@link Turret} passed is not
     *          an existing option
     */
    boolean purchase(Turret t);
}
