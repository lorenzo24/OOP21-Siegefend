package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import sgf.model.map.Tile;
import sgf.model.map.TileImpl;
import sgf.model.map.TileType;

/**
 * Test class to check tiles correct behaviors about containing turrets.
 */
public class TestTile {

    /**
     * Checks if grass tile can actually contain a turret.
     */
    @Test
    public void checkGrassContainsTurret() {
        final Tile tile = new TileImpl(TileType.GRASS);
        assertTrue(tile.canContainTurret());
    }

    /**
     * Ensure that a water tile cannot contain a turret.
     */
    @Test
    public void checkWaterNotContainsTurret() {
        final Tile tile = new TileImpl(TileType.WATER);
        assertFalse(tile.canContainTurret());
    }

    /**
     * Ensure that a path tile cannot contain a turret.
     */
    @Test
    public void checkPathNotContainsTurret() {
        final Tile tile = new TileImpl(TileType.PATH);
        assertFalse(tile.canContainTurret());
    }
}
