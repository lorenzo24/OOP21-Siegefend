package sgf.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import sgf.controller.PlayingController;
import sgf.model.Turret;
import sgf.utilities.SimpleTurretsImageLoader;

/**
 *
 */
public final class ShopItemView extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private static final String BUY = "Buy";
    private static final String CANCEL = "Cancel";

    private final ImageCanvas turretCanvas;
    private final JButton turretBuy;
    private final JLabel turretPrice;

    private boolean isSelected;

    private ShopItemView() {
        this.turretCanvas = null;
        this.turretPrice = new JLabel("Failed to load turret");
        this.turretBuy = new JButton("");
        this.turretBuy.setEnabled(false);
        isSelected = false;
    }

    private ShopItemView(final Turret t) {
        this.turretCanvas = new ImageCanvas(new SimpleTurretsImageLoader().getTurretImage("sample").orElse(null));
        this.turretBuy = new JButton(ShopItemView.BUY);
        this.turretPrice = new JLabel(Integer.toString(t.getPrice()), SwingConstants.CENTER);
        isSelected = false;
        // Setup panel
        final JPanel panel = new JPanel(new BorderLayout());
        final JPanel intPanel = new JPanel(new BorderLayout());
        panel.add(turretCanvas, BorderLayout.CENTER);
        intPanel.add(turretPrice, BorderLayout.CENTER);
        intPanel.add(turretBuy, BorderLayout.SOUTH);
        panel.add(intPanel, BorderLayout.SOUTH);
        this.add(panel);
    }

    /**
     * 
     * @param t The turret
     * @return a new {@code ShopTurretInfo} instance
     */
    public static ShopItemView from(final Turret t) {
        return new ShopItemView(t);
    }

    /**
     * Adds the {@link ActionListener} to the buy button of the view.
     * @param l the listener to add
     */
    public void addActionListener(final ActionListener l) {
        this.turretBuy.addActionListener(l);
    }

    /**
     * Changes the size of the image canvas.
     * @param d the new size
     */
    public void setCanvasSize(final Dimension d) {
        final Dimension cloned = new Dimension(d);
        this.turretCanvas.setSize(cloned);
        this.turretCanvas.setPreferredSize(cloned);
    }

    /**
     * 
     * @return a boolean
     */
    public boolean isSelected() {
        return isSelected;
    }

    /**
     * 
     * @param isSelected
     */
    public void setSelected(final boolean isSelected) {
        this.isSelected = isSelected;
    }

    /**
     * 
     */
    public void setBuyMode() {
        if (isButtonEnabled()) {
            this.turretBuy.setText(ShopItemView.BUY);
        }
    }

    /**
     * 
     */
    public void setCancelMode() {
        if (isButtonEnabled()) {
            this.turretBuy.setText(ShopItemView.CANCEL);
        }
    }

    /**
     * 
     * @return a boolean
     */
    public boolean isButtonEnabled() {
        return this.turretBuy.isEnabled();
    }

    /**
     * 
     * @param b
     */
    public void setButtonEnabled(final boolean b) {
        this.turretBuy.setEnabled(b);
    }
}
