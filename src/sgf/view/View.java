package sgf.view;

import sgf.controller.Controller;
import sgf.model.game.Stoppable;

/**
 * Base interface for a view.
 * @param <C> the controller type accepted by the view
 */
public interface View<C extends Controller<?>> extends Stoppable {

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
}
