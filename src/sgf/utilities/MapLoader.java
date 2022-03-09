package sgf.utilities;

import sgf.model.Map;

/**
 *  Interface that loads the map.
 */
public interface MapLoader {

    /**
     * Method that gives the map field.
     * @return the map field.
     */
    Map getMap();
}
