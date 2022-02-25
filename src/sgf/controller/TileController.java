package sgf.controller;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.imageio.ImageIO;

import sgf.model.Tile;
import sgf.model.TileType;

/**
 * This class is responsible for the creation of all tile types.
 */
public class TileController extends AbstractImageLoader<TileType> {
    private final Map<TileType, Image> imageTileSelector;       // Map that contains all the correspondences betwenn TileType and the specific images.

    /**
     * Constructor that fills the arrays with all types of tiles.
     */
    public TileController() {
        this.imageTileSelector = new HashMap<>();
        this.fillMap(super.getPathImage().getTileMap());
    }

    /**
     * This method returns the image of a given tile type.
     * @param type Represents the type of the image we want the sprite.
     * @return the image of the specific type of tile.
     */
    public Image getImage(final Tile type) {
        return this.imageTileSelector.get(type.getTileType());
    }

    @Override
    public void fillMap(final Map<TileType, String> elems) {
        for (final var elem : elems.entrySet()) {
            this.imageTileSelector.put(elem.getKey(), this.loadRightImage(elem.getValue()));
        }
    }
}
