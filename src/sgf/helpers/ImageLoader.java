package sgf.helpers;

import java.awt.Image;
import java.util.Map;

import sgf.model.game.PathLinker;

/**
 * Interface implemented from image loading.
 * @param <T> Is the type of the entity whose image must be loaded.
 */
public interface ImageLoader<T> {

    /**
     * This method loads from res folder the right .png file given as parameter.
     * @param pngFile Is the image file name.
     * @return the correct image from res folder.
     * @throws IOException if file is not found.
     */
    Image loadRightImage(String pngFile);

    /**
     * This method returns the image of a given element.
     * @param element Represents the element of the image we want the sprite.
     * @return the image of the specific type.
     */
    Image getImage(T element);

    /**
     * Fills the map with links between types and filenames.
     * @param map Is the map to be filled.
     */
    void fillMap(Map<T, String> map);

    /**
     * This method returns the field PathLinker.
     * @return all the imagePaths.
     */
    PathLinker getPathImage();

}
