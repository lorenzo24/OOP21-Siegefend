package sgf.controller.map;


import java.awt.image.BufferedImage;

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
     * kfd.
     * @return kjsdb.
     */
    BufferedImage getMapImage();

}
