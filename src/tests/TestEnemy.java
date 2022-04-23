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
 * Testing class for enemy behavior.
 */
public class TestEnemy {

    private static final double DELTA = 0.1;  // Delta for double numbers.
    private static final int PLANE_LIFE  = 100;
    private static final int TANK_LIFE  = 225;
    private static final int HELICOPTER_LIFE  = 150;
    private static final int POSITION  = 5;
    private static final int NUMBER_WAVES  = 5;
    private static final int LEVEL_ID = 1;
    private final EnemyFactory factory = new  EnemyFactoryImpl();

    /**
     * Checks the correct initial health of enemies.
     */
    @Test
    public void lifeTests() {
        Enemy enemy = this.factory.createPlane(new Position(0, 0), 0);
        assertTrue(check(enemy.getHP(), PLANE_LIFE));
        enemy = this.factory.createTank(new Position(0, 0), 0);
        assertTrue(check(enemy.getHP(), TANK_LIFE));
        enemy = this.factory.createHelicopter(new Position(0, 0), 0);
        assertTrue(check(enemy.getHP(), HELICOPTER_LIFE));
    }

    /**
     * Checks a simple movement example.
     */
    @Test
    public void moveTests() {
       final Enemy enemy = this.factory.createTank(new Position(0, 0), 0);
       enemy.move(POSITION, POSITION);
       assertTrue(check(enemy.getPosition().getX(), POSITION) && check(enemy.getPosition().getY(), POSITION));
    }

    /**
     * File loaded has a number 9 on it and this number doesn't correspond to an enemy.
     */
    @Test(expected = IllegalArgumentException.class)
    public void loadEnemiesTests() {
        final Map map = new MapLoaderImpl(LEVEL_ID).getMap();
        new WavesLoaderImpl(map, 0);
    }

    /**
     * Checks the correct number of waves read from file.
     */
    @Test
    public void numberWaveTests() {
        final Map map = new MapLoaderImpl(LEVEL_ID).getMap();
        final WavesLoader loader = new WavesLoaderImpl(map, LEVEL_ID);
        assertEquals(loader.getWaves().size(), NUMBER_WAVES);
    }

    /**
     * Checks the behavior when we try to load a non existent wave.
     */
    @Test (expected = NoSuchElementException.class)
    public void nextWaveTests() {
        final Map map = new MapLoaderImpl(LEVEL_ID).getMap();
        final WavesLoader loader = new WavesLoaderImpl(map, LEVEL_ID);
        final LevelManager level = new LevelManagerImpl(new LevelImpl(loader.getWaves(), map, LEVEL_ID));
        level.nextWave();
        level.nextWave();
        level.nextWave();
        level.nextWave();
        level.nextWave();
    }

    /**
     * Checks the correct enemy getting.
     */
    @Test
    public void nextEnemyTests() {
        final Map map = new MapLoaderImpl(LEVEL_ID).getMap();
        final WavesLoader loader = new WavesLoaderImpl(map, LEVEL_ID);
        final LevelManager level = new LevelManagerImpl(new LevelImpl(loader.getWaves(), map, LEVEL_ID));
        assertNotEquals(level.getNextEnemy(), Optional.empty());
        assertEquals(level.getNextEnemy(), Optional.empty());
    }


    private boolean check(final double hp, final int life) {
        return hp > life - DELTA && hp < life + DELTA;
    }
}
