package sgf.controller;

import sgf.model.GridPosition;
import sgf.model.Map;
import sgf.model.MapImpl;
import sgf.view.ScreenGame;

/**
 * This class is responsible for the map's multithreading management and also to show it.
 */
public class MapController {
    private static final int SLEEP_TIME = 10;   // Modify this value in order to make the animation slower or faster.
    private final ScreenGame screen;    // View field.
    private final Map map;      // Model field.
    private volatile boolean threadRun = true;

    /**
     * Constructor that sets up the screen and also start thread loop.
     */
    public MapController() {
        this.map = new MapImpl(1);      // 1 denotes to create the map for the first level.
        screen = new ScreenGame(this.map);      // The map is passed in order to create a MapCreator into ScreenGame.
        this.obtainTileFromMouseClick();        // Method that return the correspondent Tile of a click on the screen.
        this.startMapThread();
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

    private void startMapThread() {
        final Thread gameThread = new Thread(new Runnable() {

            @Override
            public void run() {
                screen.getMapCreator().createMapImage();
                int ups = 0;
                long lastTime = System.currentTimeMillis();
                while (threadRun) {
                    // Print how many update has been done in 1 second.
                    if (System.currentTimeMillis() - lastTime >= 1000) {
                        System.out.print("UPS:" + ups + "\n");
                        ups = 0;
                        lastTime = System.currentTimeMillis();
                    }
                    try {
                        // TODO Deleting this if problems related to hiding windows disappears. Maybe this if must be deleted? 
                        if (screen.getMapCreator().isUpdateNeeded()) {
                            screen.getMapCreator().showGridImage();
                        }
                        ups++;
                        Thread.sleep(SLEEP_TIME);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        gameThread.start();
    }

    // TODO pause and resume thread must change following game status.
    /**
     * Temporary stops the thread.
     */
    public void pauseMapThread() {
        this.threadRun = false;
    }

    /**
     * Resume the thread.
     */
    public void resumeMapThread() {
        this.threadRun = true;
    }
}
