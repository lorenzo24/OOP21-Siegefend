package sgf.view.map;

import java.awt.Graphics;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import sgf.controller.map.MapController;
import sgf.model.map.GridPosition;
import sgf.model.map.Map;

/**
 * This class is responsible for the process of map showing. It involves 2 steps: a calculation 
 * and composition of a grid and the creation and appearance of the corresponding final map image.
 */
public class MapViewImpl extends AbstractMapView implements ComponentListener {
    private static final long serialVersionUID = -7141712951441617040L;
    private MapController mapController;
    private final Map map;
    private final int matrixSize;       // Number of tiles in each grid size.
    private BufferedImage completeMap;    // Map to be shown after creation process.
    //private Consumer<MouseEvent> mouseHandler;  // Manager for user click into grid tiles.
    private boolean isControllerSet;
    private boolean ready;

    /**
     * Constructor that initializes fields and links this panel with mouse listener.
     * @param map The logic map of the current level
     */
    public MapViewImpl(final Map map) {
        this.map = map;
        this.matrixSize = map.getSize();
        this.setVisible(false);
    }

    /**
     * Simple getter for the matrix size field.
     * @return an integer that is the matrix number of tiles per dimension.
     */
    @Override
    public int getMatrixSize() {
        return this.matrixSize;
    }

    @Override
    public void componentResized(final ComponentEvent e) {
    }

    // Taken a value and the dimension in refers to, it returns an integer value that is the corresponding tile position in the dimension.
    private int convertCoordinate(final int x, final double dimension) {
        final double sizeOfASingleTile = dimension / this.getMatrixSize();
        return (int) (x / sizeOfASingleTile);
    }

    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        if (this.ready) {
            g.drawImage(completeMap, 0, 0, this.getWidth(), this.getHeight(), null);
        }
    }

    @Override
    public void setController(final MapController controller) {
        if (!isControllerSet) {
            this.isControllerSet = true;
            this.mapController = controller;
            this.completeMap = this.mapController.getMapImage();
        }
    }

    @Override
    public void start() {
        if (isControllerSet) {
            this.addComponentListener(this);
            this.ready = true;
            this.setVisible(true);
        } else {
            throw new IllegalStateException("Cannot invoke start() if the controller has not been set.");
        }
    }

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
    public void stopView() {
    }
}
