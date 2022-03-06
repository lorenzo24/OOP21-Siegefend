package sgf.controller.map;

import sgf.model.ImgTileSize;
import sgf.model.Map;
import sgf.model.GridPosition;
import sgf.model.Position;
import sgf.utilities.MapLoader;
import sgf.utilities.MapLoaderImpl;
import sgf.view.MapView;

/**
 * This class is responsible for the map's multithreading management and also to show it.
 */
public class MapControllerImpl implements MapController {
    private final Map map;
    private final int cellSize;

    /**
     * Constructor that sets up the screen and also start thread loop.
     */
    public MapControllerImpl(final Map map) {
        this.map = map;
        this.cellSize = ImgTileSize.getTileSize();
    }

    @Override
    public void setView(final MapView view) {
        // TODO Auto-generated method stub

    }

    @Override
    public Map getMap() {
        return this.map;
    }

    @Override
    public Position convertToPosition(final GridPosition position) {
        return new Position(position.getColumn() * this.cellSize, position.getRow() * this.cellSize);
    }

    @Override
    public GridPosition convertToGridPosition(final Position position) {
        return new GridPosition((int) position.getY() / cellSize, (int) position.getX() / cellSize);
    }
}
