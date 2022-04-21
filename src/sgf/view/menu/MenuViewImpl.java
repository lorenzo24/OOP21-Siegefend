package sgf.view.menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Map;
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
import sgf.utilities.Pair;
import sgf.utilities.ThreadAndViewObservable;

/**
 * View of the menu.
 */
public class MenuViewImpl extends AbstractMenuView {
    private static final long serialVersionUID = 5001578289309695664L;
    private static final String BACKGROUND_COLOR = "#293132", TEXT_COLOR = "#F7F9F9";
    private final String CREDITS_TEXT = "The team that made Siegefend a reality:\n\n\n\n\n"
                                        + "Lorenzo Gessi\n\n\n"
                                        + "Andrea Bedei\n\n\n"
                                        + "Fabio Notaro\n\n\n"
                                        + "Luca Venturi\n\n\n"
                                        + "Giacomo Leo Bertuccioli\n\n\n\n\n"
                                        + "Thank you for playing Siegefend!";
    private static final Font TITLE_FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 100);
    private static final Font INMENU_FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 25);
    private static final int BORDER = 50;
    private static final int STANDARD_HGAP = 15;
    private static final int STANDARD_VGAP = 15;
    private static final int START_MENU_VGAP = 50;
    private static final String GO_BACK_BUTTON_TEXT = "Go Back";
    private final LevelLoader levelLoader;
    private final ScrollingText scrollingCredits;
    @SuppressWarnings("unused")
    private boolean ready;
    private boolean isControllerSet;
    private boolean isLeaderboardCreated;
    private MenuController menuController;
    private JPanel menuPanel, levelPanel, leaderboardPanel, optionsPanel, creditsPanel;

    /**
     * Creates a new instance of the class.
     * @param l the level we want to load
     */
    public MenuViewImpl(final LevelLoader l) {
        super();
        this.levelLoader = l;
        this.isLeaderboardCreated = false;
        this.scrollingCredits = new ScrollingText(CREDITS_TEXT);
        this.setVisible(true);
    }

    @Override
    public void setController(final MenuController controller) {
        if (!isControllerSet) {
            this.isControllerSet = true;
            this.menuController = controller;
        }
    }

    @Override
    public void start() {
        if (this.isControllerSet) {
            ThreadAndViewObservable.register(this);
            this.setup();
            this.ready = true;
            this.setVisible(true);
        } else {
            throw new IllegalStateException("Cannot invoke start() if the controller has not been set.");
        }
    }

    @Override
    public void stop() {
        this.ready = false;
        this.setVisible(false);
    }

    @Override
    public void showLevelPicker() {
        menuPanel.setVisible(false);
        this.add(levelPanel);
        this.remove(menuPanel);
    }

    @Override
    public void showOptions() {
        menuPanel.setVisible(false);
        this.add(optionsPanel);
        this.remove(menuPanel);
    }

    @Override
    public void showLeaderboard() {
        menuPanel.setVisible(false);
        this.createTable();
        this.add(leaderboardPanel);
        this.remove(menuPanel);
    }

    @Override
    public void showCredits() {
        menuPanel.setVisible(false);
        scrollingCredits.restart();  // restart the credit roll everytime we open the credits section
        this.creditsPanel.add(scrollingCredits, 0);
        this.add(creditsPanel);
        this.remove(menuPanel);
    }

    private void goBack() {
        this.removeAll();
        this.add(menuPanel);
        menuPanel.setVisible(true);
    }

    /**
     * Represents the home screen.
     */
    private final class StartMenu extends JPanel {
        private static final long serialVersionUID = 6719773034661190037L;
        private final JPanel buttonsPanel;
        private final MenuButton startButton, optionsButton, leaderboardButton, creditsButton;
        private final JLabel titleLabel;

        private StartMenu() {
            this.setLayout(new GridLayout(2, 1, STANDARD_HGAP, START_MENU_VGAP));
            this.setBorder(BorderFactory.createEmptyBorder(BORDER, BORDER / 2, BORDER, BORDER / 2));
            this.setBackground(Color.decode(BACKGROUND_COLOR));

            titleLabel = new JLabel("Siegefend");
            titleLabel.setVerticalAlignment(SwingConstants.CENTER);
            titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
            titleLabel.setFont(TITLE_FONT);
            titleLabel.setForeground(Color.decode(TEXT_COLOR));

            startButton = new MenuButton("Start game", new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent e) {
                    showLevelPicker();
                }
            });

            optionsButton = new MenuButton("Options", new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent e) {
                    showOptions();
                }
            });

            leaderboardButton = new MenuButton("Leaderboard", new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent e) {
                    showLeaderboard();
                }
            });

            creditsButton = new MenuButton("Credits", new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent e) {
                    showCredits();
                }
            });

            buttonsPanel = new JPanel(new GridLayout(4, 1, STANDARD_HGAP, STANDARD_VGAP));
            buttonsPanel.setBackground(Color.decode(BACKGROUND_COLOR));
            buttonsPanel.add(startButton);
            buttonsPanel.add(optionsButton);
            buttonsPanel.add(leaderboardButton);
            buttonsPanel.add(creditsButton);

            this.add(titleLabel);
            this.add(buttonsPanel);
        }
    }

    /**
     * Lets the user select the level he wants to play and input his username.
     */
    private final class LevelMenu extends JPanel {
        private static final long serialVersionUID = -8092993900595318140L;
        private boolean isUsernameSet;

        private LevelMenu() {
            this.isUsernameSet = false;
            this.setLayout(new GridLayout(levelLoader.getLevelsNumber() + 2, 1, STANDARD_HGAP, STANDARD_VGAP)); // +2 = playerPanel + goBackButton.
            this.setBorder(BorderFactory.createEmptyBorder(BORDER, BORDER / 2, BORDER, BORDER / 2));
            this.setBackground(Color.decode(BACKGROUND_COLOR));

            // Username panel
            final JPanel playerPanel = new JPanel(new GridLayout(1, 3, 3, 3));
            playerPanel.setBackground(Color.decode(BACKGROUND_COLOR));

            final JLabel usernameLabel = new JLabel("Insert username:");
            usernameLabel.setVerticalAlignment(SwingConstants.CENTER);
            usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
            usernameLabel.setFont(INMENU_FONT);
            usernameLabel.setForeground(Color.decode(TEXT_COLOR));
            playerPanel.add(usernameLabel);

            final JTextField inputField = new JTextField();
            inputField.setFont(INMENU_FONT);
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
            this.add(playerPanel);

            // Dynamically creates a button for each level we have
            Stream.iterate(1, i -> i + 1)
                    .limit(levelLoader.getLevelsNumber())
                    .map(i -> {
                        final JButton b = new MenuButton("Level " + i);
                        b.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(final ActionEvent e) {
                                if (!isUsernameSet()) {
                                    JOptionPane.showMessageDialog(null, "No username provided, you will use the default one.", "Update", 1);
                                }
                                MenuViewImpl.this.beginGame(i);
                            }
                        });
                        return b;
            }).forEach(this::add);

            // Creates a button to go back to the start menu
            this.add(new MenuButton(GO_BACK_BUTTON_TEXT, new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent e) {
                    goBack();
                }
            }));

        }

        private boolean isUsernameSet() {
            return this.isUsernameSet;
        }
    }

    /**
     * Contains all the options that the user can freely change.
     */
    private final class OptionsMenu extends JPanel {
        private static final long serialVersionUID = -5193173354110468925L;
        private static final String MUSIC_OFF_COLOR = "#EF476F", MUSIC_ON_COLOR = "#00A676";
        private final MenuButton musicButton;

        private OptionsMenu() {
            this.setLayout(new GridLayout(2, 1, STANDARD_HGAP, STANDARD_VGAP));
            this.setBorder(BorderFactory.createEmptyBorder(BORDER, BORDER / 2, BORDER, BORDER / 2));
            this.setBackground(Color.decode(BACKGROUND_COLOR));
            musicButton = new MenuButton("Music is currently ON");     // Music is enabled by default.

            musicButton.setBackground(Color.decode(MUSIC_ON_COLOR));

            musicButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent e) {
                    updateMusicButton();
                }
            });

            this.add(musicButton);
            this.add(new MenuButton(GO_BACK_BUTTON_TEXT, new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent e) {
                    goBack();
                }
            }));
        }

        private void updateMusicButton() {
            if (menuController.getMusicController().isMusicPlaying()) {
                menuController.getMusicController().musicOff();
                this.musicButton.setBackground(Color.decode(MUSIC_OFF_COLOR));
                this.musicButton.setText("Music is currently OFF");
            } else {
                menuController.getMusicController().musicOn();
                this.musicButton.setBackground(Color.decode(MUSIC_ON_COLOR));
                this.musicButton.setText("Music is currently ON");
            }
        }

    }

    /**
     * Contains the leaderboard.
     */
    private class LeaderboardMenu extends JPanel {
        private static final long serialVersionUID = -569715702442061004L;

        LeaderboardMenu() {
            this.setLayout(new GridLayout(2, 1, STANDARD_HGAP, STANDARD_VGAP));
            this.setBorder(BorderFactory.createEmptyBorder(BORDER, BORDER / 2, BORDER, BORDER / 2));
            this.setBackground(Color.decode(BACKGROUND_COLOR));

            this.add(new MenuButton(GO_BACK_BUTTON_TEXT, new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent e) {
                    goBack();
                }
            }), -1);
        }
    }

    /**
     * Contains the credits of the game.
     */
    private final class CreditsMenu extends JPanel {
        private static final long serialVersionUID = 6332858745374471601L;

        private CreditsMenu() {
            this.setLayout(new GridLayout(2, 1, STANDARD_HGAP, STANDARD_VGAP));
            this.setBorder(BorderFactory.createEmptyBorder(BORDER, BORDER / 2, BORDER, BORDER / 2));
            this.setBackground(Color.decode(BACKGROUND_COLOR));

            this.add(new MenuButton(GO_BACK_BUTTON_TEXT, new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent e) {
                    goBack();
                }
            }), -1);
        }
    }

    private void beginGame(final int level) {
        this.removeAll();
        this.add(this.menuController.loadPlayingView(level));
        this.revalidate();
        this.repaint();
    }

    private void createTable() {
        if (!this.isLeaderboardCreated) {
            this.isLeaderboardCreated = true;
            final String[] columnNames = { "DATE", "NAME", "SCORE" };
            final JTable table = new JTable(this.convertToMatrix(), columnNames);
            table.setBackground(Color.decode(BACKGROUND_COLOR));
            table.setForeground(Color.decode(TEXT_COLOR));
            table.setEnabled(false);                                                // disable edit
            final JScrollPane sp = new JScrollPane(table);
            sp.getViewport().setBackground(Color.decode(BACKGROUND_COLOR));         // sp background
            this.leaderboardPanel.add(sp, 0);
        }
    }

    private Object[][] convertToMatrix() {
        final var recordMap = this.menuController.getLeaderboard().getMapScore();
        final String[][] matrix = new String[recordMap.size()][3];
        final Iterator<Map.Entry<String, Pair<String, Integer>>> iterator = recordMap.entrySet().stream().sorted((x, y) -> y.getValue().getY().compareTo(x.getValue().getY())).iterator();
        int count = 0;
        while (iterator.hasNext()) {
            final var elem = iterator.next();
            matrix[count][0] = elem.getKey();
            matrix[count][1] = elem.getValue().getX();
            matrix[count][2] = elem.getValue().getY().toString();
            count++;
        }
        return matrix;
    }

    private void setup() {
        menuPanel = new StartMenu();
        levelPanel = new LevelMenu();
        optionsPanel = new OptionsMenu();
        leaderboardPanel = new LeaderboardMenu();
        creditsPanel = new CreditsMenu();

        this.setLayout(new BorderLayout());
        this.add(menuPanel, BorderLayout.CENTER);
        menuPanel.setVisible(true);
    }
}
