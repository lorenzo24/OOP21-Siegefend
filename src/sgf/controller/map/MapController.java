package sgf.controller.map;

import java.awt.image.BufferedImage;
import sgf.controller.Controller;
import sgf.model.Map;
import sgf.view.MapView;

/**
 * Controller for the class Map.
 */
public interface MapController extends Controller<MapView> {

    /**
     * Simple getter for field of type Map..
     * @return the game map intended as correspondences between GridPositions and Tiles.
     */
    Map getMap();

    /**
     * Simple getter for the image representing the whole map.
     * @return a BufferedImage representing the complete map.
     */
    BufferedImage getMapImage();
}
