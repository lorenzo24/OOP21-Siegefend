package sgf.view.menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.stream.Stream;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import sgf.controller.menu.MenuController;
import sgf.helpers.LevelLoader;

/**
 *
 */
public class MenuViewImpl extends AbstractMenuView {

    /**
     * 
     */
    private static final long serialVersionUID = 5001578289309695664L;
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

        /**
         * 
         */
        private static final long serialVersionUID = -8068410130600493746L;

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
        private boolean isUsernameSet;

        LevelPicker() {
            this.isUsernameSet = false;
            final JPanel playerPanel = new JPanel(new GridLayout(1, 3, 3, 3));
            final JPanel levelsListPanel = new JPanel(new GridLayout(levelLoader.getLevelsNumber() + 1, 1, 3, 3));
            final Font levelFont = new Font(Font.SANS_SERIF, Font.PLAIN, 25);

            final JLabel usernameLabel = new JLabel("Insert username:");
            usernameLabel.setVerticalAlignment(SwingConstants.CENTER);
            usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
            usernameLabel.setFont(levelFont);
            usernameLabel.setForeground(Color.decode("#F7F9F9"));
            playerPanel.add(usernameLabel);

            final JTextField inputField = new JTextField();
            inputField.setFont(levelFont);
            playerPanel.add(inputField);

            final MenuButton setUsernameButton = new MenuButton("Set");
            setUsernameButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent e) {
                    if (!inputField.getText().isEmpty()) {
                        menuController.getPlayerController().getPlayer().setPlayerName(inputField.getText());
                        JOptionPane.showMessageDialog(null, "Username updated!", "Update", 1);
                        isUsernameSet = true;
                    } else {
                        JOptionPane.showMessageDialog(null, "No text detected, please write something.", "Update", 1);
                    }
                }
            });
            playerPanel.add(setUsernameButton);

            playerPanel.setBackground(Color.decode(BACKGROUND_COLOR));
            levelsListPanel.setBorder(BorderFactory.createEmptyBorder(200, 100, 200, 100));
            levelsListPanel.setBackground(Color.decode(BACKGROUND_COLOR));
            levelsListPanel.add(playerPanel);
            Stream.iterate(1, i -> i + 1)
            .limit(levelLoader.getLevelsNumber())
            .map(i -> {
                final JButton b = new MenuButton("Livello" + i);
                b.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(final ActionEvent e) {
                        if (!checkUsername()) {
                            JOptionPane.showMessageDialog(null, "No username provided, you will use the default one.", "Update", 1);
                        }
                        MenuViewImpl.this.beginGame(i);
                    }
                });
                return b;
            }).forEach(levelsListPanel::add);      // Without levelsListPanel -> .forEach(this::add);



            this.setBackground(Color.decode(BACKGROUND_COLOR));
            this.setLayout(new BorderLayout());
            //this.setLayout(new GridLayout(2, 1, 8, 100));
            //this.add(playerPanel, BorderLayout.CENTER);
            this.add(levelsListPanel, BorderLayout.CENTER);
        }

        private boolean checkUsername() {
            return this.isUsernameSet;
        }
    }

    private class Options extends JPanel {

        Options() {
            final JPanel optionsPanel = new JPanel(new GridLayout(2, 1, 3, 3));
            final JButton musicButton = new MenuButton("");
        }

    }

    private class LeaderboardMenu extends JPanel {
        /**
         * 
         */
        private static final long serialVersionUID = -569715702442061004L;

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
        table.setBackground(Color.decode(BACKGROUND_COLOR));                    // table background
        //table.setGridColor(Color.green);                                      // grid border
        table.setForeground(Color.WHITE);                                       // text color
        table.setEnabled(false);                                                // disable edit
        final JScrollPane sp = new JScrollPane(table);
        sp.getViewport().setBackground(Color.decode(BACKGROUND_COLOR));         // sp background
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
