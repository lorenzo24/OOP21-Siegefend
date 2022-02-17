package sgf.controller;

import java.awt.Image;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.awt.*;
import sgf.model.TileType;

/**
 * This class is responsible for the creation of all tile types.
 */
public class TileController {
    private final Map<TileType, Image> imageTileSelector;       // Map that contains all the correspondences betwenn TileType and the specific images.
    private final Map<TileType, Image> cacheMap;        // Copy of the principal map useful to improve performance.
    
    /**
     * Constructor that fills the arrays with all types of tiles.
     */
    public TileController() {
        this.imageTileSelector = new HashMap<>();
        this.cacheMap = new HashMap<>();
        this.fillMap(this.imageTileSelector); // Method that fills the primary map.
        this.fillMap(this.cacheMap); // Method that fills the cache copy.
    }

    /**
     * This method returns the image of a given tile type.
     * @param type Represents the type of the image we want the sprite.
     * @return the image of the specific type of tile.
     */
    public Image getImage(final int type) {
        if (type == 0) {
            return this.imageTileSelector.get(TileType.GRASS);
        } else if (type == 2) {
            return this.imageTileSelector.get(TileType.WATER);
        } else {
            return this.imageTileSelector.get(TileType.PATH);
        }
    }

    /**
     * This method is called after a window resizing and manages to the resizing of the tiles.
     * @param newWidth Is the new width that the image must have.
     * @param newHeight Is the new height that the image must have.
     */
    public void correctImagesSize(final int newWidth, final int newHeight) {
        // Every tile in original map updates its value (the image of the tile type).
        for (final Entry<TileType, Image> element : this.imageTileSelector.entrySet()) {
            final Image newResizedImage = this.cacheMap.get(element.getKey()).getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH); 
            element.setValue(newResizedImage);
        }
    }

    // This method loads from res folder the right png file.
    private Image loadRightImage(final String pngFile) {
        return Toolkit.getDefaultToolkit().createImage("res" + File.separator + pngFile);
    }

    private void fillMap(final Map<TileType, Image> mapToBeFilled) {
        mapToBeFilled.put(TileType.GRASS, this.loadRightImage("grass.png"));
        mapToBeFilled.put(TileType.PATH, this.loadRightImage("sand.png"));
        mapToBeFilled.put(TileType.WATER, this.loadRightImage("water.png"));
    }
}
