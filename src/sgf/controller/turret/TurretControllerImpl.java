package sgf.controller.turret;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.Semaphore;
import java.util.Optional;
import sgf.controller.bullet.BulletController;
import sgf.controller.enemy.EnemyController;
import sgf.controller.shop.ShopController;
import sgf.helpers.ImgTileSize;
import sgf.managers.GameManager;
import sgf.managers.TurretManager;
import sgf.managers.TurretManagerImpl;
import sgf.model.bullet.Bullet;
import sgf.model.map.GridPosition;
import sgf.model.map.Map;
import sgf.model.turret.Turret;
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
    private final java.util.Map<GridPosition, TurretManager> turrets;
    private final Semaphore semaphore;
    private final int tileSize;
    private final EnemyController enemyController;
    private final GameManager gameManager;
    private final BulletController bulletController;

    /**
     * 
     * @param map
     * @param shopController
     * @param semaphore
     * @param enemyController
     * @param gameManager
     * @param bulletController
     */
    public TurretControllerImpl(final Map map, final ShopController shopController, final Semaphore semaphore, final EnemyController enemyController, final GameManager gameManager, final BulletController bulletController) {
        this.map = map;
        this.shopController = shopController;
        this.semaphore = semaphore;
        this.turrets = new HashMap<>();
        this.tileSize = ImgTileSize.getTileSize();
        this.enemyController = enemyController;
        this.gameManager = gameManager;
        this.bulletController = bulletController;
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
                final TurretManager newTurretManager = new TurretManagerImpl(t.get().getClone(), this, this.enemyController, this.gameManager);
                newTurretManager.getTurret().setPosition(new PositionConverter(this.tileSize).convertToPosition(gpos));
                turrets.put(new GridPosition(gpos), newTurretManager);
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
        return turrets.entrySet().stream().map(e -> java.util.Map.entry(e.getKey(), e.getValue().getTurret())).iterator();
    }

    @Override
    public void stopController() { }

    @Override
    public boolean isTurretSelected() {
        return this.shopController.getSelectedTurret().isPresent();
    }

    @Override
    public Optional<Turret> getTurretAt(final GridPosition gpos) {
        final TurretManager t = this.turrets.get(gpos);
        return t != null ? Optional.of(t.getTurret()) : Optional.empty();
    }

    @Override
    public void bulletCreated(final Bullet bullet) {
        this.bulletController.addBullet(bullet);
    }

}
