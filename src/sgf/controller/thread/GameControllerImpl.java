package sgf.controller.thread;

import sgf.controller.map.MapFileLoader;
import sgf.view.AbstractGameView;
import sgf.view.GameView;

/**
 * This class refers to the implementation of the interface GameController.
 * The goal of this class is to manage the whole game thread.
 */
public class GameControllerImpl implements GameController {
    private AbstractGameView gameView;
    private final MapFileLoader mapLoader;
    private volatile boolean threadRun = true; // Boolean that manages the thread loop.
    private boolean isControllerSet;


    /**
     * Constructor that starts the thread.
     */
    public GameControllerImpl() {
        this.mapLoader = new MapFileLoader(1);       // Loads the correct map according to the current level.
        this.startGameThread();
    }

   /*private Position initialPosition() {
        final Map map = mapLoader.getMap();
        final GridPosition gridPos = map.getStartTile();
        final double widthTile = this.gameView.getWidth() / map.getMapSize();
        final double heightTile = this.gameView.getHeight() / map.getMapSize();
        final double width = widthTile * gridPos.getColumn() - widthTile;
        final double height = heightTile * gridPos.getRow() - heightTile;
        return new Position(width, height);
    }*/

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
                        gameView.update();
                        Thread.sleep(100);
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

    @Override
    public void setView(final GameView view) {
        if (!isControllerSet) {
            if (view instanceof AbstractGameView) {
                this.isControllerSet = true;
                this.gameView = (AbstractGameView) view;
            } else {
                throw new IllegalArgumentException("Argument must be subclass of AbstractGameView");
            }
        }
    }
}
