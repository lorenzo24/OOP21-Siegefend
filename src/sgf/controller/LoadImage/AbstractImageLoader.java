package sgf.controller.LoadImage;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import sgf.model.Tile;
import sgf.model.TileType;
import sgf.model.UrlImage;
import sgf.model.UrlImageImpl;

/**
 *
 * @param <T>
 */
public abstract class AbstractImageLoader<T> implements ImageLoader<T> {
    private final UrlImage pathImage = new UrlImageImpl();

    // This method loads from res folder the right png file.
    public Image loadRightImage(final String pngFile) {
        try {
            return ImageIO.read(new File("res" + File.separator + pngFile));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 
     * @return
     */
    public UrlImage getPathImage() {
        return this.pathImage;
    }
}
