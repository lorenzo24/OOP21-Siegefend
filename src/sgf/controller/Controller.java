package sgf.controller;

import sgf.view.View;

/**
 * Base interface for a controller.
 * @param <V> the view type accepted by the controller.
 */
public interface Controller<V extends View<?>> {

    /**
     * Sets the view associated to the controller. Can only be called once.
     * @param view the view to associate
     */
    void setView(V view);

    /**
     * Stops all ongoing work.
     */
    void stopController();
}
