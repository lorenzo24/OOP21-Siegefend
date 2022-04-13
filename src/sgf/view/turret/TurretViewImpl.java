package sgf.view.turret;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.concurrent.Semaphore;

import javax.swing.SwingUtilities;

import sgf.controller.turret.TurretController;
import sgf.helpers.ImgTileSize;
import sgf.managers.TurretImageManager;
import sgf.model.map.Position;
import sgf.utilities.LockClass;
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
            gImage.setBackground(new Color(0, 0, 0, 0));
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
            final Position p = entry.getValue().getPosition();
            int origW = imgManager.getImage(entryID).getWidth(null);
            int origH = imgManager.getImage(entryID).getHeight(null);
            entry.getValue().setAngle(entry.getValue().getAngle() + 0.1); // test
            BufferedImage bimg = rotateImage(imgManager.getImage(entryID), entry.getValue().getAngle());
            double scaleX = (double) bimg.getWidth() / origW;
            double scaleY = (double) bimg.getHeight() / origH;
            System.out.println("Scala X:" + scaleX);
            System.out.println("Scala Y:" + scaleY);
            gImage.drawImage(bimg, (int) (p.getX() - (this.tileSize * (scaleX - 1) / 2)), (int) (p.getY() - (this.tileSize * (scaleY - 1) / 2)), (int) (this.tileSize * scaleX), (int) (this.tileSize * scaleY), null);
        }
        semaphore.release();
    }

    /**
     * 
     * @param img
     * @param rads
     * @return
     */
    public static BufferedImage rotateImage(final Image img, final double rads) {
        double sin = Math.abs(Math.sin(rads)), cos = Math.abs(Math.cos(rads));
        int w = img.getWidth(null);
        int h = img.getHeight(null);
        int newWidth = (int) Math.floor(w * cos + h * sin);
        int newHeight = (int) Math.floor(h * cos + w * sin);

        /* Debug Only
        System.out.println("new width: " + newWidth);
        System.out.println("new heigth: " + newHeight);
        System.out.println("orig width: " + w);
        System.out.println("orig heigth: " + h);
        */

        BufferedImage rotated = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = rotated.createGraphics();
        AffineTransform at = new AffineTransform();
        at.translate((newWidth - w) / 2, (newHeight - h) / 2);

        final int x = w / 2;
        final int y = h / 2;

        at.rotate(rads, x, y);
        g2d.setTransform(at);
        g2d.drawImage(img, 0, 0, null);
        g2d.dispose();

        return rotated;
    }

}
