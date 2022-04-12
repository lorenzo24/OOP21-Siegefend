package sgf.managers;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;
import sgf.helpers.AbstractImageLoader;
import sgf.model.map.TileType;

/**
 * This class contains the links between tile types and corresponding images.
 */
public class TileImageManager extends AbstractImageLoader<TileType> {
    // Map that contains all the correspondences between tile types and the corresponding images.
    private final Map<TileType, Image> imageTileSelector;

    /**
     * Constructor that fills the field with all correspondences between tile types and image paths.
     */
    public TileImageManager() {
        this.imageTileSelector = new HashMap<>();
        this.fillMap(super.getPathImage().getTileMap()); 
    }

    @Override
    public void fillMap(final Map<TileType, String> map) {
        map.entrySet().forEach(x -> this.imageTileSelector.put(x.getKey(), this.loadRightImage(x.getValue())));
    }

    @Override
    public Image getImage(final TileType element) {
        return this.imageTileSelector.get(element);
    }
}
