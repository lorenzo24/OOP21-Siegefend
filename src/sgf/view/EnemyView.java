package sgf.view;

import sgf.controller.EnemyImageController;
import sgf.model.Enemy;
import sgf.model.EnemyType;

import java.awt.*;
import java.awt.image.BufferedImage;
import  java.util.List;
import javax.swing.JPanel;

/**
 *
 */
public class EnemyView extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = 6345414040020937047L;
    private final EnemyImageController imgControl;
    private final BufferedImage field;
    private final List<Enemy> enemyList;

    /**
     * 
     * @param size
     * @param matrixSize
     * @param enemyList
     */
    public EnemyView(final int size, final int matrixSize, final List<Enemy> enemyList) {
        this.field = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        this.imgControl = new EnemyImageController(matrixSize);
        this.enemyList = enemyList;
    }

    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        final var gImage = (Graphics2D) this.field.getGraphics();
        final var width = this.imgControl.tileSize(this.field.getWidth());
        final var height = this.imgControl.tileSize(this.field.getHeight());
        gImage.setBackground(new Color(255,255,255,0));
        gImage.clearRect(0, 0, this.field.getWidth(), this.field.getHeight());
        for (final Enemy enemy : enemyList) {
            final var x = enemy.getPosition().getX();
            final var y = enemy.getPosition().getY();
            enemy.move();
            gImage.drawImage(this.imgControl.spriteImage(EnemyType.TANK), (int) x, (int) y, width, height, null);
        }
        g.drawImage(field, 0, 0, this.getWidth(), this.getHeight(), null);
    }
}
