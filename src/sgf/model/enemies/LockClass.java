package sgf.model.enemies;

import java.util.concurrent.Semaphore;

/**
 * Class that represents a semaphore for the mutual exclusion.
 */
public final class LockClass {
    private static final Semaphore SEMAPHORE = new Semaphore(1); // One semaphore.

    private LockClass() { }

    /**
     * Returns the semaphore.
     * @return The semaphore for the mutual exclusion.
     */
    public static Semaphore getSemaphore() {
        return SEMAPHORE;
    }
}
