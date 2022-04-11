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
    private final Image lifeImage;

    /**
     * Simple constructor that takes the enemies path names useful for the loading.
     */
    public EnemyImageManager() {
        this.fillMap(super.getPathImage().getEnemyMap());
        this.lifeImage = this.loadLifeImage();
    }

    /**
     * Method that returns the image corresponding the given enemy type.
     * @param type Is the enemy type we want the image.
     * @return the correct image.
     */
    public Image spriteImage(final EnemyType type) {
        return this.enemySprite.get(type);
    }

    /**
     * Method that returns the image of the life bar.
     * @return the correct image.
     */
    public Image barLife() {
        return this.lifeImage;
    }

    private Image loadLifeImage() {
        return this.loadRightImage(this.getPathImage().getLifeBar());
    }

    @Override
    public void fillMap(final Map<EnemyType, String> map) {
        for (final var elem : map.entrySet()) {
            this.enemySprite.put(elem.getKey(), this.loadRightImage(elem.getValue()));
        }
    }

    @Override
    public Image getImage(final EnemyType t) {
        return this.enemySprite.get(t);
    }
}
