package sgf.controller.LoadImage;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

import sgf.model.EnemyType;

/**
 *
 */
public class EnemyImageController extends AbstractImageLoader<EnemyType> {
    private final Map<EnemyType, Image> enemySprite = new HashMap<>();
    private final int matrixSize;

    /**
     * 
     * @param matrixSize
     */
    public EnemyImageController(final int matrixSize) {
        this.fillMap(super.getPathImage().getEnemyMap());
        this.matrixSize = matrixSize;
    }

    /**
     * 
     * @param size
     * @return Ciao Pino
     */
    public int tileSize(final int size) {
        return size / matrixSize;
    }

    /**
     * 
     * @param type
     * @return
     */
    public Image spriteImage(final EnemyType type) {
        return this.enemySprite.get(type);
    }

    @Override
    public void fillMap(final Map<EnemyType, String> elems) {
        for (final var elem : elems.entrySet()) {
            this.enemySprite.put(elem.getKey(), this.loadRightImage(elem.getValue()));
        }
    }
}
