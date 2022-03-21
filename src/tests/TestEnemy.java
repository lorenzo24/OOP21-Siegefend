package tests;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import org.junit.Test;
import sgf.model.enemies.Enemy;
import sgf.model.enemies.EnemyFactory;
import sgf.model.enemies.EnemyFactoryImpl;
import sgf.model.map.Position;

/**
 * Class of test for enmey.
 */
public class TestEnemy {

    private static final double DELTA = 0.1; 
    private static final int PLANE_LIFE  = 50;
    private static final int TANK_LIFE  = 200;
    private static final int HELICOPTER_LIFE  = 100;

    /**
     * Test of life of one enemy.
     */
    @Test
    public void lifeTests() {
        final EnemyFactory factory = new  EnemyFactoryImpl();
        Enemy enemy = factory.createPlane(new Position(0, 0));
        assertTrue(checkLife(enemy.getHP(), PLANE_LIFE));
        enemy = factory.createTank(new Position(0, 0));
        assertTrue(checkLife(enemy.getHP(), TANK_LIFE));
        enemy = factory.createHelicopter(new Position(0, 0));
        assertTrue(checkLife(enemy.getHP(), HELICOPTER_LIFE));
    }

    private boolean checkLife(final double hp, final int life) {
        return hp > life - DELTA && hp < life + DELTA;
    }
}
