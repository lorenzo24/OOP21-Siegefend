package sgf.view;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import sgf.model.Enemy;

/**
 *
 */
public class EnemyView {

    private JPanel pannel;

    /**
     * 
     * @param pannel
     */
    public EnemyView(final JPanel pannel) {
        this.pannel = pannel;
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
     * @param enemy
     */
    public void drawEnemy(final Enemy enemy) {
        var g = pannel.getGraphics();
        var image = loadRightImage("tank.png");
        g.drawImage(image, 50, 50, 50, 50, null);
        g.dispose();
    }    
}
