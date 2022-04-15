package sgf.view.menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import sgf.controller.enemy.EnemyController;
import sgf.controller.enemy.EnemyControllerImpl;
import sgf.controller.game.GameController;
import sgf.controller.game.GameControllerImpl;
import sgf.controller.game.MusicController;
import sgf.controller.game.MusicControllerImpl;
import sgf.controller.game.PlayerController;
import sgf.controller.game.PlayerControllerImpl;
import sgf.controller.game.PlayingController;
import sgf.controller.game.PlayingControllerImpl;
import sgf.controller.map.MapController;
import sgf.controller.map.MapControllerImpl;
import sgf.controller.menu.MenuController;
import sgf.controller.menu.MenuControllerImpl;
import sgf.controller.shop.ShopController;
import sgf.controller.shop.ShopControllerImpl;
import sgf.helpers.LevelLoader;
import sgf.helpers.LevelLoaderImpl;
import sgf.managers.GameManager;
import sgf.managers.LevelManager;
import sgf.managers.LevelManagerImpl;
import sgf.model.game.Classification;
import sgf.model.game.ClassificationImpl;
import sgf.model.game.Player;
import sgf.model.game.PlayerImpl;
import sgf.view.ScreenGame;
import sgf.view.enemy.AbstractEnemyView;
import sgf.view.enemy.EnemyViewImpl;
import sgf.view.game.AbstractGameView;
import sgf.view.game.AbstractPlayerView;
import sgf.view.game.AbstractPlayingView;
import sgf.view.game.GameViewImpl;
import sgf.view.game.PlayerViewImpl;
import sgf.view.game.PlayingViewImpl;
import sgf.view.map.AbstractMapView;
import sgf.view.map.MapViewImpl;
import sgf.view.shop.AbstractShopView;
import sgf.view.shop.ShopViewImpl;

/**
 * 
 * 
 *
 */
public class MenuViewImpl extends AbstractMenuView {

    private boolean isControllerSet;
    private boolean ready;
    private MenuController menuController;
    //private final JButton button;
    private static final String backgroundColor = "#293132", buttonColor = "#00A676", textColor = "#F7F9F9", hoverColor = "#E0D0C1"; // 293132, 00A676, F7F9F9, (E0D0C1, A76D60). https://coolors.co/293132-00a676-f7f9f9-e0d0c1-a76d60
    private static final Font titleFont = new Font(Font.SANS_SERIF, Font.PLAIN, 200);
    private static final Font textFont = new Font(Font.SANS_SERIF, Font.PLAIN, 50);
    JPanel menuPanel, levelPanel;
    private final LevelLoader levelLoader;

    @Override
    public void setController(final MenuController controller) {
        if (!isControllerSet) {
            this.isControllerSet = true;
            this.menuController = controller;
            //this.button.addActionListener(e -> {
            //    final AbstractPlayingView view = this.enemyController.loadPlayingView(1);
            //    System.out.println("testing...");
            //    MenuViewImpl.this.remove(button);
            //    MenuViewImpl.this.add(view, BorderLayout.CENTER);
            //    this.revalidate();
            //});
        }
    }

    public MenuViewImpl(final LevelLoader l) {
        super();
        //this.setLayout(new BorderLayout());
        //this.button = new JButton("Start Game");
        //this.add(button, BorderLayout.CENTER);
        
        //currentPanel = new StartMenu();
        //this.add(currentPanel);
        
        // OG
        // this.add(new StartMenu());
        this.levelLoader = l;
        levelPanel = new LevelPicker();
        this.setVisible(true);
        //this.add(menuPanel);
    }

    /**
     * 
     * 
     *
     */
    private class StartMenu extends JPanel{

        private StartMenu() {
            JPanel menuPanel;
            JPanel buttonsPanel;
            JButton startButton, optionsButton, leaderboardButton, creditsButton;
            JLabel titleLabel;

            titleLabel = new JLabel("Siegefend");
            startButton = new JButton("Start game");
            optionsButton = new JButton("Options");
            leaderboardButton = new JButton("Leaderboard");
            creditsButton = new JButton("Credits");

            // Changing the look of the components of the menu
            titleLabel.setVerticalAlignment(SwingConstants.CENTER);
            titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
            titleLabel.setFont(titleFont);
            titleLabel.setForeground(Color.decode(textColor));
            startButton.setVerticalAlignment(SwingConstants.CENTER);
            startButton.setFont(textFont);
            startButton.setForeground(Color.decode(textColor));
            startButton.setBackground(Color.decode(buttonColor));
            optionsButton.setVerticalAlignment(SwingConstants.CENTER);
            optionsButton.setFont(textFont);
            optionsButton.setForeground(Color.decode(textColor));
            optionsButton.setBackground(Color.decode(buttonColor));
            leaderboardButton.setVerticalAlignment(SwingConstants.CENTER);
            leaderboardButton.setFont(textFont);
            leaderboardButton.setForeground(Color.decode(textColor));
            leaderboardButton.setBackground(Color.decode(buttonColor));
            creditsButton.setVerticalAlignment(SwingConstants.CENTER);
            creditsButton.setFont(textFont);
            creditsButton.setForeground(Color.decode(textColor));
            creditsButton.setBackground(Color.decode(buttonColor));

            startButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent e) {
                    showLevelPicker();
                }
            });

            /*
            optionsButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    changeOptions();
                }
            });
            */

            /*
            leaderboardButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    showLeaderboard();
                }
            });
            */

            creditsButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    showCredits();
                }
            });

            buttonsPanel = new JPanel(new GridLayout(4, 1, 3, 3)); // 4-1-8-8
            buttonsPanel.add(startButton);
            buttonsPanel.add(optionsButton);
            buttonsPanel.add(leaderboardButton);
            buttonsPanel.add(creditsButton);
            buttonsPanel.setBorder(BorderFactory.createEmptyBorder(50, 25, 50, 25));
            buttonsPanel.setBackground(Color.decode(backgroundColor));

            menuPanel = new JPanel(new GridLayout(2, 1, 8, 50));
            menuPanel.add(titleLabel);
            menuPanel.add(buttonsPanel);
            menuPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            menuPanel.setBackground(Color.decode(backgroundColor));
            this.setBackground(Color.decode(backgroundColor));
            this.setLayout(new BorderLayout());
            this.add(menuPanel, BorderLayout.CENTER);
            //this.add(titleLabel);
            //this.add(buttonsPanel);
            //this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        }
    }

    private class LevelPicker extends JPanel{
        LevelPicker() {
            /*
            Stream.iterate(1, i -> i + 1).limit(3).map(i -> Map.entry(i, new JButton("Livello" + i))).peek(e -> {
                JButton b = e.getValue();
                b.addActionListener(null);
                // add look
            })
            .map(Map.Entry::getValue).forEach(this::add);
            */
            Stream.iterate(1, i -> i + 1)
            .limit(levelLoader.getLevelsNumber())
            .map(i -> {
                final JButton b = new JButton("Livello" + i);
                b.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(final ActionEvent e) {
                        MenuViewImpl.this.beginGame(i);
                    }
                });
                return b;
            }).forEach(this::add);
        }
    }

    @Override
    public void showLevelPicker() {
        menuPanel.setVisible(false);
        this.setBackground(Color.decode(backgroundColor));
        this.add(levelPanel);
    }

    private void beginGame(final int level) {
        final GameManager gameManager = null;
        final Classification cl = new ClassificationImpl();

        final LevelLoader levelLoader = new LevelLoaderImpl();
        final LevelManager levelManager = new LevelManagerImpl(levelLoader.loadLevel(level));
        final MapController mapController = new MapControllerImpl(levelManager.getMap());


        final Player player = new PlayerImpl("DEFAULT_NAME");
        final PlayerController playerController = new PlayerControllerImpl(player);
        /*
         * At the start only the menu, settings and levels view will be created.
         * All these other views and controllers will be created when someone clicks on a level.
         */
        final AbstractMapView mapView = new MapViewImpl(mapController.getMap());
        final EnemyController enemyController = new EnemyControllerImpl(levelManager, playerController);
        final AbstractEnemyView enemyView = new EnemyViewImpl(mapController.getMap().getSize());
        final GameController gameController = new GameControllerImpl();
        final AbstractGameView gameView = new GameViewImpl(mapView, enemyView);
        final ShopController shopController = new ShopControllerImpl(gameManager);
        final AbstractShopView shopView = new ShopViewImpl(gameManager);
        final PlayingController playingController = new PlayingControllerImpl(gameManager, playerController);
        final AbstractPlayerView playerView = new PlayerViewImpl();
        final AbstractPlayingView playingView = new PlayingViewImpl(gameView, shopView, playerView);
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
        playerController.setView(playerView);
        playerView.setController(playerController);

        shopView.start();
        playerView.start();
        mapView.start();
        enemyView.start();
        gameView.start();
        playingView.start();

        this.remove(menuPanel);
        this.remove(levelPanel);
        this.add(playingView);
        this.revalidate();
        this.repaint();
    }

    @Override
    public void showCredits() {
        // TODO Auto-generated method stub
        JOptionPane.showMessageDialog(null, "Lorenzo Gessi\nFabio Notaro\nLuca Venturi\nAndrea Bedei\nGiacomo Leo Bertuccioli", "Credits", 1);
    }

    private void setup() {
        menuPanel = new StartMenu();
        levelPanel = new LevelPicker();

        this.setLayout(new BorderLayout());
        this.add(menuPanel, BorderLayout.CENTER);
        menuPanel.setVisible(true);
        //this.add(new JButton("test"));
    }

    @Override
    public void start() {
        if (this.isControllerSet) {
            this.setup();
            this.ready = true;
            this.setVisible(true);
        } else {
            throw new IllegalStateException("Cannot invoke start() if the controller has not been set.");
        }
    }

    @Override
    public void stop() {
        // TODO Auto-generated method stub

    }


}
