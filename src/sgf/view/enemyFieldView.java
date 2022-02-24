package sgf.view;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;

import sgf.model.Enemy;
import sgf.model.EnemyImpl;
import sgf.model.Position;

/**
 *      
 */
public class enemyFieldView extends JPanel {
    /**
     * 
     */
    private static final long serialVersionUID = -2952436858243671674L;
    private Enemy enemy = new EnemyImpl(0, new Position(50, 50), 100, 0, 0);
    private BufferedImage field;
    

    
    
}
