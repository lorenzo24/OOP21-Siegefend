package sgf.controller.map;

import sgf.view.MapView;

/**
 * This class is responsible for the map's multithreading management and also to show it.
 */
public class MapControllerImpl implements MapController {
    //private final Map map;      // Model field.
    /**
     * Constructor that sets up the screen and also start thread loop.
     */
    public MapControllerImpl() {
        //this.obtainTileFromMouseClick();        // Method that return the correspondent Tile of a click on the screen.
    }

    @Override
    public void setView(MapView view) {
        // TODO Auto-generated method stub
        
    }


    /**
     * Method that calculates the tile clicked on the screen.
     * The body of the lambda expression converts the coordinate of mouse clicking keeping in
     * mind the screen size and how many tiles are there in the matrix's structure map.
     * TODO Giacomo, it will be useful for this method to return a Tile, not to be void, but lambda expression complicates this.
     */
/*    public void obtainTileFromMouseClick() {
        this.screen.getMapCreator().addMouseHandler((e) -> {
            final int gridColumn = this.convertCoordinate(e.getX(), screen.getMapCreator().getWidth());
            final int gridRow = this.convertCoordinate(e.getY(), screen.getMapCreator().getHeight());
            System.err.println(gridRow + ": " + gridColumn);
            System.out.println(this.map.getTileFromGridPosition(new GridPosition(gridColumn, gridRow)).getTileType());
        });
    }*/

 
}
