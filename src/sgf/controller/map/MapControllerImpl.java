package sgf.controller.map;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import sgf.helpers.ImgTileSize;
import sgf.managers.TileImageManager;
import sgf.model.map.GridPosition;
import sgf.model.map.Map;
import sgf.view.map.MapView;

/**
 * This class is responsible for the map's creation.
 */
public class MapControllerImpl implements MapController {
    private final Map map;
    private MapView mapView;    // Linked View class.
    private final int tileSize;
    private final BufferedImage mapImage;    // Map to be shown after the creation process.
    private final TileImageManager tileManager;   // Field that contains all the links between tile types and corresponding images.
    private boolean isControllerSet;

    /**
     * Constructor that sets up the map's image.
     * @param map
     */
    public MapControllerImpl(final Map map) {
        this.map = map;
        this.tileSize = ImgTileSize.getTileSize();
        this.mapImage = new BufferedImage(this.map.getSize() * this.tileSize, this.map.getSize() * this.tileSize, BufferedImage.TYPE_INT_RGB);   // Complete map is empty at the beginning.
        this.tileManager = new TileImageManager();
        this.createImage();
    }

    @Override
    public Map getMap() {
        return this.map;
    }

    @Override
    public BufferedImage getMapImage() {
        return this.mapImage;
    }

    @Override
    public void setView(final MapView view) {
        if (!isControllerSet) {
            this.isControllerSet = true;
            this.mapView = view;
        }
    }
 
    // Method that creates the complete map as a matrix of tiles reading which type each of them has supposed to be. 
    private void createImage() {
        final Graphics g = this.mapImage.getGraphics();
        for (int row = 0; row < this.map.getSize(); row++) {
            for (int column = 0; column < this.map.getSize(); column++) {
                final Image i = tileManager.getImage(this.map.getTileFromGridPosition(new GridPosition(row, column)).getTileType());
                g.drawImage(i, column * this.tileSize, row *  this.tileSize,  this.tileSize,  this.tileSize, null);
            }
        }
    }
}
