package sgf.controller.map;

import sgf.model.GridPosition;
import sgf.model.Map;
import sgf.model.Position;
import sgf.utilities.MapLoader;
import sgf.utilities.MapLoaderImpl;
import sgf.view.MapView;

/**
 * This class is responsible for the map's multithreading management and also to show it.
 */
public class MapControllerImpl implements MapController {
    private final MapLoader mapLoader;
    private final Map map;
    private final int cellSize;

    /**
     * Constructor that sets up the screen and also start thread loop.
     * @param cellSize Is the dimension of a single tile image.
     */
    public MapControllerImpl(final int cellSize) {
        this.mapLoader = new MapLoaderImpl(1);
        this.map = mapLoader.getMap();
        this.cellSize = cellSize;
    }

    @Override
    public void setView(final MapView view) {
        // TODO Auto-generated method stub

    }

    @Override
    public Map getMap() {
        return this.map;
    }

    /**
     * Nested static class for position conversion.     TODO Insert the following methods into the static nested class.
     */
    public static class PositionConverter {
    }

    @Override
    public Position convertAGridPosition(final GridPosition position) {
        return new Position(position.getColumn() * this.cellSize, position.getRow() * this.cellSize);
    }
}
