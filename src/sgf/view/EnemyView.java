package sgf.view;

import sgf.controller.EnemyImageController;
import sgf.model.Enemy;
import java.awt.*;

/**
 *
 */
public class EnemyView {

    private final MapCreator pannel;
    private final EnemyImageController imgControl;

    /**
     * 
     * @param pannel
     */
    public EnemyView(final MapCreator pannel) {
        this.pannel = pannel;
        this.imgControl = new EnemyImageController(this.pannel.getMatrixSize());
    }

    /**
     * 
     * @param enemy
     */
    public void drawEnemy(final Enemy enemy) {
        final var g = this.pannel.getGraphics();
        final var x = enemy.getPosition().getX();
        final var y = enemy.getPosition().getY();
        final var width = this.imgControl.tileSize(this.pannel.getWidth());
        final var height = this.imgControl.tileSize(this.pannel.getHeight());
        g.drawImage(this.imgControl.spriteImage(0), (int) x, (int) y, width, height, null);
    }
}
