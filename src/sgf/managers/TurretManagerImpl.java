package sgf.managers;

import sgf.model.turret.Turret;

/**
 * 
 * 
 *
 */
public class TurretManagerImpl implements TurretManager {

    private static final int UPDATE_DELAY = 20;
    private final Turret turret;
    private volatile boolean isThreadRunning = true;

    /**
     * 
     * @param turret
     */
    public TurretManagerImpl(final Turret turret) {
        this.turret = turret;
        this.startTurretThread();
    }

    @Override
    public Turret getTurret() {
        return this.turret;
    }

    private void startTurretThread() {
        final Thread gameThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (isThreadRunning) {
                    try {
                        findTarget();
                        pointToTarget();        // rotation
                        Thread.sleep(UPDATE_DELAY);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        gameThread.start();
    }

    private ? findTarget() {

    }

    private ? pointToTarget() {

    }

    @Override
    public void stopThread() {
        this.isThreadRunning = false;
    }

    @Override
    public void resumeThread() {
        this.isThreadRunning = true;
    }

    @Override
    public int getCurrentUpgradeLevel() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getCurrentUpgradePrice() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getNextUpgradePrice() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Turret getNextUpgrade() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean canPurchaseUpgrade() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int sell() {
        throw new UnsupportedOperationException();
    }

}
