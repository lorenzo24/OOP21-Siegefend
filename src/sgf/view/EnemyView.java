package sgf.view;

import sgf.controller.EnemyImageController;
import sgf.model.Enemy;
import java.awt.*;

import javax.swing.JPanel;

/**
 *
 */
public class EnemyView {

    private final MapCreator pannel;
    private final EnemyImageController imgControl;
    private final JPanel enemyPanel;

    /**
     * 
     * @param pannel
     */
    public EnemyView(final MapCreator pannel) {
        this.pannel = pannel;
        this.imgControl = new EnemyImageController(this.pannel.getMatrixSize());
        pannel.setLayout(new BorderLayout());
        enemyPanel = new JPanel();
        pannel.add(enemyPanel, BorderLayout.CENTER);
        enemyPanel.setVisible(true);
        pannel.validate();
    }

    /**
     * 
     * @param enemy
     */
    public void drawEnemy(final Enemy enemy) {
        final var g = this.enemyPanel.getGraphics();
        final var x = enemy.getPosition().getX();
        final var y = enemy.getPosition().getY();
        final var width = this.imgControl.tileSize(this.pannel.getWidth());
        final var height = this.imgControl.tileSize(this.pannel.getHeight());
        g.drawImage(this.imgControl.spriteImage(0), (int) x, (int) y, width, height, null);
    }
}
