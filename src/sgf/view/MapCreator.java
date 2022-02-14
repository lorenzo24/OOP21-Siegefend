package sgf.view;

import java.awt.image.BufferedImage;
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
        graphic.drawImage(grassImage, 0, 0, null);
    }

}
