package sgf.managers;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import sgf.helpers.AbstractImageLoader;

/**
 * Class for load the bar life.
 */
public class BarLifeImageManager extends AbstractImageLoader<Integer> {

    private final Map<Integer, Image> lifeSprite = new HashMap<>();

    /**
     * Simple constructor that takes the barlife path names useful for the loading.
     */
    public BarLifeImageManager() {
        this.fillMap(super.getPathImage().getLifeBarMap());
    }

    @Override
    public void fillMap(final Map<Integer, String> map) {
        Stream.iterate(0, i -> i + 1).limit(map.size()).forEach(x -> this.lifeSprite.put(x, this.loadRightImage(map.get(x))));
    }

    @Override
    public Image getImage(final Integer element) {
        return this.lifeSprite.get(element);
    }
}
