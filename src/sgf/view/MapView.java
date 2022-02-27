package sgf.view;

import sgf.controller.map.MapController;

/**
 * 
 */
public interface MapView extends View<MapController> {

    /**
     * Simple getter for the matrix size field.
     * @return an integer that is the matrix number of tiles per dimension.
     */
    int getMatrixSize();

    /**
     * Checks whether the window with the map has been updated and needs to be redrawn.
     * @return a boolean
     */
    boolean isUpdateNeeded();

}
