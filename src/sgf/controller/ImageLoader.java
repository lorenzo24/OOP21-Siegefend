package sgf.controller;

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
public abstract class ImageLoader<T> implements ImageLoad {
    private final UrlImage pippo = new UrlImageImpl();

    // This method loads from res folder the right png file.
    public Image loadRightImage(final String pngFile) {
        try {
            return ImageIO.read(new File("res" + File.separator + pngFile));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public UrlImage getPippo() {
        return this.pippo;
    }
    
}
