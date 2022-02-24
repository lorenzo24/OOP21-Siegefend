package sgf.view;

import sgf.controller.EnemyImageController;
import sgf.model.Enemy;
import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

/**
 *
 */
public class EnemyView extends JPanel{

    private static final int IMAGE_SIZE = 80;
    private final MapCreator pannel;
    private final EnemyImageController imgControl;
    private BufferedImage field;

    /**
     * 
     * @param pannel
     */
    public EnemyView(final MapCreator pannel) {
        this.pannel = pannel;
        this.field = new BufferedImage(this.pannel.getMatrixSize() * IMAGE_SIZE, this.pannel.getMatrixSize() * IMAGE_SIZE, BufferedImage.TYPE_INT_ARGB);
        this.imgControl = new EnemyImageController(this.pannel.getMatrixSize());
        pannel.setLayout(new BorderLayout());
        pannel.add(this, BorderLayout.CENTER);
        this.setOpaque(false);
        this.setVisible(true);
        pannel.validate();
    }

    /**
     * 
     * @param enemy
     */
    public void drawEnemy(final Enemy enemy) {
        final var g = this.getGraphics();
        final var gImage = this.field.getGraphics();
        final var x = enemy.getPosition().getX();
        final var y = enemy.getPosition().getY();
        final var width = this.imgControl.tileSize(this.field.getWidth());
        final var height = this.imgControl.tileSize(this.field.getHeight());
        gImage.drawImage(this.imgControl.spriteImage(0), (int) x, (int) y, width, height, null);
        g.drawImage(field, 0, 0, this.pannel.getWidth(), this.pannel.getHeight(), null);
    }

    public void clear() {
        //final var g = this.enemyPanel.getGraphics();
        //g.clearRect(0, 0, this.enemyPanel.getWidth(), this.enemyPanel.getHeight());
        //this.field.getGraphics().clearRect(0, 0, this.enemyPanel.getWidth(), this.enemyPanel.getHeight());
        
    }
    
    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        
    }
}
