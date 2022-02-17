package sgf.view;

import java.awt.image.BufferedImage;

import java.awt.Image;
import java.awt.event.*;
import javax.swing.JPanel;

import sgf.controller.TileController;

/**
 * 
 * This class is responsible for the process of map's creation and showing.
 *
 */
public class MapCreator extends JPanel implements ComponentListener{
    private static final long serialVersionUID = -7141712951441617040L;
    private static final int MATRIX_SIZE = 20;
    private final TileController tileController = new TileController();
    private int[][] internalMatrix;
    public MapCreator() {
        this.addComponentListener(this);
    }
    /**
     * Method that loads and show a random image from the array of images.
     * 
     */
    public void showGridImage(int[][] mapToBeShowed) {
      
        final int widthImage = this.sizeImage(this.getWidth());
        final int heightImage =  this.sizeImage(this.getHeight());
        
        for (int x = 0; x < MATRIX_SIZE; x++) {
            for (int y = 0; y < MATRIX_SIZE; y++) {
                final Image img = tileController.getImage(mapToBeShowed[y][x]);
                this.getGraphics().drawImage(img, x * widthImage, y * heightImage, null);
            }
        }
    }
    public void componentResized(ComponentEvent e) {
        this.tileController.correctImagesSize(this.sizeImage(this.getWidth()), this.sizeImage(this.getHeight()));
        
    }

    private int sizeImage(final double dimension) {
        return (int) Math.round(dimension / MATRIX_SIZE);
    }
    @Override
    public void componentMoved(ComponentEvent e) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void componentShown(ComponentEvent e) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void componentHidden(ComponentEvent e) {
        // TODO Auto-generated method stub
        
    }
}
