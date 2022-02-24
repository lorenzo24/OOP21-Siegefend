package sgf.controller;

import sgf.model.Map;
import sgf.model.MapImpl;
import sgf.view.GameView;
import sgf.view.ScreenGame;

/**
 * 
 *
 */
public class GameController {
    
    private final Map map = new MapImpl(1);      // 1 denotes to create the map for the first level.; 
    private final GameView gameView = new GameView(map, 500); // cambia numero.
    private final ScreenGame gameFrame;
    
    public GameController() {
        gameFrame = new ScreenGame(this.gameView);
        this.startGameThread();
    }
    
    private void startGameThread() {
        final Thread gameThread = new Thread(new Runnable() {
            @Override
            public void run() {
                int ups = 0;
                long lastTime = System.currentTimeMillis();
                while (true) {
                    // Print how many update has been done in 1 second.
                    if (System.currentTimeMillis() - lastTime >= 1000) {
                        System.out.print("UPS:" + ups + "\n");
                        ups = 0;
                        lastTime = System.currentTimeMillis();
                    }
                    ups++;
                    try {
                        gameView.repaint();
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        gameThread.start();
    }
}
