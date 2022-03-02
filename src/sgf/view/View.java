package sgf.view;

import sgf.controller.Controller;

/**
 * Base interface for a view.
 * @param <C> the controller type accepted by the view
 */
public interface View<C extends Controller<?>> {

    /**
     * Sets the controller associated to the view. Can only be called once.
     * @param controller the controller to associate
     */
    void setController(C controller);
}
