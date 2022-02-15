package sgf.view;

import java.awt.image.BufferedImage;
import java.awt.Image;
import java.awt.Graphics;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
/**
 * 
 * This class is responsible for the process of map's creation and showing.
 *
 */
public class MapCreator extends JPanel {
    private static final int NUMBER_OF_CELLS = 25;
    private static final long serialVersionUID = -7141712951441617040L;
    private BufferedImage grassImage;

    /**
     * Constructor for a map creator.
     */
    public MapCreator() {
        try {
            grassImage = ImageIO.read(new FileInputStream("res" + File.separator + "grass.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Auto-called method that loads and show a simple image from res folder.
     * @param graphic Is the argument for this method.
     */
    public void paintComponent(final Graphics graphic) {
        super.paintComponent(graphic);
        final int widthImage = sizeImage(this.getWidth());
        final int heightImage =  sizeImage(this.getHeight());
        final Image modifiedImage = this.dinamicResize(widthImage, heightImage);
        for (int x = 0; x < NUMBER_OF_CELLS; x++) {
            for (int y = 0; y < NUMBER_OF_CELLS; y++) {
                graphic.drawImage(modifiedImage, x * widthImage, y * heightImage, null);
            }
        }
    }

    private Image dinamicResize(final int widthImage, final int heightImage) {
        return  grassImage.getScaledInstance(widthImage, heightImage, Image.SCALE_SMOOTH);
    }
    private int sizeImage(final double dimension) {
        return (int) Math.round(dimension / NUMBER_OF_CELLS);
    }
}
