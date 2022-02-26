package sgf.view;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.util.List;
import javax.swing.JPanel;
import sgf.model.Enemy;
import sgf.model.Map;

/**
 * This panel contains both the enemy and tile panels.
 */
public class GameView extends JPanel {
    private static final long serialVersionUID = -5124611364267300243L;
    private final MapView mapPanel;     // Panel that contains map grid of tiles.
    private final EnemyView enemyPanel;  // Panel that contains enemies in movement.

    /**
     * Constructor that initializes both enemy and map panels.
     * @param map The map of the current level.
     * @param size The cell images size.
     * @param enemyList The enemys list that have to be draw. 
     */
    public GameView(final Map map, final int size, final List<Enemy> enemyList) {
        this.mapPanel = new MapView(map, size);
        this.enemyPanel = new EnemyView(size, map.getMapSize(), enemyList);
        this.setLayout(new BorderLayout());
        this.add(mapPanel);
        this.mapPanel.setLayout(new BorderLayout());
        this.mapPanel.add(enemyPanel);
        this.enemyPanel.setOpaque(false);       // The enemy's empty panel has no opacity so it cannot cover the map's panel.
    }

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        mapPanel.repaint();
        enemyPanel.repaint();
    }
}
