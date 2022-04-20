package sgf.view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import sgf.utilities.ThreadObserver;

/**
 * This class represents the screen game (JFrame).
 */
public class ScreenGame extends JFrame {
    private static final long serialVersionUID = 8030357690780926273L;
    private static final double INITIAL_SIZE_PERC = 0.9;        // Initial frame size compared to the screen.
    private static final int MIN_SCREEN_DIMENSION = 800;    // Minimum resizing acceptable size of screen.
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    /**
     * Window constructor.
     * @param view Is the game panel.
     */
    public ScreenGame(final JPanel view) {
        this.setTitle("SIEGEFEND");
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);      // Window closing will be managed with a JDialog.
        this.setFrameSize();    // Private method that sets up minimum and initial window size.
        this.getContentPane().add(view);       // Adds main game panel to this frame.
        this.windowClosing();   // Private method that manages the window closing by showing a confirm dialog.
        this.setVisible(true);
    }

    private void setFrameSize() {
        final double height = this.screenSize.getHeight();
        final Dimension minDimension = new Dimension(MIN_SCREEN_DIMENSION, MIN_SCREEN_DIMENSION); 
        this.setMinimumSize(minDimension);
        this.setSize((int) (height * INITIAL_SIZE_PERC), (int) (height * INITIAL_SIZE_PERC));
    }

    // Methods that shows a JDialog when the user tries to close the game.
    private void windowClosing() {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(final WindowEvent e) {
                final int choise = JOptionPane.showConfirmDialog(new JFrame(), 
                        "Do you really want to quit? \nYour score won't be saved into the leaderborad!", "QUITTING", JOptionPane.YES_NO_OPTION);
                if (choise == JOptionPane.YES_OPTION) {
                    ThreadObserver.stop();      // Just before closing all managers' threads and all views are stopped.
                    System.exit(0);
                }
            }
        });
    }
}
