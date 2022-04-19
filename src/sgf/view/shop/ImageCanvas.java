package sgf.view.shop;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JLabel;

/**
 * Label for displaying an image.
 */
public class ImageCanvas extends JLabel {

    private final Image image;
    private static final int PREF_SIZE = 100;

    /**
     * 
     */
    private static final long serialVersionUID = 1326241868157403551L;

    /**
     * Creates a new instance of the class.
     * @param image an image
     */
    public ImageCanvas(final Image image) {
        this.image = image;
        this.setPreferredSize(new Dimension(PREF_SIZE, PREF_SIZE));
    }

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        if (this.image != null) {
            g.drawImage(this.image, 0, 0, this.getWidth(), this.getHeight(), null);
        }
    }
}
