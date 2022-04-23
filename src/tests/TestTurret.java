package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;
import sgf.helpers.TurretsLoader;
import sgf.helpers.TurretsLoaderImpl;
import sgf.model.map.Position;
import sgf.model.turret.Turret;

/**
 * Testing class for turrets.
 */
public class TestTurret {

    private static final int TURRET0_PRICE  = 120;
    private static final int TURRET1_PRICE  = 250;
    private static final int TURRET2_PRICE  = 400;
    private static final Position POSITION  = new Position(3, 3);
    private final TurretsLoader turretLoader = new TurretsLoaderImpl();
    private final Turret turret0 = this.turretLoader.getTurrets().get(0);
    private final Turret turret1 = this.turretLoader.getTurrets().get(1);
    private final Turret turret2 = this.turretLoader.getTurrets().get(2);

    /**
     * Checks if the price of the turrets is correct.
     */
    @Test
    public void priceTests() {
        assertEquals(turret0.getPrice(), TURRET0_PRICE);
        assertEquals(turret1.getPrice(), TURRET1_PRICE);
        assertEquals(turret2.getPrice(), TURRET2_PRICE);
    }

    /**
     * Checks if the initial position of the turret is correct.
     */
    @Test
    public void initialPositionTests() {
        assertNull(turret0.getPosition());
        assertNull(turret1.getPosition());
        assertNull(turret2.getPosition());
    }

    /**
     * Tests if the setPosition() method works correctly.
     */
    @Test
    public void setPositionTest() {
        turret0.setPosition(POSITION);
        assertEquals(turret0.getPosition(), POSITION);
    }

    /**
     * Tests if the getClone() method works correctly.
     */
    @Test
    public void getCloneTest() {
        final Turret clone = turret0.getClone();
        assertEquals(turret0, clone);
    }
}
