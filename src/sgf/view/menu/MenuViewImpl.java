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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.table.JTableHeader;

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
import sgf.model.game.Leaderboard;
import sgf.model.game.Player;
import sgf.model.game.PlayerImpl;
import sgf.utilities.Pair;
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
    private static final String BACKGROUND_COLOR = "#293132";
    private static final Font TITLE_FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 200);
    private JPanel menuPanel, levelPanel, leaderboardPanel;
    private final LevelLoader levelLoader;

    @Override
    public void setController(final MenuController controller) {
        if (!isControllerSet) {
            this.isControllerSet = true;
            this.menuController = controller;
        }
    }

    /**
     * 
     * @param l
     */
    public MenuViewImpl(final LevelLoader l) {
        super();
        this.levelLoader = l;
        levelPanel = new LevelPicker();
        this.setVisible(true);
    }

    /**
     * 
     * 
     *
     */
    private final class StartMenu extends JPanel {

        private StartMenu() {
            JPanel buttonsPanel;
            MenuButton startButton, optionsButton, leaderboardButton, creditsButton;
            JLabel titleLabel;

            titleLabel = new JLabel("Siegefend");
            titleLabel.setVerticalAlignment(SwingConstants.CENTER);
            titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
            titleLabel.setFont(TITLE_FONT);
            titleLabel.setForeground(Color.decode("#F7F9F9"));

            startButton = new MenuButton("Start game");
            optionsButton = new MenuButton("Options");
            leaderboardButton = new MenuButton("Leaderboard");
            creditsButton = new MenuButton("Credits");

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
            leaderboardButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent e) {
                    showLeaderboard();
                }
            });


            creditsButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent e) {
                    showCredits();
                }
            });

            buttonsPanel = new JPanel(new GridLayout(4, 1, 3, 3));
            buttonsPanel.add(startButton);
            buttonsPanel.add(optionsButton);
            buttonsPanel.add(leaderboardButton);
            buttonsPanel.add(creditsButton);
            buttonsPanel.setBorder(BorderFactory.createEmptyBorder(50, 25, 50, 25));
            buttonsPanel.setBackground(Color.decode(BACKGROUND_COLOR));

            menuPanel = new JPanel(new GridLayout(2, 1, 8, 50));
            menuPanel.add(titleLabel);
            menuPanel.add(buttonsPanel);
            menuPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            menuPanel.setBackground(Color.decode(BACKGROUND_COLOR));
            this.setBackground(Color.decode(BACKGROUND_COLOR));
            this.setLayout(new BorderLayout());
            this.add(menuPanel, BorderLayout.CENTER);
        }
    }

    private class LevelPicker extends JPanel {
        private static final long serialVersionUID = -8864115627664618752L;

        LevelPicker() {
            // TODO: add name input (use DEFAULT when empty)
            final JPanel levelsListPanel = new JPanel(new GridLayout(levelLoader.getLevelsNumber(), 1, 3, 3));
            Stream.iterate(1, i -> i + 1)
            .limit(levelLoader.getLevelsNumber())
            .map(i -> {
                final JButton b = new MenuButton("Livello" + i);
                b.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(final ActionEvent e) {
                        MenuViewImpl.this.beginGame(i);
                    }
                });
                return b;
            }).forEach(levelsListPanel::add);      // Without levelsListPanel -> .forEach(this::add);

            levelsListPanel.setBorder(BorderFactory.createEmptyBorder(200, 100, 200, 100));
            levelsListPanel.setBackground(Color.decode(BACKGROUND_COLOR));

            this.setBackground(Color.decode(BACKGROUND_COLOR));
            this.setLayout(new BorderLayout());
            this.add(levelsListPanel, BorderLayout.CENTER);
        }
    }

    private class Options extends JPanel {

        Options(){
            final JPanel optionsPanel = new JPanel(new GridLayout(2, 1, 3, 3));
            final JButton musicButton = new MenuButton("");
        }

    }

    private class LeaderboardMenu extends JPanel {
        LeaderboardMenu() {
            this.setBackground(Color.decode(BACKGROUND_COLOR));
        }
    }

    @Override
    public void showLevelPicker() {
        menuPanel.setVisible(false);
        this.setBackground(Color.decode(BACKGROUND_COLOR));
        this.add(levelPanel);
    }

    private void beginGame(final int level) {
        this.levelPanel.setVisible(false);
        this.add(this.menuController.loadPlayingView(level));
        this.revalidate();
        this.repaint();
    }

    public void showLeaderboard() {
        leaderboardPanel = new LeaderboardMenu();
        menuPanel.setVisible(false);
        this.setBackground(Color.decode(BACKGROUND_COLOR));
        this.createTable();
        this.add(leaderboardPanel);
        this.revalidate();
        this.repaint();
    }

    private void createTable() {
        final String[] columnNames = { "DATE", "NAME", "SCORE" };
        final JTable table = new JTable(this.convertToMatrix(), columnNames);
        table.setBackground(Color.decode(BACKGROUND_COLOR));
        table.setGridColor(Color.WHITE);
        table.setForeground(Color.WHITE);
        final JScrollPane sp = new JScrollPane(table);
        this.leaderboardPanel.setLayout(new BorderLayout());
        this.leaderboardPanel.add(sp, BorderLayout.CENTER);
    }

    private Object[][] convertToMatrix() {
        final var recordMap = this.menuController.getLeaderboard().getMapScore();
        final String[][] matrix = new String[recordMap.size()][3];
        int count = 0; 
        for (final var elem : recordMap.entrySet()) {
            matrix[count][0] = elem.getKey();
            matrix[count][1] = elem.getValue().getX();
            matrix[count][2] = elem.getValue().getY().toString();
            count++;
        }
        return matrix;
    }

    @Override
    public void showCredits() {
        JOptionPane.showMessageDialog(null, "Lorenzo Gessi\nFabio Notaro\nLuca Venturi\nAndrea Bedei\nGiacomo Leo Bertuccioli", "Credits", 1);
    }

    private void setup() {
        menuPanel = new StartMenu();
        levelPanel = new LevelPicker();

        this.setLayout(new BorderLayout());
        this.add(menuPanel, BorderLayout.CENTER);
        menuPanel.setVisible(true);
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
    public void stopView() {
        // TODO Auto-generated method stub

    }


}
