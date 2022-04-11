package sgf.controller.turret;

import sgf.controller.shop.ShopController;
import sgf.model.map.GridPosition;
import sgf.model.map.Map;
import sgf.model.turret.Turret;
import sgf.view.turret.TurretView;

/**
 * 
 * 
 *
 */
public class TurretControllerImpl implements TurretController {

    private TurretView turretView;
    private boolean isViewSet;
    private Map map;
    private ShopController shopController;
    private java.util.Map<GridPosition, Turret> turrets;
    
    /**
     * 
     * @param map
     * @param shopController
     */
    public TurretControllerImpl(final Map map, ShopController shopController) {
        this.map = map;
        this.shopController = shopController;
    }

    @Override
    public void setView(final TurretView view) {
        if (!isViewSet) {
            this.isViewSet = true;
            this.turretView = view;
        }
    }

    @Override
    public void addSelectedTurret(final GridPosition gpos) {
        if(shopController.getSelectedTurret())

    }

    @Override
    public boolean isTileEmpty(final GridPosition gpos) {
        // TODO Auto-generated method stub
        return false;
    }

}
