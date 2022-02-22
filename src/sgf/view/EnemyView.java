package sgf.view;


import javax.swing.JPanel;
import sgf.controller.EnemyImageController;
import sgf.model.Enemy;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

/**
 *
 */
public class EnemyView implements ComponentListener {

    private final JPanel pannel;
    private final EnemyImageController imgControl = new EnemyImageController();

    /**
     * 
     * @param pannel
     */
    public EnemyView(final JPanel pannel) {
        this.pannel = pannel;
        this.pannel.addComponentListener(this);
    }

    /**
     * 
     * @param enemy
     */
    public void drawEnemy(final Enemy enemy) {
        final var g = pannel.getGraphics();
        g.drawImage(imgControl.spriteImage(0), 50, 50, pannel.getWidth()/20, pannel.getHeight()/20, null);
        g.dispose();
    }

    @Override
    public void componentResized(final ComponentEvent e) {
    }

    @Override
    public void componentMoved(ComponentEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void componentShown(ComponentEvent e) {
        // TODO Auto-generated method stub

    }



    @Override
    public void componentHidden(ComponentEvent e) {
        // TODO Auto-generated method stub
        
    }
}
