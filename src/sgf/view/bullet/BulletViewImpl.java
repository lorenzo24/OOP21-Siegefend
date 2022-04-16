package sgf.view.bullet;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.concurrent.Semaphore;

import sgf.controller.bullet.BulletController;
import sgf.helpers.ImageLoader;
import sgf.helpers.ImgTileSize;
import sgf.managers.BulletImageManager;
import sgf.model.bullet.Bullet;
import sgf.model.map.Position;
import sgf.utilities.LockClass;

/**
 *
 */
public class BulletViewImpl extends AbstractBulletView {

    private BulletController bulletController;
    private boolean isControllerSet;
    private boolean ready;
    private final Semaphore semaphore;
    private final int tileSize;
    private final BufferedImage image;
    private final ImageLoader<Integer> imgManager;

    /**
     * Constructor for creating an instance of a {@code BulletViewImpl}.
     * @param matrixSize the size of map in tiles
     */
    public BulletViewImpl(final int matrixSize) {
        this.tileSize = ImgTileSize.getTileSize();
        this.image = new BufferedImage(matrixSize * this.tileSize, matrixSize * this.tileSize, BufferedImage.TYPE_INT_ARGB);
        this.semaphore = LockClass.getBulletSemaphore();
        this.imgManager = new BulletImageManager();
        this.setVisible(false);
    }

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        if (this.ready) {
            this.drawBullets((Graphics2D) this.image.getGraphics());
            g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), null);
        }
    }

    private void drawBullets(final Graphics2D gImage) {
        gImage.setBackground(new Color(0, 0, 0, 0));
        gImage.clearRect(0, 0, this.image.getWidth(), this.image.getHeight());  // Clears the image area before repaint in another position.
        semaphore.acquireUninterruptibly();
        final var iterator = this.bulletController.getBulletsIterator();
        while (iterator.hasNext()) {
            final Bullet b = iterator.next();
            final Image img = this.imgManager.getImage(1);
            final Position pos = b.getPosition();
            gImage.drawImage(img, (int) pos.getX(), (int) pos.getY(), this.tileSize, this.tileSize, null);
        }
        semaphore.release();
    }

    @Override
    public void setController(final BulletController controller) {
        if (!isControllerSet) {
            this.isControllerSet = true;
            this.bulletController = controller;
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
    public void stopView() {
        this.ready = false;
        this.setVisible(false);
    }
}
