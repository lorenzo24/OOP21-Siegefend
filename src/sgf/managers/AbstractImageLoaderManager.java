package sgf.managers;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import sgf.model.game.PathLinker;
import sgf.model.game.PathLinkerImpl;

/**
 * Class that manages the {@link Image} loading from file.
 * @param <T> Is the entity type whose {@link Image} must be loaded in the correct view.
 */
public abstract class AbstractImageLoaderManager<T> implements ImageLoaderManager<T> {
    // A path linker contains all associations between enemy or tile type and its correspondent image.
    private final PathLinker imagePaths = new PathLinkerImpl(); 

    @Override
    public Image loadRightImage(final String pngFile) {
        try {
            try (InputStream is = ClassLoader.getSystemResourceAsStream(pngFile)) {
                return ImageIO.read(is);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB); // In case of error returns an empty image.
        }
    }

    @Override
    public PathLinker getPathImage() {
        return this.imagePaths;
    }
}
