package sgf.controller.map;

import sgf.model.ImgTileSize;
import sgf.model.Map;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import sgf.controller.loading.TileImageManager;
import sgf.model.GridPosition;
import sgf.model.Position;
import sgf.view.MapView;

/**
 * This class is responsible for the map's multithreading management and also to show it.
 */
public class MapControllerImpl implements MapController {
    private final Map map;
    private final int tileSize;
    private final BufferedImage mapImage;    // Map to be shown after creation process.
    private final TileImageManager tileManager;   // Field that contains all the links between tile types and corresponding images.
    private MapView mapView;
    private boolean isControllerSet;
    

    /**
     * Constructor that sets up the screen and also start thread loop.
     * @param map
     */
    public MapControllerImpl(final Map map) {
        this.map = map;
        this.tileSize = ImgTileSize.getTileSize();
        this.mapImage = new BufferedImage(this.map.getMapSize() * this.tileSize, this.map.getMapSize() * this.tileSize, BufferedImage.TYPE_INT_RGB);   // Final map is empty at the beginning.
        this.tileManager = new TileImageManager();
        this.createImage();
    }

    @Override
    public void setView(final MapView view) {
        if (!isControllerSet) {
            this.isControllerSet = true;
            this.mapView = view;
        }
    }

    @Override
    public Map getMap() {
        return this.map;
    }

    @Override
    public Position convertToPosition(final GridPosition position) {
        return new Position(position.getColumn() * this.tileSize, position.getRow() * this.tileSize);
    }

    @Override
    public GridPosition convertToGridPosition(final Position position) {
        return new GridPosition((int) position.getY() / tileSize, (int) position.getX() / tileSize);
    }

    @Override
    public BufferedImage getMapImage() {
        return this.mapImage;
    }
    private void createImage() {
        final Graphics g = this.mapImage.getGraphics();
        for (int row = 0; row < this.map.getMapSize(); row++) {
            for (int column = 0; column < this.map.getMapSize(); column++) {
                final Image i = tileManager.getImage(this.map.getTileFromGridPosition(new GridPosition(row, column)));
                g.drawImage(i, column * this.tileSize, row *  this.tileSize,  this.tileSize,  this.tileSize, null);
            }
        }
        try {
            ImageIO.write(this.mapImage, "PNG", new File("res" + File.separator + "testimage.png"));      // Creates the final map. It will be showed as game interactive background.
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
