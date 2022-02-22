package sgf.controller;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

/**
 *
 */
public class EnemyImageController {
    private final List<Image> enemySprite = new ArrayList<>();
    private final int matrixSize;

    /**
     * 
     * @param matrixSize
     */
    public EnemyImageController(final int matrixSize) {
        this.fillSprite();
        this.matrixSize = matrixSize;
    }

    private void fillSprite() {
        this.enemySprite.add(loadRightImage("tank.png"));
    }

    // This method loads from res folder the right png file.
    private Image loadRightImage(final String pngFile) {
        try {
            return ImageIO.read(new File("res" + File.separator + pngFile));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
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
     * @param id
     * @return Ciao pino.
     */
    public Image spriteImage(final int id) {
        return this.enemySprite.get(id);
    }
}
