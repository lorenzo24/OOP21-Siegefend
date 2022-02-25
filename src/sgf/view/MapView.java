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
import sgf.controller.loading.TileImageController;
import sgf.model.GridPosition;
import sgf.model.Map;

/**
 * This class is responsible for the process of map showing. It involves 2 steps: a calculation 
 * and composition of a grid and the creation and appearance of the corresponding final map image.
 */
public class MapView extends JPanel implements ComponentListener, MouseListener {
    private static final long serialVersionUID = -7141712951441617040L;
    private final int matrixSize;       // Number of tiles in each grid size.
    private final TileImageController tileController;   // Field that contains all the links between tile types and corresponding images.
    private final BufferedImage completeMap;    // Map to be showed after creation process.
    private final Map map;      // Model field into View. Is it correct??? Remember to remove from here.
    private Consumer<MouseEvent> mouseHandler;  // Manager for user click into grid tiles.
    private final int size;
    private boolean mapCreated; // Checks if the map has already been created.
    private boolean needUpdate; // Checks if the map needs an update after resizing.

    /**
     * Constructor that initializes fields and links this panel with mouse listener.
     * @param map
     * @param size
     */
    public MapView(final Map map, final int size) {
        this.matrixSize = map.getMapSize();
        this.completeMap = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);   // Final map is empty at the beginning.
        this.tileController = new TileImageController();
        this.addComponentListener(this);
        this.addMouseListener(this);   // Links this panel with a controller of mouse events.
        this.size = size;
        this.mapCreated = false;
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
     * This method, reading the internal field map, calculates the correspondent grid of cells that will be composing the map.
     */
    public void createMapImage() {
        final Graphics g = completeMap.getGraphics();
        final int tileSize = this.size / this.matrixSize;
        for (int row = 0; row < matrixSize; row++) {
            for (int column = 0; column < matrixSize; column++) {
                final Image i = tileController.getImage(this.map.getTileFromGridPosition(new GridPosition(column, row)));
                g.drawImage(i, column * tileSize, row *  tileSize,  tileSize,  tileSize, null);
            }
        }
        try {
            ImageIO.write(completeMap, "PNG", new File("res" + File.separator + "testimage.png"));      // Creates the final map. It will be showed as game interactive background.
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    }

    @Override
    public void componentShown(final ComponentEvent e) {
    }

    @Override
    public void componentHidden(final ComponentEvent e) {
    }

    @Override
    public void mouseClicked(final MouseEvent e) {
        // Simple way to obtain and print mouse position and tile type when clicking.
        if (e.getButton() == MouseEvent.BUTTON1) {
            //this.mouseHandler.accept(e);
            final int gridColumn = this.convertCoordinate(e.getX(), this.getWidth());
            final int gridRow = this.convertCoordinate(e.getY(), this.getHeight());
            System.err.println(gridRow + ": " + gridColumn);
            System.out.println(this.map.getTileFromGridPosition(new GridPosition(gridColumn, gridRow)).getTileType());
        }
    }

    // Taken a value and the dimension in refers to, it returns an integer value that is the corresponding tile position in the dimension.
    private int convertCoordinate(final int x, final double dimension) {
        final double sizeOfASingleTile = dimension / this.getMatrixSize();
        return (int) (x / sizeOfASingleTile);
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

    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        if (!mapCreated) {
            mapCreated = true;
            this.createMapImage();
        }
        g.drawImage(completeMap, 0, 0, this.getWidth(), this.getHeight(), null);
    }
}
