package sgf.helpers;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import sgf.model.bullet.Bullet;
import sgf.model.enemies.Enemy;
import sgf.model.map.Position;
import sgf.model.turret.Turret;
import sgf.model.turret.TurretImpl;

/**
 *
 */
public class SimpleTurretsLoader implements TurretsLoader {

    @Override
    public Map<Integer, Turret> getTurrets() {
        final Map<Integer, Turret> m = new HashMap<>();
        Stream.iterate(0, i -> i + 1)
              .limit(10)
              .map(i -> new TurretImpl(i, null, 100, 600, 2, 1, 50))
              .forEach(t -> m.put(t.getID(), t));
        return m;
    }

    /**
     * 
     */
    private static final class SimpleTurretImpl implements Turret, Cloneable {
        private final int v;
        private final int id;

        SimpleTurretImpl(final int id, final int v) {
            this.id = id;
            this.v = v;
        }

        @Override
        public boolean isAttacking() {
            return false;
        }

        @Override
        public Optional<Enemy> getTarget() {
            return Optional.empty();
        }

        @Override
        public double getRange() {
            return 0;
        }

        @Override
        public int getPrice() {
            return v * 100;
        }

        @Override
        public Position getPosition() {
            return null;
        }

        @Override
        public int getID() {
            return this.id;
        }

        @Override
        public double getFireRate() {
            return 0;
        }

        @Override
        public SimpleTurretImpl clone() throws CloneNotSupportedException {
            return (SimpleTurretImpl) super.clone();
        }

        @Override
        public Turret getClone() {
            try {
                return this.clone();
            } catch (CloneNotSupportedException e) { }
            throw new UnsupportedOperationException("Class " + SimpleTurretsLoader.class.getName() + " does not support cloning.");
        }

        @Override
        public void setState(final boolean state) {
            // TODO Auto-generated method stub

        }

        @Override
        public Bullet createBullet() {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public double getAngle() {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public void setAngle(double angle) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void setPosition(Position p) {
            // TODO Auto-generated method stub
            
        }
    }
}
