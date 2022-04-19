package sgf.controller.bullet;

import java.util.Iterator;

import sgf.controller.Controller;
import sgf.managers.BulletManager;
import sgf.model.bullet.Bullet;
import sgf.view.bullet.BulletView;

/**
 * Manages the BulletView.
 */
public interface BulletController extends Controller<BulletView>  {

    /**
     * Adds a bullet that can be shown in the view.
     * @param bullet the bullet that we want to add
     */
    void addBullet(Bullet bullet);

    /**
     * Removes a certain bullet from the view.
     * @param bulletManager the manager of the bullet
     */
    void removeBullet(BulletManager bulletManager);

    /**
     * Returns an iterator of all bullets currently active.
     * @return an iterator of bullets
     */
    Iterator<Bullet> getBulletsIterator();
}
