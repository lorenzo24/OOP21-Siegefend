package sgf.utilities;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

import javax.imageio.ImageIO;

/**
 * 
 */
public class SimpleTurretsImageLoader implements TurretImagesLoader {

    private Map<String, BufferedImage> images;

    /**
     * 
     */
    public SimpleTurretsImageLoader() {
        this.loadAllImages();
    }

    private void loadAllImages() {
        try {
            this.images = Map.of("sample", ImageIO.read(new File("res/test/vuoto.png")));
        } catch (IOException e) {
            this.images = Map.of();
        }
    }

    @Override
    public Optional<BufferedImage> getTurretImage(final String turretName) {
        if (turretName == null) {
            return Optional.empty();
        } else {
            return Optional.of(this.images.get(turretName));
        }
    }

}
