package sgf.view;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.util.List;
import javax.swing.JPanel;

import sgf.controller.thread.GameController;
import sgf.model.Enemy;
import sgf.model.Map;

/**
 * This panel contains both the enemy and tile panels.
 */
public class GameViewImpl extends JPanel implements GameView {
    private static final long serialVersionUID = -5124611364267300243L;
    private final MapViewImpl mapPanel;     // Panel that contains map grid of tiles.
    private final EnemyViewImpl enemyPanel;  // Panel that contains enemies in movement.

    /**
     * Constructor that initializes both enemy and map panels.
     * @param map The map of the current level.
     * @param size The cell images size.
     * @param enemyList The enemies list that have to be draw. 
     */
    public GameViewImpl(final Map map, final int size, final List<Enemy> enemyList) {
        this.mapPanel = new MapViewImpl(map, size);
        this.enemyPanel = new EnemyViewImpl(size, map.getMapSize(), enemyList);
        this.setLayout(new BorderLayout());
        this.add(mapPanel);
        this.mapPanel.setLayout(new BorderLayout());
        this.mapPanel.add(enemyPanel);
        this.enemyPanel.setOpaque(false);       // The enemy's empty panel is set to be transparent so it doesn't cover the map's panel.
    }

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        mapPanel.repaint();
        enemyPanel.repaint();
    }

    @Override
    public void setController(GameController controller) {
        // TODO Auto-generated method stub
        
    }
}
