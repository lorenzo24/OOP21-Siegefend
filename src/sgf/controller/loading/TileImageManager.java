package sgf.controller.loading;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;
import sgf.model.Tile;
import sgf.model.TileType;

/**
 * This class contains the links between tile types and corresponding images.
 */
public class TileImageManager extends AbstractImageLoader<TileType> {
    private final Map<TileType, Image> imageTileSelector;       // Map that contains all the correspondences between tile types and the corresponding images.

    /**
     * Constructor that fills the field with all correspondences between tile types and images path.
     */
    public TileImageManager() {
        this.imageTileSelector = new HashMap<>();
        this.fillMap(super.getPathImage().getTileMap()); 
    }

    /**
     * This method returns the image of a given tile type.
     * @param tile Represents the type of the image we want the sprite.
     * @return the image of the specific type of tile.
     */
    public Image getImage(final Tile tile) {
        return this.imageTileSelector.get(tile.getTileType());
    }

    @Override
    public void fillMap(final Map<TileType, String> map) {
        for (final var elem : map.entrySet()) {
            this.imageTileSelector.put(elem.getKey(), this.loadRightImage(elem.getValue()));    // Method loadRightImage is from extended class.
        }
    }
}
