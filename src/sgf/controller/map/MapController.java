package sgf.controller.map;


import sgf.controller.Controller;
import sgf.model.Map;
import sgf.model.GridPosition;
import sgf.model.Position;
import sgf.view.MapView;


/**
 *
 */
public interface MapController extends Controller<MapView> {
    /**
     * Simple getter for map field.
     * @return the map.
     */
    Map getMap();

    /**
     * Method to convert a GridPosition into a Position.
     * @param position Is the position expressed as GridPosition.
     * @return the equivalent position expressed as Position.
     */
    Position convertToPosition(GridPosition position);

    /**
     * Method to convert a Position into a GridPosition.
     * @param position Is the position expressed as Position.
     * @return the equivalent position expressed as GridPosition.
     */
    GridPosition convertToGridPosition(Position position);

}
