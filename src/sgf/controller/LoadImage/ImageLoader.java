package sgf.controller.LoadImage;

import java.awt.Image;
import java.util.Map;

/**
 *
 */
public interface ImageLoader<T> {

    Image loadRightImage(final String pngFile);
    
    void fillMap(final Map<T, String> elems);
}