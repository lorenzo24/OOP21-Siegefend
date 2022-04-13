package sgf.managers;

import sgf.model.turret.Turret;

/**
 * 
 * 
 *
 */
public class TurretManagerImpl implements TurretManager {

    private final Turret turret;

    /**
     * 
     * @param turret
     */
    public TurretManagerImpl(final Turret turret) {
        this.turret = turret;
    }

    @Override
    public Turret getTurret() {
        return this.turret;
    }

    @Override
    public int getCurrentUpgradeLevel() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getCurrentUpgradePrice() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getNextUpgradePrice() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Turret getNextUpgrade() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean canPurchaseUpgrade() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int sell() {
        // TODO Auto-generated method stub
        return 0;
    }

}
