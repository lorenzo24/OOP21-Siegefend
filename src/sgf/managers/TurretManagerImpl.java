package sgf.managers;

import sgf.model.game.Pausable;
import sgf.model.turret.Turret;

/**
 * 
 * 
 *
 */
public class TurretManagerImpl implements TurretManager, Pausable {

    private static final int UPDATE_DELAY = 20;
    private final Turret turret;
    private volatile boolean isThreadRunning = true;
    private Thread gameThread;

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
        if(gameThread == null) {
            gameThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (isThreadRunning) {
                        try {
                            if(getTurret() == null) {
                                findTarget();
                            }
                            pointToTarget();        // rotation
                            Thread.sleep(UPDATE_DELAY);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
        this.gameThread.start();
    }

    private void findTarget() {

    }

    private void pointToTarget() {

    }

    @Override
    public void stop() {
        this.isThreadRunning = false;
    }

    @Override
    public void resume() {
        this.isThreadRunning = true;
        this.startTurretThread();
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
