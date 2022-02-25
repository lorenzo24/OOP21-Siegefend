package sgf.view;

import sgf.controller.loading.EnemyImageController;
import sgf.model.Enemy;
import sgf.model.EnemyType;
import java.awt.*;
import java.awt.image.BufferedImage;
import  java.util.List;
import javax.swing.JPanel;

/**
 * Panel for enemy's movement and appearance.
 */
public class EnemyView extends JPanel {
    private static final long serialVersionUID = 6345414040020937047L;
    private static final int RGB_MAX = 255;     // Maximum value that a RGB parameter must assume.
    private final EnemyImageController imageController;      // Contains the links between enemy type and images.
    private final BufferedImage image;  // Empty image of total panel size to replace and hide previous effective enemy image.
    private final List<Enemy> enemyList;        // List of enemies to be showed.

    /**
     * Constructor that sets the image, image controller and list of enemies.
     * @param size Is the size that the enemy's image must have.
     * @param tileSize Is the size of a single tile in the map.
     * @param enemyList Is the list of enemies to be showed.
     */
    public EnemyView(final int size, final int tileSize, final List<Enemy> enemyList) {
        this.image = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        this.imageController = new EnemyImageController(tileSize);
        this.enemyList = enemyList;
    }

    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        final var gImage = (Graphics2D) this.image.getGraphics();       // Cast useful in order to avoid "trail effect" in movement.
        final var width = this.imageController.tileSize(this.image.getWidth());
        final var height = this.imageController.tileSize(this.image.getHeight());
        gImage.setBackground(new Color(RGB_MAX, RGB_MAX, RGB_MAX, 0));
        gImage.clearRect(0, 0, this.image.getWidth(), this.image.getHeight());  // Clear the image area before repaint in another position.

        // For each enemy in the list move and repaint it.
        for (final Enemy enemy : enemyList) {
            final var x = enemy.getPosition().getX();
            final var y = enemy.getPosition().getY();
            enemy.move();
            gImage.drawImage(this.imageController.spriteImage(EnemyType.TANK), (int) x, (int) y, width, height, null);
        }

        // The panel is covered with an empty image in order to hide the previous enemy image displayed.
        g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), null);
    }
}
