package sgf.view;

import sgf.controller.EnemyController;
import sgf.controller.loading.EnemyImageManager;
import sgf.model.ImgTileSize;
import sgf.utilities.EnemyManager;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import  java.util.List;

/**
 * Panel for enemy's movement and appearance.
 */
public class EnemyViewImpl extends AbstractEnemyView {
    private static final long serialVersionUID = 6345414040020937047L;
    private static final int RGB_MAX = 255;     // Maximum value that a RGB parameter must assume.
    private EnemyController enemyController;
    private final EnemyImageManager imageController;      // Contains the links between enemy type and images.
    private final BufferedImage image;  // Empty image of total panel size to replace and hide previous effective enemy image.
    private List<EnemyManager> enemyList;       // List of enemies to be showed.
    private boolean isControllerSet;
    private final int tileSize;

    /**
     * Constructor that sets the image, image controller and list of enemies.
     * @param matrixSize Is the size that the enemy's image must have.
     */
    public EnemyViewImpl(final int matrixSize) { // TODO: pass Level instead of first argument(?).
        this.tileSize = ImgTileSize.getTileSize();
        this.image = new BufferedImage(matrixSize * this.tileSize, matrixSize * this.tileSize, BufferedImage.TYPE_INT_ARGB);
        this.imageController = new EnemyImageManager(this.tileSize);
        this.enemyList = new ArrayList<>();
    }

    @Override
    public void setList(final List<EnemyManager> enemies) {
        this.enemyList = enemies;
    }

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        final var gImage = (Graphics2D) this.image.getGraphics();
        gImage.setBackground(new Color(RGB_MAX, RGB_MAX, RGB_MAX, 0));
        gImage.clearRect(0, 0, this.image.getWidth(), this.image.getHeight());  // Clear the image area before repaint in another position.

        // For each enemy in the list repaint it.
        this.enemyList.forEach(x -> gImage.drawImage(this.imageController.spriteImage(x.getEnemy().getEnemyType()),
                (int) x.getEnemy().getPosition().getX(),
                (int) x.getEnemy().getPosition().getY(),
                this.tileSize, this.tileSize, null));

        // The panel is covered with an empty image in order to hide the previous enemy image displayed.
        g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), null);
    }

    @Override
    public void setController(final EnemyController controller) {
        if (!isControllerSet) {
            this.isControllerSet = true;
            this.enemyController = controller;
        }
    }
}
