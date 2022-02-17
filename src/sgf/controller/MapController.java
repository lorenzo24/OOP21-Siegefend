package sgf.controller;

import sgf.model.Map;
import sgf.model.MapImpl;
import sgf.view.ScreenGame;

/**
 * This class is responsible for the map's multithreading management and also to show it.
 *
 */
public class MapController {
    private static final int SLEEP_TIME = 100;   // Modify this value in order to make the animation slower or faster.
    private final ScreenGame screen;
    private final Map map = new MapImpl();

    /**
     * Constructor that sets up the screen and also start thread loop.
     */
    public MapController() {
        screen = new ScreenGame();
        this.startMapThread();
    }

    private void startMapThread() {
        final Thread gameThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        // Taken the input matrix, show the correspondent map (grid of tiles), by
                        // first creating an array of tile from which load the images.
                        screen.getMapCreator().showGridImage(map.getMapComposition());
                        Thread.sleep(SLEEP_TIME);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        gameThread.start();
    }
    // TODO Show fps in old way and reload image every x seconds to avoid "sgranatura".
}
