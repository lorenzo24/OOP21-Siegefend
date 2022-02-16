package sgf.view;

import java.awt.BorderLayout;
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

    private JLabel turretImg;
    private final JButton turretBuy;
    private final JLabel turretPrice;

    private ShopTurretInfo() {
        this.turretImg = new JLabel();
        this.turretPrice = new JLabel("Failed to load turret");
        this.turretBuy = new JButton("");
        this.turretBuy.setEnabled(false);
    }

    private ShopTurretInfo(final Turret t) {
        try {
            this.turretImg = new JLabel(new ImageIcon(ImageIO.read(new File("res/test/vuoto.png"))));
        } catch (IOException e) {
            this.turretImg = new JLabel("No Image Found");
        }
        this.turretBuy = new JButton("Buy");
        this.turretPrice = new JLabel(Integer.toString(t.getPrice()), SwingConstants.CENTER);
        /*
         * 
         */
        final JPanel panel = new JPanel(new BorderLayout());
        final JPanel intPanel = new JPanel(new BorderLayout());
        panel.add(turretImg, BorderLayout.CENTER);
        intPanel.add(turretPrice, BorderLayout.CENTER);
        intPanel.add(turretBuy, BorderLayout.SOUTH);
        panel.add(intPanel, BorderLayout.SOUTH);
        this.add(panel);
    }

    static ShopTurretInfo from(final Turret t) {
        return new ShopTurretInfo(t);
    }
}
