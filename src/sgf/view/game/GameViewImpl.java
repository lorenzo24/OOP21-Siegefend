package sgf.view.game;

import java.awt.BorderLayout;
import java.awt.Graphics;

import sgf.controller.game.GameController;
import sgf.view.bullet.AbstractBulletView;
import sgf.view.enemy.AbstractEnemyView;
import sgf.view.map.AbstractMapView;
import sgf.view.turret.AbstractTurretView;

/**
 * This panel contains both the enemy and tile panels.
 */
public class GameViewImpl extends AbstractGameView {
    private static final long serialVersionUID = -5124611364267300243L;
    private GameController gameController;
    private final AbstractMapView mapPanel;             // Panel that contains map grid of tiles.
    private final AbstractEnemyView enemyPanel;         // Panel that contains enemies in movement.
    private final AbstractTurretView turretPanel;       // Panel that contains turrets.
    private final AbstractBulletView bulletPanel;       // Panel that contains bullets.
    // the same thing must be done for bullets
    private boolean isControllerSet;
    private boolean ready;

    /**
     * Constructor that initializes both enemy and map panels.
     * @param mapView the view of the map
     * @param enemyView the view of the enemies
     * @param turretView
     * @param bulletView
     */
    public GameViewImpl(final AbstractMapView mapView, final AbstractEnemyView enemyView, final AbstractTurretView turretView, final AbstractBulletView bulletView) { // TODO: add TurretView, BulletView.
        super();
        // this.mapPanel = new MapViewImpl(map);
        //this.enemyPanel = new EnemyViewImpl(size, map.getMapSize(), enemyList);
        this.mapPanel = mapView;
        this.enemyPanel = enemyView;
        this.turretPanel = turretView;
        this.bulletPanel = bulletView;
        this.setVisible(false);
    }

    @Override
    public void update() {
        this.repaint();
    }

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        if (this.ready) {
            mapPanel.repaint();                         //TODO: might be useless.
            enemyPanel.repaint();
            turretPanel.repaint();
            bulletPanel.repaint();
        }
    }

    @Override
    public void setController(final GameController controller) {
        if (!isControllerSet) {
            this.isControllerSet = true;
            this.gameController = controller;
        }
    }

    @Override
    public void start() {
        if (this.isControllerSet) {
            this.setLayout(new BorderLayout());
            this.add(mapPanel);
            this.mapPanel.setOpaque(false);
            this.mapPanel.setLayout(new BorderLayout());
            this.mapPanel.add(enemyPanel);
            this.enemyPanel.setOpaque(false);       // The enemy's empty panel is set to be transparent so it doesn't cover the map's panel.
            this.enemyPanel.setLayout(new BorderLayout());
            this.enemyPanel.add(turretPanel);
            this.turretPanel.setOpaque(false);
            this.turretPanel.setLayout(new BorderLayout());
            this.bulletPanel.setOpaque(false);
            this.turretPanel.add(bulletPanel);
            this.ready = true;
            this.setVisible(true);
        } else {
            throw new IllegalStateException("Cannot invoke start() if the contorller has not been set.");
        }
    }

    @Override
    public void stop() {
        this.ready = false;
        this.setVisible(false);
    }
}
