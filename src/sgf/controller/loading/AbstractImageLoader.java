package sgf.controller.loading;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import sgf.model.PathLinker;
import sgf.model.PathLinkerImpl;

/**
 * Class that manages the image loading.
 * @param <T> Is the entity (tile or enemy) type whose image must be loaded in the correct view.
 */
public abstract class AbstractImageLoader<T> implements ImageLoader<T> {
    private final PathLinker imagePaths = new PathLinkerImpl();
    /**
     * This method loads from res folder the right png file.
     * @param pngFile Is the image file name.
     * @return the correct image from res folder.
     */
    public Image loadRightImage(final String pngFile) {
        try {
            return ImageIO.read(new File("res" + File.separator + pngFile));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * This method returns the field PathLinker.
     * @return all the imagePaths.
     */
    public PathLinker getPathImage() {
        return this.imagePaths;
    }
}
