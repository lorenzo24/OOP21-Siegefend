package sgf.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

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
    private static final int MATRIX_SIZE = 20;  // Instead of constant, can be calculated with getter of field matrix.length in MapImpl.
    private final TileController tileController = new TileController();
//    private int imageWidth = this.adaptImageSize(this.getWidth());
//    private int imageHeight =  this.adaptImageSize(this.getHeight());

    private static final int IMAGE_SIZE = 20;
    private BufferedImage completeMap = new BufferedImage(MATRIX_SIZE * IMAGE_SIZE, MATRIX_SIZE * IMAGE_SIZE, BufferedImage.TYPE_INT_RGB);
    private boolean mapCreated;

    /**
     * Constructor that link this component in such a way that it can be listened.
     */
    public MapCreator() {
        this.addComponentListener(this);
        this.mapCreated = false;
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
//        this.imageWidth = this.adaptImageSize(this.getWidth());
//        this.imageHeight = this.adaptImageSize(this.getHeight());
//        this.tileController.correctImagesSize(this.imageWidth, this.imageHeight);
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
