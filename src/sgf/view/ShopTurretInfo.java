package sgf.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import sgf.model.Turret;

/**
 *
 */
public final class ShopTurretInfo extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private static final String BUY = "Buy";
    private static final String CANCEL = "Cancel";

    private JLabel turretImg;
    private final JButton turretBuy;
    private final JLabel turretPrice;

    private boolean isSelected;

    private ShopTurretInfo() {
        this.turretImg = new JLabel();
        this.turretPrice = new JLabel("Failed to load turret");
        this.turretBuy = new JButton("");
        this.turretBuy.setEnabled(false);
        isSelected = false;
    }

    private ShopTurretInfo(final Turret t) {
        try {
            this.turretImg = new JLabel(new ImageIcon(ImageIO.read(new File("res/test/vuoto.png"))));
        } catch (IOException e) {
            this.turretImg = new JLabel("No Image Found");
        }
        this.turretBuy = new JButton(ShopTurretInfo.BUY);
        this.turretPrice = new JLabel(Integer.toString(t.getPrice()), SwingConstants.CENTER);
        isSelected = false;
        // Setup panel
        final JPanel panel = new JPanel(new BorderLayout());
        final JPanel intPanel = new JPanel(new BorderLayout());
        panel.add(turretImg, BorderLayout.CENTER);
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
    public static ShopTurretInfo from(final Turret t) {
        return new ShopTurretInfo(t);
    }

    /**
     * Adds the {@link ActionListener} to the buy button of the view.
     * @param l the listener to add
     */
    public void addActionListener(final ActionListener l) {
        this.turretBuy.addActionListener(l);
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
            this.turretBuy.setText(ShopTurretInfo.BUY);
        }
    }

    /**
     * 
     */
    public void setCancelMode() {
        if (isButtonEnabled()) {
            this.turretBuy.setText(ShopTurretInfo.CANCEL);
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
