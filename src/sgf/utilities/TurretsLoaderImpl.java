package sgf.utilities;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import sgf.model.Enemy;
import sgf.model.Position;
import sgf.model.Turret;

/**
 *
 */
public class TurretsLoaderImpl implements TurretsLoader {

    @Override
    public List<Turret> getTurrets() {
     return Stream.iterate(0, i -> i + 1)
                  .limit(10)
                  .map(i -> new SimpleTurretImpl(i))
                  .collect(Collectors.toList());
    }

    /**
     * 
     */
    private static final class SimpleTurretImpl implements Turret, Cloneable {
        private final int v;

        SimpleTurretImpl(final int v) {
            this.v = v;
        }

        @Override
        public boolean isAttacking() {
            return false;
        }

        @Override
        public void idle() {
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
            return 0;
        }

        @Override
        public double getFireRate() {
            return 0;
        }

        @Override
        public void fireAt(final Position target) {
        }

        @Override
        public Optional<Enemy> findTarget() {
            return Optional.empty();
        }

        @Override
        public void attack() {
        }

        @Override
        public SimpleTurretImpl clone() throws CloneNotSupportedException {
            return (SimpleTurretImpl) super.clone();
        }
    }
}
