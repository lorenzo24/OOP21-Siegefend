package sgf.helpers;

import sgf.model.map.Map;

/**
 *  Interface for the map loading from file.
 */
public interface MapLoader {

    /**
     * Method that gives the map field.
     * @return the map field.
     */
    Map getMap();
}
