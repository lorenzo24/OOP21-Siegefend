package sgf.utilities;

import java.util.List;

import sgf.model.Turret;

/**
 * Loads the turrets (and their upgrades).
 */
public interface TurretsLoader {

    /**
     * Returns a list containing the turrets that can be bought in the shop.
     * @return a list of turrets
     */
    List<Turret> getTurrets();

    /**
     * Returns a list containing all the names of the turrets that can be bought in the shop.
     * @return a list of turret names
     */
    List<String> getTurretNames();
}
