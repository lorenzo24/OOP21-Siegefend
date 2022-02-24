package sgf.controller;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import sgf.model.TileType;
import sgf.model.UrlImage;
import sgf.model.UrlImageImpl;

/**
 *
 * @param <T>
 */
public class ImageLoader<T> {
    private final Map<T, Image> imageSelector;
    private final UrlImage urlIm = new UrlImageImpl();

    /**
     * 
     */
    public ImageLoader() {
        this.imageSelector = new HashMap<>();
        //this.fillMap();
    }

    /**
     * This method returns the image of a given tile type.
     * @param type Represents the type of the image we want the sprite.
     * @return the image of the specific type.
     */
    public Image getImage(final T type) {
        return this.imageSelector.get(type);
    }

    // This method loads from res folder the right png file.
    private Image loadRightImage(final String pngFile) {
        try {
            return ImageIO.read(new File("res" + File.separator + pngFile));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void fillMap(final Map<T, String> elems) {
        for (final var elem : elems.entrySet()) {
            this.imageSelector.put(elem.getKey(), this.loadRightImage(elem.getValue()));
        }
    }
}
