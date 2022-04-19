package sgf.utilities;

import java.util.ArrayList;
import java.util.List;

import sgf.model.game.Stoppable;

/**
 * 
 *
 */
public final class ThreadObserver {
    private static final List<Stoppable> STOPPABLE_LIST = new ArrayList<>();

    private ThreadObserver() {
    }

    /**
     * 
     * @param stoppable
     */
    public static void register(final Stoppable stoppable) {
        STOPPABLE_LIST.add(stoppable);
    }

    /**
     * 
     */
    public static void stop() {
        STOPPABLE_LIST.forEach(Stoppable::stop);
    }
}
