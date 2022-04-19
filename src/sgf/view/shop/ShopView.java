package sgf.view.shop;

import sgf.controller.shop.ShopController;
import sgf.view.View;

/**
 * Interfaces for the view of the shop.
 */
public interface ShopView extends View<ShopController> {

    /**
     * Disables all unselected options in the shop.
     */
    void disableAllNotSelected();

    /**
     * Re-enables all options in the shop.
     */
    void enableAll();

    /**
     * Alerts the view that the turret has been deselected, either after a cancellation or a purchase.
     */
    void turretDeselected();
}
