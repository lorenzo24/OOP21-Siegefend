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
import sgf.managers.TurretManager;
import sgf.managers.TurretManagerImpl;
import sgf.model.bullet.Bullet;
import sgf.model.map.GridPosition;
import sgf.model.turret.Turret;
import sgf.utilities.PositionConverter;
import sgf.view.turret.TurretView;

/**
 * Controller class for the view of turrets.
 */
public class TurretControllerImpl implements TurretController {

    private final int tileSize;
    private final ShopController shopController;
    private final java.util.Map<GridPosition, TurretManager> turrets;
    private final Semaphore semaphore;
    private final EnemyController enemyController;
    private final BulletController bulletController;
    @SuppressWarnings("unused")
    private TurretView turretView;
    private boolean isViewSet;

    /**
     * Creates a new instance of the class.
     * @param shopController the {@link ShopController}
     * @param semaphore the {@link Semaphore}
     * @param enemyController the {@link EnemyController}
     * @param bulletController the {@link BulletController}
     */
    public TurretControllerImpl(final ShopController shopController, final Semaphore semaphore, final EnemyController enemyController, final BulletController bulletController) {
        this.shopController = shopController;
        this.semaphore = semaphore;
        this.turrets = new HashMap<>();
        this.tileSize = ImgTileSize.getTileSize();
        this.enemyController = enemyController;
        this.bulletController = bulletController;
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
                final TurretManager newTurretManager = new TurretManagerImpl(t.get().getClone(), this, this.enemyController);
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
    public void stop() { }

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
