package sgf.utilities;

import java.util.ArrayList;
import java.util.List;

import sgf.model.game.Stoppable;

/**
 * Class useful for Observer pattern implementation.
 */
public final class ThreadObserver {
    private static final List<Stoppable> STOPPABLE_LIST = new ArrayList<>();

    private ThreadObserver() {
    }

    /**
     * This method is used to register every Manager and View.
     * @param stoppable
     */
    public static void register(final Stoppable stoppable) {
        STOPPABLE_LIST.add(stoppable);
    }

    /**
     * This method is called just before exiting the game to stop every Manager thread and every View.
     */
    public static void stop() {
        STOPPABLE_LIST.forEach(Stoppable::stop);
    }
}
