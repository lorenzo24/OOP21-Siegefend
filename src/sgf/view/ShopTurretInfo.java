package sgf.view;

import javax.swing.JPanel;

import sgf.model.Turret;

public class ShopTurretInfo extends JPanel {

    private Turret turret;

    private ShopTurretInfo(final Turret t) {
        this.turret = t;
    }

    static ShopTurretInfo from(final Turret t) {
        return new ShopTurretInfo();
    }
}
