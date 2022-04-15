package sgf.controller.bullet;

import sgf.controller.Controller;
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
}
