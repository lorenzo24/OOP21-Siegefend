package sgf.managers;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

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
        Stream.iterate(0, i -> i + 1).limit(map.size()).forEach(x -> this.turretSprite.put(x, this.loadRightImage(map.get(x))));
    }

    @Override
    public Image getImage(final Integer element) {
        return this.turretSprite.get(element);
    }
}
