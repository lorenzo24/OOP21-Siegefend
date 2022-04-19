package sgf.managers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;
import java.util.Optional;
import javax.swing.Timer;

import sgf.controller.enemy.EnemyController;
import sgf.controller.turret.TurretController;
import sgf.model.enemies.Enemy;
import sgf.model.game.Stoppable;
import sgf.model.map.Position;
import sgf.model.turret.Turret;
import sgf.utilities.LockClass;
import sgf.utilities.Pair;
import sgf.utilities.ThreadObserver;

/**
 * 
 * 
 *
 */
public class TurretManagerImpl implements TurretManager, Stoppable {

    private static final int UPDATE_DELAY = 20;
    private final Turret turret;
    private volatile boolean isThreadRunning = true;
    private Thread gameThread;
    private final EnemyController enemyController;
    private final ActionListener fire;                          // Used for shooting.
    private final TurretController turretController;
    private final Timer bulletTimer;
    private final GameManager gameManager;

    /**
     * 
     * @param turret
     * @param turretController
     * @param enemyController
     */
    public TurretManagerImpl(final Turret turret, final TurretController turretController, final EnemyController enemyController, final GameManager gameManager) {
        this.gameManager = gameManager;
        this.turret = turret;
        this.enemyController = enemyController;
        ThreadObserver.register(this);
        this.turretController = turretController;
        this.fire = new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                if (turret.getTarget() != null) {
                    turretController.bulletCreated(getTurret().createBullet());
                }
            }
        };
        this.bulletTimer = new Timer((int) (1000 / getTurret().getFireRate()), fire);
        this.startTurretThread();
    }

    @Override
    public Turret getTurret() {
        return this.turret;
    }

    /**
     * Starts the turret thread.
     */
    private void startTurretThread() {
        if (gameThread == null) {
            gameThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (isThreadRunning) {
                        try {
                            final Optional<Enemy> target = getTurret().getTarget();
                            if (target.isEmpty() || target.get().getHP() <= 0) {        // Checks if there is a target and if there is one, it checks its HP.
                                bulletTimer.stop();
                                findTarget();
                            } else {
                                if (getTurret().getPosition().distanceTo(target.get().getPosition()) <= getTurret().getRange()) {       // Checks if the target is inside the turret's range.
                                    pointToTarget(target.get().getPosition());        // rotation
                                    if (!bulletTimer.isRunning()) {
                                        bulletTimer.start();
                                    }
                                } else {
                                    getTurret().setTarget(null);
                                }
                            }
                            Thread.sleep(UPDATE_DELAY);
                        } catch (InterruptedException e) {
                        }
                    }
                }
            });
        }
        this.gameThread.start();
    }

    /**
     * Searches the closest enemy to the turret and sets it as a target.
     */
    private void findTarget() {
        LockClass.getEnemySemaphore().acquireUninterruptibly();
        final Position currentPosition = this.getTurret().getPosition();
        final var closest = this.enemyController.getManagerList().stream()
                                             .filter(e -> e.getEnemy().getHP() > 0 && turret.getPosition().distanceTo(e.getEnemy().getPosition()) <= this.turret.getRange()) // Ignores enemies with HP lower or equal to 0
                                             .map(e -> Pair.from(e, e.getEnemy().getSteps()))
                                             .min(new Comparator<Pair<EnemyManager, Double>>() {
                                                 public int compare(final Pair<EnemyManager, Double> p1, final Pair<EnemyManager, Double> p2) {
                                                     return Double.compare(p2.getY(), p1.getY());
                                                 }
                                             });
        LockClass.getEnemySemaphore().release();
        if (closest.isPresent()) {
            this.turret.setTarget(closest.get().getX().getEnemy());
        } else {
            this.turret.setTarget(null);
            //System.out.println("No target found");
        }
    }

    private void pointToTarget(final Position targetPosition) {
        this.turret.setAngle(this.turret.getPosition().getAngle(targetPosition));
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

    @Override
    public void stop() {
        this.isThreadRunning = false;
        this.gameThread.interrupt();
    }

}
