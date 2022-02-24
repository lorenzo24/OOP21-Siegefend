package sgf.view;

import javax.swing.JPanel;
import java.awt.*;

import sgf.model.Enemy;
import sgf.model.EnemyImpl;
import sgf.model.Position;

/**
 *      
 */
public class enemyFieldView extends JPanel {
    private Enemy enemy = new EnemyImpl(0, new Position(50, 50), 100, 0, 0);
    
    @Override
    public void paintComponent(final Graphics g) {
        
    }
}
