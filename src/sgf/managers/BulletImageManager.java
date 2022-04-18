package sgf.managers;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import javax.imageio.ImageIO;

/**
 * This class loads turret images.
 */
public class BulletImageManager extends AbstractImageLoaderManager<Integer> {

    private final Map<Integer, Image> bulletSprite = new HashMap<>();

    /**
     * Simple constructor that takes the turrets path names useful for the loading.
     */
    public BulletImageManager() {
        this.fillMap(super.getPathImage().getBulletMap());
    }

    @Override
    public void fillMap(final Map<Integer, String> map) {
        Stream.iterate(0, i -> i + 1).limit(map.size()).forEach(x -> this.bulletSprite.put(x, this.loadRightImage(map.get(x))));
    }

    @Override
    public Image getImage(final Integer element) {
        return this.bulletSprite.get(element);
    }
}
