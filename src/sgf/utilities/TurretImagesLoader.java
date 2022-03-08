package sgf.utilities;

import java.awt.image.BufferedImage;
import java.util.Optional;

/**
 * Loads the images of turrets.
 */
public interface TurretImagesLoader {

    /**
     * Returns the image of the turret with the given name.
     * @param turretName The name of the turret
     * @return a {@link BufferedImage} with the image of the turret
     */
    Optional<BufferedImage> getTurretImage(String turretName);
}
