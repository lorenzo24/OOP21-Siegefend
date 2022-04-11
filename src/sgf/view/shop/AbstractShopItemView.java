package sgf.view.shop;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import sgf.model.turret.Turret;

/**
 * Abstract class for displaying information about a {@link Turret} inside a window frame.
 */
public abstract class AbstractShopItemView extends JPanel {     /* Making an interface didn't look like a smart idea because some of the public methods
                                                                   of the class are strictly related to JPanel. */
    /**
     * 
     */
    private static final long serialVersionUID = -548080196182873203L;

    /**
     * Returns the associated {@link Turret}.
     * @return a turret
     */
    public abstract Turret getTurret();

    /**
     * Adds the {@link ActionListener} to the buy button of the view.
     * @param l the listener to add
     */
    public abstract void addActionListener(ActionListener l);

    /**
     * Changes the size of the image canvas.
     * @param d the new size
     */
    public abstract void setCanvasSize(Dimension d);

    /**
     * Returns whether an item is selected or not.
     * @return a boolean
     */
    public abstract boolean isSelected();

    /**
     * Sets the item as selected or not selected.
     * @param isSelected a boolean
     */
    public abstract void setSelected(boolean isSelected);

    /**
     * Puts the item in buy mode.
     */
    public abstract void buyMode();

    /**
     * Puts the item in cancel mode.
     */
    public abstract void cancelMode();

    /**
     * Returns whether the buy button is selected or not.
     * @return a boolean
     */
    public abstract boolean isButtonEnabled();

    /**
     * Sets the buy button as enabled or disabled.
     * @param b a boolean
     */
    public abstract void setButtonEnabled(boolean b);
}
