package sgf.controller.turret;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.Semaphore;
import java.util.Optional;

import sgf.controller.shop.ShopController;
import sgf.helpers.ImgTileSize;
import sgf.model.map.GridPosition;
import sgf.model.map.Map;
import sgf.model.turret.Turret;
import sgf.model.turret.TurretImpl;
import sgf.utilities.PositionConverter;
import sgf.view.turret.TurretView;

/**
 * 
 * 
 *
 */
public class TurretControllerImpl implements TurretController {

    private TurretView turretView;
    private boolean isViewSet;
    private final Map map;
    private final ShopController shopController;
    private final java.util.Map<GridPosition, Turret> turrets;
    private final Semaphore semaphore;
    private final int tileSize;

    /**
     * 
     * @param map
     * @param shopController
     * @param semaphore
     */
    public TurretControllerImpl(final Map map, final ShopController shopController, final Semaphore semaphore) {
        this.map = map;
        this.shopController = shopController;
        this.semaphore = semaphore;
        this.turrets = new HashMap<>();
        this.tileSize = ImgTileSize.getTileSize();
//        turrets.put(new GridPosition(4, 4), new TurretImpl(0, new PositionConverter(ImgTileSize.getTileSize()).convertToPosition(new GridPosition(4, 4)), 100, 0, 0, 0, 0)); // test
//        turrets.put(new GridPosition(11, 8), new TurretImpl(0, new PositionConverter(ImgTileSize.getTileSize()).convertToPosition(new GridPosition(11, 8)), 100, 0, 0, 0, 0)); // test rotation
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
        if (shopController.getSelectedTurret().isPresent() && isTileEmpty(gpos)) {
            final Optional<Turret> t = shopController.buy();
            if (t.isPresent()) {
                semaphore.acquireUninterruptibly();
                final Turret newTurret = t.get().getClone();
                newTurret.setPosition(new PositionConverter(this.tileSize).convertToPosition(gpos));
                turrets.put(new GridPosition(gpos), newTurret);
                semaphore.release();
            }
        }
    }

    @Override
    public boolean isTileEmpty(final GridPosition gpos) {
        return !turrets.containsKey(gpos);
    }

    @Override
    public Iterator<Entry<GridPosition, Turret>> getTurretsIterator() {
        return turrets.entrySet().iterator();
    }

    @Override
    public void stopController() { }

}
