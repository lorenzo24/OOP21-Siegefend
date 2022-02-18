package sgf.controller;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import sgf.model.Tile;
import sgf.model.TileType;

/**
 * This class is responsible for the creation of all tile types.
 */
public class TileController {
    private final Map<TileType, Image> imageTileSelector;       // Map that contains all the correspondences betwenn TileType and the specific images.

    /**
     * Constructor that fills the arrays with all types of tiles.
     */
    public TileController() {
        this.imageTileSelector = new HashMap<>();
        this.fillMap(this.imageTileSelector); // Method that fills the primary map.
    }

    /**
     * This method returns the image of a given tile type.
     * @param tile Represents the type of the image we want the sprite.
     * @return the image of the specific type of tile.
     */
    public Image getImage(final Tile tile) {
        return this.imageTileSelector.get(tile.getTileType());
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
