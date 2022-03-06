package sgf.utilities;

/**
 * Rapresent the single enemy.
 */
public interface EnemyManager {

    /**
     * Stops the thread.
     */
    void stopThread();

    /**
     * Restarts the thread.
     */
    void resumeThread();

    /**
     * If the enemy is arrived at the end of the path.
     * @return if the enemy is arrived at the end.
     */
    boolean isWin();
}
