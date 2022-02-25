package sgf.controller;

import java.awt.Image;
import java.util.Map;

/**
 *
 */
public interface ImageLoad<T> {

    Image loadRightImage(final String pngFile);
    
    void fillMap(final Map<T, Image> elems);
}