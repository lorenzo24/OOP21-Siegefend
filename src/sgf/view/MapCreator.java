package sgf.view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import sgf.controller.MouseController;
import sgf.controller.TileController;
import sgf.model.GridPosition;
import sgf.model.Map;

/**
 * 
 * This class is responsible for the process of map showing.
 *
 */
public class MapCreator extends JPanel implements ComponentListener {
    private static final long serialVersionUID = -7141712951441617040L;
    private static final int MATRIX_SIZE = 20; // Image's nunmber.
    private static final int IMAGE_SIZE = 80;
    private final TileController tileController = new TileController();
    private final BufferedImage completeMap = new BufferedImage(MATRIX_SIZE * IMAGE_SIZE, MATRIX_SIZE * IMAGE_SIZE, BufferedImage.TYPE_INT_RGB);
    private boolean mapCreated;
    private boolean needUpdate;

    /**
     * Constructor that link this component in such a way that it can be listened.
     */
    public MapCreator() {
        this.addComponentListener(this);
        this.addMouseListener(new MouseController());   // Links this panel with a controller of mouse events.
        this.mapCreated = false;
        this.needUpdate = true;
    }

    /**
     * 
     * @param mapToBeShowed
     */
    public void createMapImage(final Map mapToBeShowed) {
        final Graphics g = completeMap.getGraphics();
        for (int row = 0; row < MATRIX_SIZE; row++) {
            for (int column = 0; column < MATRIX_SIZE; column++) {
                final Image i = tileController.getImage(mapToBeShowed.getTileFromGridPosition(new GridPosition(column, row)));
                g.drawImage(i, column * IMAGE_SIZE, row * IMAGE_SIZE, IMAGE_SIZE, IMAGE_SIZE, null);
            }
        }
        try {
            ImageIO.write(completeMap, "PNG", new File("res" + File.separator + "testimage.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.dispose();
    }

    /**
     * Method that reads the given matrix and draws the correspondent grid of tiles.
     * @param mapToBeShowed Is the matrix given in  input as map structure.
     */
    public void showGridImage(final Map mapToBeShowed) {
        if (!mapCreated) {
            mapCreated = true;
            this.createMapImage(mapToBeShowed);
        }
        this.getGraphics().drawImage(completeMap, 0, 0, this.getWidth(), this.getHeight(), null);
        this.needUpdate = false;
    }

    /**
     * Checks whether the window with the map has been updated and needs to be redrawn.
     * @return a boolean
     */
    public boolean isUpdateNeeded() {
        return needUpdate;
    }

    @Override
    public void componentResized(final ComponentEvent e) {
        this.needUpdate = true;
    }

    @Override
    public void componentMoved(final ComponentEvent e) {
        this.needUpdate = true;
    }

    @Override
    public void componentShown(final ComponentEvent e) {
        this.needUpdate = true;
    }

    @Override
    public void componentHidden(final ComponentEvent e) {
        this.needUpdate = true;
    }
    // TODO Find a way to remove this void methods that compares after implementing interface.
}
