package sgf.controller;

import sgf.model.GridPosition;
import sgf.model.Map;
import sgf.model.MapImpl;
import sgf.model.Tile;
import sgf.view.MapView;
import sgf.view.ScreenGame;

/**
 * This class is responsible for the map's multithreading management and also to show it.
 */
public class MapController {
    private final Map map;      // Model field.
    /**
     * Constructor that sets up the screen and also start thread loop.
     */
    public MapController() {
        this.obtainTileFromMouseClick();        // Method that return the correspondent Tile of a click on the screen.
    }


    /**
     * Method that calculates the tile clicked on the screen.
     * The body of the lambda expression converts the coordinate of mouse clicking keeping in
     * mind the screen size and how many tiles are there in the matrix's structure map.
     * TODO Giacomo, it will be useful for this method to return a Tile, not to be void, but lambda expression complicates this.
     */
    public void obtainTileFromMouseClick() {
        this.screen.getMapCreator().addMouseHandler((e) -> {
            final int gridColumn = this.convertCoordinate(e.getX(), screen.getMapCreator().getWidth());
            final int gridRow = this.convertCoordinate(e.getY(), screen.getMapCreator().getHeight());
            System.err.println(gridRow + ": " + gridColumn);
            System.out.println(this.map.getTileFromGridPosition(new GridPosition(gridColumn, gridRow)).getTileType());
        });
    }

    // Taken a value and the dimension in refers to, it returns an integer value that is the corresponding tile position in the dimension.
    private int convertCoordinate(final int x, final double dimension) {
        final double sizeOfASingleTile = dimension / screen.getMapCreator().getMatrixSize();
        return (int) (x / sizeOfASingleTile);
    }
}
