package sgf.view.shop;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;

import sgf.controller.game.PlayingController;

/**
 *
 */
public class ImageCanvas extends JLabel {

    private final BufferedImage image;

    /**
     * 
     */
    private static final long serialVersionUID = 1326241868157403551L;

    /**
     * 
     * @param image
     */
    public ImageCanvas(final BufferedImage image) {
        this.image = image;
        this.setPreferredSize(new Dimension(100, 100));
    }

    /**
     * 
     */
    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        if (this.image != null) {
            g.drawImage(this.image, 0, 0, this.getWidth(), this.getHeight(), null);
        }
    }
}
