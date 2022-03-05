package sgf.view;

import sgf.controller.ShopController;

/**
 *
 */
public interface ShopView extends View<ShopController> {

    /**
     * Disables all unselected options in the shop.
     */
    void disableAll();

    /**
     * Re-enables all options in the shop.
     */
    void enableAll();

}
