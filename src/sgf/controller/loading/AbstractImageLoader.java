package sgf.controller.loading;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import sgf.model.PathLinker;
import sgf.model.PathLinkerImpl;

/**
 * Class that manages the image loading from file.
 * @param <T> Is the entity (Tile or Enemy) type whose image must be loaded in the correct view.
 */
public abstract class AbstractImageLoader<T> implements ImageLoader<T> {
    // A path linker contains all associations between enemy or tile type and its correspondent image.
    private final PathLinker imagePaths = new PathLinkerImpl(); 

    /**
     * This method loads from res folder the right .png file given as parameter.
     * @param pngFile Is the image file name.
     * @return the correct image from res folder.
     * @throws IOException if file is not found.
     */
    public Image loadRightImage(final String pngFile) {
        try {
            return ImageIO.read(new File("res" + File.separator + pngFile));
        } catch (IOException e) {
            e.printStackTrace();
            return new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB); // In case of error, returns an empty image.
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
