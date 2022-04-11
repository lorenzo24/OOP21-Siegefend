package sgf.managers;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

import sgf.helpers.AbstractImageLoader;
import sgf.model.enemies.EnemyType;

/**
 * This class loads enemy images.
 */
public class EnemyImageManager extends AbstractImageLoader<EnemyType> {
    private final Map<EnemyType, Image> enemySprite = new HashMap<>();

    /**
     * Simple constructor that takes the enemies path names useful for the loading.
     */
    public EnemyImageManager() {
        this.fillMap(super.getPathImage().getEnemyMap());
    }


    @Override
    public void fillMap(final Map<EnemyType, String> map) {
        map.entrySet().forEach(x -> this.enemySprite.put(x.getKey(), this.loadRightImage(x.getValue())));
    }

    @Override
    public Image getImage(final EnemyType element) {
        return this.enemySprite.get(element);
    }

    @Override
    public Image getImage(final EnemyType t) {
        return this.enemySprite.get(t);
    }
}
