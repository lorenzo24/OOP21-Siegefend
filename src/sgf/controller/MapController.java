package sgf.controller;

import sgf.view.ScreenGame;

/**
 * 
 * @author fabio
 *
 */
public class MapController {
    private Thread gameThread;
    final ScreenGame screen;
    
    public MapController() {
        screen = new ScreenGame();
        this.startGameThread();
    }

    private void startGameThread() {
        gameThread = new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    try {
                        screen.getMapCreator().paintComponentProva();
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.print(1000/50+"fps \n");
                }
            }
        });
        gameThread.start();
    }
}
