package sgf.view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.function.Consumer;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import sgf.controller.TileController;
import sgf.model.GridPosition;
import sgf.model.Map;

/**
 * This class is responsible for the process of map showing.
 */
public class MapCreator extends JPanel implements ComponentListener, MouseListener {
    private static final long serialVersionUID = -7141712951441617040L;
    private static final int IMAGE_SIZE = 80;
    private final int matrixSize = 20;
    private final TileController tileController;
    private final BufferedImage completeMap;
    private final Map map;
    private Consumer<MouseEvent> mouseHandler;
    private boolean mapCreated;
    private boolean needUpdate;

    /**
     * Constructor that link this component in such a way that it can be listened.
     * @param map Initializes the internal map.
     */
    public MapCreator(final Map map) {
        this.completeMap = new BufferedImage(matrixSize * IMAGE_SIZE, matrixSize * IMAGE_SIZE, BufferedImage.TYPE_INT_RGB);
        this.tileController = new TileController();
        this.addComponentListener(this);
        this.addMouseListener(this);   // Links this panel with a controller of mouse events.
        this.mapCreated = false;
        this.needUpdate = true;
        this.map = map;
    }

    /**
     * Initializes the internal field mouse handler.
     * @param m
     */
    public void addMouseHandler(final Consumer<MouseEvent> m) {
        this.mouseHandler = m;
    }

    /**
     * Method that reads the given matrix and draws the correspondent map by first calculating it cells.
     */
    public void showGridImage() {
        if (!mapCreated) {
            mapCreated = true;
            this.createMapImage();
        }
        this.getGraphics().drawImage(completeMap, 0, 0, this.getWidth(), this.getHeight(), null);
        this.needUpdate = false;
    }

    /**
     * This method, reading the internal field map, calculates the correspondent grid of cells that will be composing the map.
     */
    public void createMapImage() {
        final Graphics g = completeMap.getGraphics();
        for (int row = 0; row < matrixSize; row++) {
            for (int column = 0; column < matrixSize; column++) {
                final Image i = tileController.getImage(this.map.getTileFromGridPosition(new GridPosition(column, row)));
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
     * Simple getter for the matrix size field.
     * @return an integer that is the matrix number of tiles per dimension.
     */
    public int getMatrixSize() {
        return this.matrixSize;
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

    // TODO Find a way to remove this following void methods that compares after implementing interface.

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

    @Override
    public void mouseClicked(final MouseEvent e) {
        // Simple way to obtain and print mouse position when clicking.
        if (e.getButton() == MouseEvent.BUTTON1) {
            this.mouseHandler.accept(e);
        }
    }

 // TODO Find a way to remove this following void methods that compares after implementing interface.

    @Override
    public void mousePressed(final MouseEvent e) {

    }

    @Override
    public void mouseReleased(final MouseEvent e) {

    }

    @Override
    public void mouseEntered(final MouseEvent e) {

    }

    @Override
    public void mouseExited(final MouseEvent e) {

    }
}
