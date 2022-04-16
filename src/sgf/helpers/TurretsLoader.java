package sgf.helpers;

import java.util.Map;

import sgf.model.turret.Turret;

/**
 * Loads the turrets (and their upgrades).
 */
public interface TurretsLoader {

    /**
     * Returns a list containing the turrets that can be bought in the shop.
     * @return a list of turrets
     */
    Map<Integer, Turret> getTurrets();
}
