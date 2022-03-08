package sgf.view;


import java.awt.Dimension;

import javax.swing.JPanel;

/**
 * 
 */
public abstract class AbstractShopView extends JPanel implements ShopView {

    private Dimension itemImgSize;

    /**
     * 
     */
    private static final long serialVersionUID = 2452674161344433337L;

    /**
     * Updates the view when the item size is changed.
     */
    protected abstract void update();

    /**
     * 
     * @param size
     */
    public void setItemImgSize(final Dimension size) {
        itemImgSize = new Dimension(size);
        this.update();
    }

    /**
     * 
     * @return the correct size
     */
    public Dimension getItemImgSize() {
        return this.itemImgSize;
    }
}
