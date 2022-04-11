package sgf.view.turret;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.concurrent.Semaphore;

import javax.swing.SwingUtilities;

import sgf.controller.turret.TurretController;
import sgf.helpers.ImgTileSize;
import sgf.managers.TurretImageManager;
import sgf.model.enemies.LockClass;
import sgf.model.map.Position;
import sgf.utilities.PositionConverter;

/**
 * 
 * 
 *
 */
public class TurretViewImpl extends AbstractTurretView {


    private boolean isControllerSet;
    private TurretController turretController;
    private boolean ready;
    private final BufferedImage image;
    private final int tileSize;
    private final Semaphore semaphore;
    private final TurretImageManager imgManager;
    private final PositionConverter posConverter;

    /**
     * 
     * @param matrixSize
     * @param semaphore
     */
    public TurretViewImpl(final int matrixSize, final Semaphore semaphore) {
        this.setVisible(false);
        this.tileSize = ImgTileSize.getTileSize();
        this.image = new BufferedImage(matrixSize * this.tileSize, matrixSize * this.tileSize, BufferedImage.TYPE_INT_ARGB);
        this.semaphore = semaphore;
        this.imgManager = new TurretImageManager();
        posConverter = new PositionConverter(this.tileSize);
    }

    @Override
    public void setController(final TurretController controller) {
        if (!isControllerSet) {
            this.isControllerSet = true;
            this.turretController = controller;
        }
    }

    @Override
    public void start() {
        if (this.isControllerSet) {
            this.ready = true;
            this.setVisible(true);
        } else {
            throw new IllegalStateException("Cannot invoke start() if the controller has not been set.");
        }
    }

    @Override
    public void stop() {
        this.ready = false;
        this.setVisible(false);
    }

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        if (this.ready) {
            final var gImage = (Graphics2D) this.image.getGraphics();
            gImage.clearRect(0, 0, this.image.getWidth(), this.image.getHeight());
            this.drawTurrets(gImage);
            g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), null);
        }
    }

    private void drawTurrets(final Graphics2D gImage) {
        semaphore.acquireUninterruptibly();
        final var iterator = this.turretController.getTurretsIterator();
        while (iterator.hasNext()) {
            final var entry = iterator.next();
            final int entryID = entry.getValue().getID();
            final Position p = posConverter.convertToPosition(entry.getKey());
            gImage.drawImage(imgManager.getImage(entryID), (int) p.getX(), (int) p.getY(), this.tileSize, this.tileSize, null);
        }
        semaphore.release();
    }

}
