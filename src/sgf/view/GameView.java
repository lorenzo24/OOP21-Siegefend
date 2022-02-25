package sgf.view;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;

import sgf.model.EnemyImpl;
import sgf.model.Map;
import sgf.model.Position;

/**
 * 
 * 
 */
public class GameView extends JPanel {

    final MapView mapPanel;
    final EnemyView enemyPanel;

    /**
     * 
     * @param map
     * @param size
     */
    public GameView(final Map map, final int size) {
        this.mapPanel = new MapView(map, size);
        this.enemyPanel = new EnemyView(size, map.getMatrixSize(), List.of(new EnemyImpl(0, new Position(50, 50), 100, 0, 0)));
        this.setLayout(new BorderLayout());
        this.add(mapPanel);
        this.mapPanel.setLayout(new BorderLayout());
        this.mapPanel.add(enemyPanel);
        this.enemyPanel.setOpaque(false);
    }

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        mapPanel.repaint();
        enemyPanel.repaint();
    }
}
