package sgf.controller.game;

import sgf.managers.GameManager;
import sgf.view.game.AbstractGameView;
import sgf.view.game.GameView;

/**
 * This class refers to the implementation of the interface GameController.
 * The goal of this class is to manage the whole game thread.
 */
public class GameControllerImpl implements GameController {
    private AbstractGameView gameView;
    private volatile boolean threadRun = true; // Boolean that manages the thread loop.
    private boolean isControllerSet;
    private final GameManager gameManager;

    /**
     * 
     * @param gameManager
     */
    public GameControllerImpl(final GameManager gameManager) {
        this.gameManager = gameManager;
    }

    private void startGameThread() {
        final Thread gameThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (threadRun) {
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
    public void pause() {
        this.threadRun = false;
    }

    @Override
    public void resume() {
        this.threadRun = true;
    }

    @Override
    public void setView(final GameView view) {
        if (!isControllerSet) {
            if (view instanceof AbstractGameView) {
                this.isControllerSet = true;
                this.gameView = (AbstractGameView) view;
                this.startGameThread();
            } else {
                throw new IllegalArgumentException("Argument must be subclass of AbstractGameView");
            }
        }
    }

    @Override
    public void stop() {
        this.threadRun = false;
    }
}
