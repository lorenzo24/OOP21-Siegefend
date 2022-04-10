package sgf.view.menu;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import sgf.controller.menu.MenuController;
import sgf.view.game.AbstractPlayingView;

/**
 * 
 * 
 *
 */
public class MenuViewImpl extends AbstractMenuView {

    private boolean isControllerSet;
    private MenuController enemyController;
    //private final JButton button;
    private static final String backgroundColor = "#293132", buttonColor = "#00A676", textColor = "#F7F9F9", hoverColor = "#E0D0C1"; // 293132, 00A676, F7F9F9, (E0D0C1, A76D60). https://coolors.co/293132-00a676-f7f9f9-e0d0c1-a76d60
    private static final Font titleFont = new Font(Font.SANS_SERIF, Font.PLAIN, 100);
    private static final Font textFont = new Font(Font.SANS_SERIF, Font.PLAIN, 25);
    JPanel currentPanel;

    @Override
    public void setController(final MenuController controller) {
        if (!isControllerSet) {
            this.isControllerSet = true;
            this.enemyController = controller;
            //this.button.addActionListener(e -> {
            //    final AbstractPlayingView view = this.enemyController.loadPlayingView(1);
            //    System.out.println("testing...");
            //    MenuViewImpl.this.remove(button);
            //    MenuViewImpl.this.add(view, BorderLayout.CENTER);
            //    this.revalidate();
            //});
        }
    }

    public MenuViewImpl() {
        super();
        //this.setLayout(new BorderLayout());
        //this.button = new JButton("Start Game");
        //this.add(button, BorderLayout.CENTER);
        currentPanel = new StartMenu();
        this.add(currentPanel);
        
        // OG
        // this.add(new StartMenu());
    }

    /**
     * 
     * 
     *
     */
    private class StartMenu extends JPanel{

        private StartMenu() {
            JPanel menuPanel, buttonsPanel;
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
                public void actionPerformed(ActionEvent e) {
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
        }
    }

    private static class LevelPicker extends JPanel{
        public LevelPicker() {
            /*
            Stream.iterate(1, i -> i + 1).limit(3).map(i -> Map.entry(i, new JButton("Livello" + i))).peek(e -> {
                JButton b = e.getValue();
                b.addActionListener(null);
                // add look
            })
            .map(Map.Entry::getValue).forEach(this::add);
            */
            
            Stream.iterate(1, i -> i + 1).limit(3).map(i -> {
                JButton b = new JButton("Livello" + i);
                b.addActionListener(null);
                return b;
            });
        }
    }

    @Override
    public void showLevelPicker() {
        // TODO Auto-generated method stub
        //currentPanel = 
    }

    @Override
    public void showCredits() {
        // TODO Auto-generated method stub
        
    }


}
