package sgf.controller.bullet;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Semaphore;
import sgf.managers.BulletManager;
import sgf.managers.BulletManagerImpl;
import sgf.model.bullet.Bullet;
import sgf.model.game.Pausable;
import sgf.utilities.LockClass;
import sgf.view.bullet.BulletView;

/**
 * Controller class for the view of bullets.
 */
public class BulletControllerImpl implements BulletController, Pausable {

    private boolean isViewSet;
    private BulletView bulletView;
    private final List<BulletManager> bullets;
    private final Semaphore semaphore;
    private boolean active;

    /**
     * Creates a new instance of the class.
     */
    public BulletControllerImpl() {
        this.bullets = new ArrayList<>();
        this.semaphore = LockClass.getBulletSemaphore();
    }

    @Override
    public void setView(final BulletView view) {
        if (!isViewSet) {
            this.isViewSet = true;
            this.bulletView = view;
            this.active = true;
        }
    }

    @Override
    public void stopController() {
        this.active = false;
        this.semaphore.acquireUninterruptibly();        // Semaphore needed to make sure no elements are added/removed while the foreach is running.
        this.bullets.forEach(BulletManager::eliminate);
        this.semaphore.release();
    }

    @Override
    public void addBullet(final Bullet bullet) {
        if (bullet == null) {
            throw new IllegalArgumentException("Bullet cannot be null");
        }
        this.semaphore.acquireUninterruptibly();
        this.bullets.add(new BulletManagerImpl(bullet, this));
        this.semaphore.release();
    }

    @Override
    public void removeBullet(final BulletManager bulletManager) {
        this.semaphore.acquireUninterruptibly();
        this.bullets.remove(bulletManager);
        this.semaphore.release();
    }

    @Override
    public Iterator<Bullet> getBulletsIterator() {
        return this.bullets.stream().map(BulletManager::getBullet).iterator();
    }

    @Override
    public void pause() {
        this.bullets.forEach(Pausable::pause);
    }

    @Override
    public void resume() {
        this.bullets.forEach(Pausable::resume);
    }

}
