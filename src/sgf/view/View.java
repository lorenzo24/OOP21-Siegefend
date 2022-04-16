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

    /**
     * Enables the view to start working. Throws {@code IllegalStateException} if the
     * controller has not been set
     */
    void start();

    /**
     * Disables the view and closes all/any thread currently active in it.
     */
    void stopView();
}
