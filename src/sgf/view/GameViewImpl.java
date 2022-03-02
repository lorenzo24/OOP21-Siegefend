package sgf.view;

import java.awt.BorderLayout;
import java.awt.Graphics;

import sgf.controller.thread.GameController;

/**
 * This panel contains both the enemy and tile panels.
 */
public class GameViewImpl extends AbstractGameView {
    private static final long serialVersionUID = -5124611364267300243L;
    private GameController gameController;
    private final AbstractMapView mapPanel;     // Panel that contains map grid of tiles.
    private final AbstractEnemyView enemyPanel;  // Panel that contains enemies in movement.
    private boolean isControllerSet;

    /**
     * Constructor that initializes both enemy and map panels.
     * @param mapView the view of the map
     * @param enemyView the view of the enemies
     */
    public GameViewImpl(final AbstractMapView mapView, final AbstractEnemyView enemyView) { // TODO: add TurretView, BulletView.
        super();
        // this.mapPanel = new MapViewImpl(map);
        //this.enemyPanel = new EnemyViewImpl(size, map.getMapSize(), enemyList);
        this.mapPanel = mapView;
        this.enemyPanel = enemyView;
        this.setLayout(new BorderLayout());
        this.add(mapPanel);
        this.mapPanel.setLayout(new BorderLayout());
        this.mapPanel.add(enemyPanel);
        this.enemyPanel.setOpaque(false);       // The enemy's empty panel is set to be transparent so it doesn't cover the map's panel.
    }

    @Override
    public void update() {
        this.repaint();
    }

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        mapPanel.repaint();
        enemyPanel.repaint();
    }

    @Override
    public void setController(final GameController controller) {
        if (!isControllerSet) {
            this.isControllerSet = true;
            this.gameController = controller;
        }
    }
}
