package sgf.utilities;

import java.util.concurrent.Semaphore;

/**
 * Class that represents a semaphore for the mutual exclusion.
 */
public final class LockClass {
    private static final Semaphore ENEMY_SEMAPHORE = new Semaphore(1);
    private static final Semaphore TURRET_SEMAPHORE = new Semaphore(1);
    private static final Semaphore BULLET_SEMAPHORE = new Semaphore(1);
    // the same thing must be done for bullets

    private LockClass() { }

    /**
     * Returns the enemy's semaphore.
     * @return a semaphore
     */
    public static Semaphore getEnemySemaphore() {
        return ENEMY_SEMAPHORE;
    }

    /**
     * Returns the turret's semaphore.
     * @return a semaphore
     */
    public static Semaphore getTurretSemaphore() {
        return TURRET_SEMAPHORE;
    }

    /**
     * Returns the bullet's semaphore.
     * @return a semaphore
     */
    public static Semaphore getBulletSemaphore() {
        return BULLET_SEMAPHORE;
    }
}
