package sgf.managers;

import sgf.controller.bullet.BulletController;
import sgf.model.bullet.Bullet;
import sgf.model.game.Stoppable;
import sgf.utilities.ThreadAndViewObservable;

/**
 * Manages a bullet, moving it towards an enemy and inflicting damage when reaching it.
 */
public class BulletManagerImpl implements BulletManager, Stoppable {
    private final Bullet bullet;
    private Thread thread;
    private final BulletController bulletController;
    private static final int UPDATE_DELAY = 20;
    private static final int TOUCH_DISTANCE = 50;
    private boolean active;
    private boolean threadRunning;

    /**
     * Creates an instance of the class with the given {@link Bullet}.
     * @param bullet a bullet
     * @param bulletController the controller for the bullet view
     */
    public BulletManagerImpl(final Bullet bullet, final BulletController bulletController) {
        ThreadAndViewObservable.register(this);
        this.bullet = bullet;
        this.bulletController = bulletController;
        this.active = true;
        startThread();
    }

    @Override
    public Bullet getBullet() {
        return this.bullet;
    }

    @Override
    public void eliminate() {
        this.bulletController.removeBullet(this);
        this.active = false;
    }

    private void shift(final double x, final double y) {
        this.bullet.move(this.bullet.getPosition().getX() + x, this.bullet.getPosition().getY() + y);
    }

    private void startThread() {
        final double deltaTime = 1.0 / UPDATE_DELAY;
        if (active && thread == null) {
            thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (active && threadRunning && bullet != null && bullet.getTarget() != null && bullet.getTarget().getHP() > 0) {
                            if (bullet.getPosition().distanceTo(bullet.getTargetPosition()) < TOUCH_DISTANCE) {
                                bullet.getTarget().damageSuffered(bullet.getDamage());
                                break;
                            }
                            final double directionAngle = bullet.getPosition().getAngle(bullet.getTargetPosition());
                            shift(Math.cos(directionAngle) * bullet.getSpeed() * deltaTime, Math.sin(directionAngle) * bullet.getSpeed() * deltaTime);
                            Thread.sleep(UPDATE_DELAY);
                        }
                        eliminate();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        this.threadRunning = true;
        this.thread.start();
    }

    @Override
    public void stop() {
        this.threadRunning = false;
        //this.thread.interrupt();
    }
}
