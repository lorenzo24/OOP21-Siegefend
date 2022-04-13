package sgf.controller.turret;

import java.util.Iterator;
import java.util.Map;

import sgf.controller.Controller;
import sgf.model.map.GridPosition;
import sgf.model.turret.Turret;
import sgf.view.turret.TurretView;

/**
 * 
 * 
 *
 */
public interface TurretController extends Controller<TurretView> {

    /**
     * 
     * @param gpos
     */
    void addSelectedTurret(GridPosition gpos);

    /**
     * 
     * @param gpos
     * @return ss
     */
    boolean isTileEmpty(GridPosition gpos);

    /**
     * 
     * @return ss
     */
    Iterator<Map.Entry<GridPosition, Turret>> getTurretsIterator();
}
