package sgf.view.shop;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import sgf.helpers.ImageLoader;
import sgf.helpers.SimpleTurretsImageLoader;
import sgf.helpers.TurretImagesLoader;
import sgf.managers.TurretImageManager;
import sgf.model.turret.Turret;

/**
 *
 */
public final class ShopItemViewImpl extends AbstractShopItemView {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private static final String BUY = "Buy";
    private static final String CANCEL = "Cancel";

    private ImageCanvas turretCanvas;
    private JButton turretBuy;
    private JLabel turretPrice;
    private final Turret turret;
    private boolean isSelected;

    /**
     * Creates a new instance of {@code ShopItemViewImpl}.
     * @param t a turret
     */
    public ShopItemViewImpl(final Turret t) {
        this.turret = t;
        this.createInnerElements();
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

    private void createInnerElements() {
        final ImageLoader<Integer> tLoader = new TurretImageManager();
        this.turretCanvas = new ImageCanvas(tLoader.getImage(this.turret.getID()));
        this.turretBuy = new JButton(ShopItemViewImpl.BUY);
        this.turretPrice = new JLabel(Integer.toString(this.turret.getPrice()), SwingConstants.CENTER);
    }

    @Override
    public Turret getTurret() {
        return this.turret;
    }

    @Override
    public void addActionListener(final ActionListener l) {
        this.turretBuy.addActionListener(l);
    }

    @Override
    public void setCanvasSize(final Dimension d) {
        final Dimension cloned = new Dimension(d);
        this.turretCanvas.setSize(cloned);
        this.turretCanvas.setPreferredSize(cloned);
    }

    @Override
    public boolean isSelected() {
        return isSelected;
    }

    @Override
    public void setSelected(final boolean isSelected) {
        this.isSelected = isSelected;
    }

    @Override
    public void buyMode() {
        if (isButtonEnabled()) {
            this.turretBuy.setText(ShopItemViewImpl.BUY);
        }
    }

    @Override
    public void cancelMode() {
        if (isButtonEnabled()) {
            this.turretBuy.setText(ShopItemViewImpl.CANCEL);
        }
    }

    @Override
    public boolean isButtonEnabled() {
        return this.turretBuy.isEnabled();
    }

    @Override
    public void setButtonEnabled(final boolean b) {
        this.turretBuy.setEnabled(b);
    }

    /**
     * Returns an instance of {@code ShopItemViewImpl}.
     * @param t a turret
     * @return an instance of {@code ShopItemViewImpl}
     */
    public static ShopItemViewImpl from(final Turret t) {
        return new ShopItemViewImpl(t);
    }
}
