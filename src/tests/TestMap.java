package tests;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import sgf.helpers.MapLoader;
import sgf.helpers.MapLoaderImpl;
import sgf.model.map.GridPosition;
import sgf.model.map.Map;

/**
 * Test class to check correct reading from file.
 */
public class TestMap {
    private static final int END_TILE_ROW = 19;
    private static final int END_TILE_COLUMN = 18;
    private static final int SIZE_MAP1 = 20;

    /**
     * Checks if a correct and known map (the level 1's map) sets correctly its start tile.
     */
    @Test
    public void checkCorrectStartTile() {
        final MapLoader mapLoader = new MapLoaderImpl(1);
        final Map map = mapLoader.getMap();
        assertEquals(map.getStartTile(), new GridPosition(10, 0));
    }

    /**
     * Checks if a correct and known map (the level 1's map) sets correctly its end tile.
     */
    @Test
    public void checkCorrectEndTile() {
        final MapLoader mapLoader = new MapLoaderImpl(1);
        final Map map = mapLoader.getMap();
        assertEquals(map.getEndTile(), new GridPosition(END_TILE_ROW, END_TILE_COLUMN));
    }

    /**
     * Checks if a correct and known map (the level 1's map) has the right size (number of cells per side).
     */
    @Test
    public void checkCorrectSize() {
        final MapLoader mapLoader = new MapLoaderImpl(1);
        assertEquals(mapLoader.getMap().getSize(), SIZE_MAP1);
    }

    /**
     * File loaded shows a number 9 and there is no cell that corresponds to this number.
     */
    @Test(expected = IllegalArgumentException.class)
    public void checkIncorrectReading() {
        new MapLoaderImpl(0);
    }

    /**
     * File loaded hasn't got the start tile (number 3).
     */
    @Test(expected = IllegalStateException.class)
    public void checkNoStartTile() {
        new MapLoaderImpl(-1);
    }

    /**
     * File loaded hasn't got the end tile (number 4).
     */
    @Test(expected = IllegalStateException.class)
    public void checkNoEndTile() {
        new MapLoaderImpl(-1);
    }
}
