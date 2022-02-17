package sgf.view;

import java.awt.Image;
import java.awt.event.*;
import javax.swing.JPanel;

import sgf.controller.TileController;

/**
 * 
 * This class is responsible for the process of map showing.
 *
 */
public class MapCreator extends JPanel implements ComponentListener {
    private static final long serialVersionUID = -7141712951441617040L;
    private static final int MATRIX_SIZE = 20;
    private final TileController tileController = new TileController();

    /**
     * Constructor that link this component in such a way that it can be listened.
     */
    public MapCreator() {
        this.addComponentListener(this);
    }

    /**
     * Method that reads the given matrix and draws the correspondent grid of tiles.
     * @param matrixToBeShowed Is the matrix given in  input as map structure.
     */
    public void showGridImage(final int[][] matrixToBeShowed) {
        final int imageWidth = this.adaptImageSize(this.getWidth());
        final int imageHeight =  this.adaptImageSize(this.getHeight());
        for (int row = 0; row < MATRIX_SIZE; row++) {
            for (int column = 0; column < MATRIX_SIZE; column++) {
                // Get from TileController the correct image from matrix and displays it.
                final Image img = tileController.getImage(matrixToBeShowed[row][column]);
                this.getGraphics().drawImage(img, column * imageWidth, row * imageHeight, null);
            }
        }
    }

    /**
     * This method adapts the image's size to the panel.
     * @param dimension Is the panel size.
     * @return an integer that denotes the best-fit dimension.
     */
    private int adaptImageSize(final double dimension) {
        return (int) Math.round(dimension / MATRIX_SIZE);
    }

    @Override
    public void componentResized(final ComponentEvent e) {
        this.tileController.correctImagesSize(this.adaptImageSize(this.getWidth()), this.adaptImageSize(this.getHeight()));
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
    // TODO Find a way to remove this void methods that compares after implementin interface.
}
