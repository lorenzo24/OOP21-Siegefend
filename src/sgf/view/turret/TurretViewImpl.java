package sgf.view.turret;

import sgf.controller.turret.TurretController;

/**
 * 
 * 
 *
 */
public class TurretViewImpl extends AbstractTurretView {


    private boolean isControllerSet;
    private TurretController turretController;
    private boolean ready;

    /**
     * 
     */
    public TurretViewImpl() {
        this.setVisible(false);
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

}
