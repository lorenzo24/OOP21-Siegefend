package sgf.controller.turret;

import sgf.controller.Controller;
import sgf.model.map.GridPosition;
import sgf.view.turret.TurretView;

/**
 * 
 * 
 *
 */
public interface TurretController extends Controller<TurretView> {

    /**
     * 
     */
    void addSelectedTurret(GridPosition gpos);

    /**
     * 
     * @param gpos
     * @return
     */
    boolean isTileEmpty(GridPosition gpos);
}
