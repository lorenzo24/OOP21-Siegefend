package sgf.view.shop;


import java.awt.Dimension;

import javax.swing.JPanel;

/**
 * Abstract class for the view of the shop based on {@link JPanel}.
 */
public abstract class AbstractShopView extends JPanel implements ShopView {

    private Dimension itemImgSize;
    private static final long serialVersionUID = 2452674161344433337L;

    /**
     * Updates the view when the item size is changed.
     */
    protected abstract void update();

    /**
     * Sets the size of the image of the item.
     * @param size The size of the image.
     */
    public void setItemImgSize(final Dimension size) {
        itemImgSize = new Dimension(size);
        this.update();
    }

    /**
     * @return the correct size
     */
    public Dimension getItemImgSize() {
        return this.itemImgSize;
    }
}
