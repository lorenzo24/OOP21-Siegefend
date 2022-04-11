package sgf.helpers;

import java.awt.image.BufferedImage;
import java.util.Optional;

/**
 * Loads the images of turrets.
 */
public interface TurretImagesLoader {

    /**
     * Returns the image of the turret with the given id.
     * @param turretID the id of the turret
     * @return an {@link Optional} of {@link BufferedImage} with the image of the turret
     */
    Optional<BufferedImage> getTurretImage(int turretID);

    /**
     * Returns the image of the turret with the given id, or a default image if none is found.
     * @param turretID the id of the turret
     * @return a {@link BufferedImage}
     */
    BufferedImage getTurretImageOrDefault(int turretID);
}
