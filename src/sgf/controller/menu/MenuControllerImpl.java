package sgf.controller.menu;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import sgf.controller.enemy.EnemyController;
import sgf.controller.enemy.EnemyControllerImpl;
import sgf.controller.game.GameController;
import sgf.controller.game.GameControllerImpl;
import sgf.controller.game.PlayingController;
import sgf.controller.game.PlayingControllerImpl;
import sgf.controller.map.MapController;
import sgf.controller.map.MapControllerImpl;
import sgf.controller.shop.ShopController;
import sgf.controller.shop.ShopControllerImpl;
import sgf.helpers.MapLoaderImpl;
import sgf.helpers.WavesLoaderImpl;
import sgf.managers.GameManager;
import sgf.managers.LevelManager;
import sgf.managers.LevelManagerImpl;
import sgf.managers.PlayerManager;
import sgf.model.level.Level;
import sgf.model.level.LevelImpl;
import sgf.model.level.Wave;
import sgf.model.map.Map;
import sgf.view.enemy.AbstractEnemyView;
import sgf.view.enemy.EnemyViewImpl;
import sgf.view.game.AbstractGameView;
import sgf.view.game.AbstractPlayingView;
import sgf.view.game.GameViewImpl;
import sgf.view.game.PlayingViewImpl;
import sgf.view.map.AbstractMapView;
import sgf.view.map.MapViewImpl;
import sgf.view.menu.AbstractMenuView;
import sgf.view.menu.MenuView;
import sgf.view.menu.MenuViewImpl;
import sgf.view.shop.AbstractShopView;
import sgf.view.shop.ShopViewImpl;

/**
 * 
 * 
 *
 */
public class MenuControllerImpl implements MenuController {

    private boolean isControllerSet;
    private AbstractMenuView menuView;

    @Override
    public void setView(final MenuView view) {
        if(view instanceof AbstractMenuView) {
            if (!isControllerSet) {
                this.isControllerSet = true;
                this.menuView = (AbstractMenuView) view;
            }
        } else {
            throw new IllegalArgumentException();
        }
        
    }

    @Override
    public AbstractPlayingView loadPlayingView(final int levelNum) {
        final GameManager gameManager = null;
        final PlayerManager playerManager = null;
        final Map map = new MapLoaderImpl(1).getMap();  // 1 to be generalized.
        final MapController mapController = new MapControllerImpl(map);
        final List<Wave> waves = new WavesLoaderImpl(map, 1).getWaves();      // 1 to be generalized.
        final Level level = new LevelImpl(waves, map);
        final LevelManager levelManager = new LevelManagerImpl(level);

        /*
         * At the start only the menu, settings and levels view will be created.
         * All these other views and controllers will be created when someone clicks on a level.
         */
        final AbstractMapView mapView = new MapViewImpl(map);
        final EnemyController enemyController = new EnemyControllerImpl(levelManager, playerManager);
        final AbstractEnemyView enemyView = new EnemyViewImpl(map.getSize());
        final GameController gameController = new GameControllerImpl();
        final AbstractGameView gameView = new GameViewImpl(mapView, enemyView);
        final ShopController shopController = new ShopControllerImpl(gameManager);
        final AbstractShopView shopView = new ShopViewImpl(gameManager);
        final PlayingController playingController = new PlayingControllerImpl(gameManager, playerManager);
        final AbstractPlayingView playingView = new PlayingViewImpl(gameView, shopView);

        /**
         * Linking.
         */
        gameController.setView(gameView);
        gameView.setController(gameController);
        mapController.setView(mapView);
        mapView.setController(mapController);
        enemyController.setView(enemyView);
        enemyView.setController(enemyController);
        shopController.setView(shopView);
        shopView.setController(shopController);
        playingController.setView(playingView);
        playingView.setController(playingController);

        return playingView;
    }

    public void showCredits(){
        JOptionPane.showMessageDialog(null, "Lorenzo Gessi\nFabio Notaro\nLuca Venturi\nAndrea Bedei\nGiacomo Leo Bertuccioli", "Credits", 1);
    }
    
    public void levelPicker() {
        /*
        this.menuView.add(new JPanel() {
            JButton test = new JButton("AAAAAA");
            
            this.add(test);
        });
        */
        
        this.menuView.showLevelPicker();
    }

    @Override
    public void changeOptions() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void showLeaderboard() {
        // TODO Auto-generated method stub
        
    }
    
    

}