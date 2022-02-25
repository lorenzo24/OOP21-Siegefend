package sgf.controller.thread;

import sgf.controller.map.MapFileLoader;
import sgf.view.GameView;
import sgf.view.ScreenGame;

/**
 * This class refers to the implementation of the interface GameController.
 * The goal of this class is to manage the whole game thread.
 */
public class GameControllerImpl implements GameController {
    private final MapFileLoader mapLoader = new MapFileLoader(1);       // Loads the correct map according to the current level.
    private final GameView gameView = new GameView(this.mapLoader.getMap(), 500); // cambia numero.
    private final ScreenGame gameFrame; // Window JFrame.
    private volatile boolean threadRun = true; // Boolean that manages the thread loop.

    /**
     * Constructor that starts the thread.
     */
    public GameControllerImpl() {
        gameFrame = new ScreenGame(this.gameView);      // The panle gameView is passed to the frame.
        this.startGameThread();
    }

    private void startGameThread() {
        final Thread gameThread = new Thread(new Runnable() {
            @Override
            public void run() {
                int ups = 0;    // Updates per second.
                long lastTime = System.currentTimeMillis();
                while (threadRun) {
                    // Print how many updates has been done in 1 second.
                    if (System.currentTimeMillis() - lastTime >= 1000) {
                        System.out.print("UPS:" + ups + "\n");
                        ups = 0;
                        lastTime = System.currentTimeMillis();
                    }
                    ups++;
                    try {
                        gameView.repaint();     // Thread core job. This is an automatic method that redraws the main panel content.
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        gameThread.start();
    }

    @Override
    public void stopThread() {
        this.threadRun = false;
    }

    @Override
    public void resumeThread() {
        this.threadRun = true;
    }
}
