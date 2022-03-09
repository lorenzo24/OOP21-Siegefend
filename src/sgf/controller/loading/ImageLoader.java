package sgf.controller.loading;

import java.awt.Image;
import java.util.Map;

/**
 * Interface implemented from image loading.
 * @param <T> is the type of the entity whose image must be loaded.
 */
public interface ImageLoader<T> {

    /**
     * Returns the corresponding image from a given string that is its pathname.
     * @param pngFile Is the name of the file.
     * @return the correct image.
     */
    Image loadRightImage(String pngFile);

    /**
     * Fills the map with links between types and filenames.
     * @param map Is the map to be filled.
     */
    void fillMap(Map<T, String> map);
}
