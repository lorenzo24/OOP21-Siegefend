package sgf.managers;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;
import sgf.helpers.AbstractImageLoader;

/**
 * This class loads turret images.
 */
public class TurretImageManager extends AbstractImageLoader<Integer> {

    private final Map<Integer, Image> turretSprite = new HashMap<>();

    /**
     * Simple constructor that takes the turrets path names useful for the loading.
     */
    public TurretImageManager() {
        this.fillMap(super.getPathImage().getTurretMap());
    }

    @Override
    public void fillMap(final Map<Integer, String> map) {
        for (final var elem : map.entrySet()) {
            this.turretSprite.put(elem.getKey(), this.loadRightImage(elem.getValue()));
        }
    }

    /**
     * This method returns the image of a given turret.
     * @param turret Represents the code of the image we want the sprite.
     * @return the image of the specific turret id.
     */
    public Image getImage(final int turret) {
        return this.turretSprite.get(turret);
    }
}
