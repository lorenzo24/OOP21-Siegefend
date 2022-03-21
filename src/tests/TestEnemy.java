package tests;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.junit.Test;
import sgf.helpers.MapLoaderImpl;
import sgf.helpers.WavesLoader;
import sgf.helpers.WavesLoaderImpl;
import sgf.managers.LevelManager;
import sgf.managers.LevelManagerImpl;
import sgf.model.enemies.Enemy;
import sgf.model.enemies.EnemyFactory;
import sgf.model.enemies.EnemyFactoryImpl;
import sgf.model.level.LevelImpl;
import sgf.model.map.Map;
import sgf.model.map.Position;

/**
 * Class of test for enmey.
 */
public class TestEnemy {

    private static final double DELTA = 0.1;  // Delta for double number.
    private static final int PLANE_LIFE  = 50;
    private static final int TANK_LIFE  = 200;
    private static final int HELICOPTER_LIFE  = 100;
    private static final int POSITION  = 5;
    private final EnemyFactory factory = new  EnemyFactoryImpl();

    /**
     * Test of life of one enemy.
     */
    @Test
    public void lifeTests() {
        Enemy enemy = this.factory.createPlane(new Position(0, 0));
        assertTrue(check(enemy.getHP(), PLANE_LIFE));
        enemy = this.factory.createTank(new Position(0, 0));
        assertTrue(check(enemy.getHP(), TANK_LIFE));
        enemy = this.factory.createHelicopter(new Position(0, 0));
        assertTrue(check(enemy.getHP(), HELICOPTER_LIFE));
    }

    /**
     * Test for movement of one enemy.
     */
    @Test
    public void moveTests() {
       final Enemy enemy = this.factory.createTank(new Position(0, 0));
       enemy.move(POSITION, POSITION);
       assertTrue(check(enemy.getPosition().getX(), POSITION) && check(enemy.getPosition().getY(), POSITION));
    }

    /**
     * Test for movement of one enemy.
     */
    @SuppressWarnings("unused")
    @Test(expected = IllegalArgumentException.class)
    public void loadEnemiesTests() {
        final Map map = new MapLoaderImpl(1).getMap();
        final WavesLoader loader = new WavesLoaderImpl(map, 0);
    }

    /**
     * Test check number of waves.
     */
    @Test
    public void numberWaveTests() {
        final Map map = new MapLoaderImpl(1).getMap();
        final WavesLoader loader = new WavesLoaderImpl(map, 1);
        assertEquals(loader.getWaves().size(), 4);
    }

    /**
     * Test check the exception if the next wave there isn't.
     */
    @Test (expected = NoSuchElementException.class)
    public void nextWaveTests() {
        final Map map = new MapLoaderImpl(1).getMap();
        final WavesLoader loader = new WavesLoaderImpl(map, 1);
        final LevelManager level = new LevelManagerImpl(new LevelImpl(loader.getWaves(), map));
        level.nextWave();
        level.nextWave();
        level.nextWave();
        level.nextWave();
    }

    /**
     * Test check the next enemy.
     */
    @Test
    public void nextEnemyTests() {
        final Map map = new MapLoaderImpl(1).getMap();
        final WavesLoader loader = new WavesLoaderImpl(map, 1);
        final LevelManager level = new LevelManagerImpl(new LevelImpl(loader.getWaves(), map));
        assertNotEquals(level.getNextEnemy(), Optional.empty());
        assertNotEquals(level.getNextEnemy(), Optional.empty());
        assertNotEquals(level.getNextEnemy(), Optional.empty());
        assertEquals(level.getNextEnemy(), Optional.empty());
    }


    private boolean check(final double hp, final int life) {
        return hp > life - DELTA && hp < life + DELTA;
    }
}
