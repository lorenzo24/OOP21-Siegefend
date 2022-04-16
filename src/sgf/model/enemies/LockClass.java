package sgf.model.enemies;

import java.util.concurrent.Semaphore;

/**
 * Class that represents a semaphore for the mutual exclusion.
 */
public final class LockClass {
    private static final Semaphore ENEMY_SEMAPHORE = new Semaphore(1);

    // the same thing must be done for bullets

    private LockClass() { }

    /**
     * Returns the enemy's semaphore.
     * @return a semaphore
     */
    public static Semaphore getEnemySemaphore() {
        return ENEMY_SEMAPHORE;
    }
}
