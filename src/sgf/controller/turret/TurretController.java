package sgf.controller.turret;

import java.util.Iterator;
import java.util.Map;
import java.util.Optional;

import sgf.controller.Controller;
import sgf.model.map.GridPosition;
import sgf.model.turret.Turret;
import sgf.view.turret.TurretView;

/**
 * Manages the turrets' view.
 */
public interface TurretController extends Controller<TurretView> {

    /**
     * Check if a turret is selected in the shop.
     * @return {@code true} if any turret is selected, {@code false} otherwise
     */
    boolean isTurretSelected();

    /**
     * Returns the turret placed at the given position.
     * @param gpos the given position
     * @return the turret, otherwise an empty optional
     */
    Optional<Turret> getTurretAt(GridPosition gpos); 

    /**
     * Tries to buy and place a turret.
     * @param gpos the position where we want to place the turret
     */
    void addSelectedTurret(GridPosition gpos);

    /**
     * Check if there is a turret in the tile indicated by the position.
     * @param gpos the position of the tile
     * @return {@code true} if the tile is empty, {@code false} otherwise
     */
    boolean isTileEmpty(GridPosition gpos);

    /**
     * Returns an iterator of turrets.
     * @return an iterator of turrets
     */
    Iterator<Map.Entry<GridPosition, Turret>> getTurretsIterator();
}
