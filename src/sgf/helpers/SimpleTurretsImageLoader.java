package sgf.helpers;

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

    /**
     * 
     */
    public static final int SAMPLE = -1;
    private static final int DEFAULT = -1;

    private Map<Integer, BufferedImage> images;

    /**
     * 
     */
    public SimpleTurretsImageLoader() {
        this.loadAllImages();
    }

    private void loadAllImages() {
        try {
            this.images = Map.of(SAMPLE, ImageIO.read(new File("res/test/vuoto.png")));
        } catch (IOException e) {
            this.images = Map.of();
        }
    }

    @Override
    public Optional<BufferedImage> getTurretImage(final int turretID) {
        return Optional.ofNullable(this.images.get(turretID));
    }

    @Override
    public BufferedImage getTurretImageOrDefault(final int turretID) {
        return this.getTurretImage(turretID).orElse(this.images.get(DEFAULT));
    }

}
