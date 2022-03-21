package tests;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import sgf.model.enemies.Enemy;
import sgf.model.enemies.EnemyFactory;
import sgf.model.enemies.EnemyFactoryImpl;
import sgf.model.map.Position;

/**
 * 
 */
public class TestEnemy {

    private static final double DELTA = 0.1; 
    private static final double PLANE_LIFE  = 50;

    /**
     * 
     */
    @Test
    public void test1() {
        final EnemyFactory factory = new  EnemyFactoryImpl();
        final Enemy enemy = factory.createPlane(new Position(0, 0));
        assertTrue(enemy.getHP() > PLANE_LIFE - DELTA && enemy.getHP() < PLANE_LIFE + DELTA);
    }
}
