package sgf.controller;

import sgf.view.ScreenGame;

/**
 * This class is responsible for the map's multithreading management.
 *
 */
public class MapController {
    private static final int SLEEP_TIME = 50;
    private static final int A_SECOND_IN_MILLIS = 1000;
    private final ScreenGame screen;

    /**
     * Constructor that also start thread loop.
     */
    public MapController() {
        screen = new ScreenGame();
        this.startGameThread();
    }

    private void startGameThread() {
        final Thread gameThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        screen.getMapCreator().paintComponentProva();
                        Thread.sleep(SLEEP_TIME);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.print(A_SECOND_IN_MILLIS / SLEEP_TIME + " fps\n");
                }
            }
        });
        gameThread.start();
    }
}
