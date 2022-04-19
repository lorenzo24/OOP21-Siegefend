package sgf.managers;

import java.awt.Image;
import java.util.Map;
import sgf.model.game.PathLinker;

/**
 * Interface implemented from image loading.
 * @param <T> Is the type of the entity whose {@link Image} must be loaded.
 */
public interface ImageLoaderManager<T> {

    /**
     * This method loads from res folder the right .png file given as parameter.
     * @param pngFile Is the {@link Image} file name.
     * @return the correct {@link Image} from res folder.
     * @throws IOException if file is not found.
     */
    Image loadRightImage(String pngFile);

    /**
     * This method returns the {@link Image} of a given element.
     * @param element Represents the element of the {@link Image} we want the sprite.
     * @return the {@link Image} of the specific type.
     */
    Image getImage(T element);

    /**
     * Fills the map with links between types and filenames.
     * @param map Is the map to be filled.
     */
    void fillMap(Map<T, String> map);

    /**
     * This method returns the field {@link PathLinker}.
     * @return all the imagePaths.
     */
    PathLinker getPathImage();

}
