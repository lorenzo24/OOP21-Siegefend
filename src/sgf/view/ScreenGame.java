package sgf.view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * This class represents a simple screen game (JFrame).
 */
public class ScreenGame extends JFrame {
    private static final long serialVersionUID = 8030357690780926273L;
    private static final double INITIAL_SIZE_PERC = 0.7;        // Initial frame size compared to the screen.
    private static final double MIN_SIZE_PERC = 0.9;    // Minimum resizing acceptable size compared to the screen.
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final AbstractPlayingView playingView;

    /**
     * Window constructor.
     * @param view Is the game panel.
     */
    public ScreenGame(final AbstractPlayingView view) {
        this.setTitle("SIEGEFEND");
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);      // Window closing will be managed with a JDialog.
        this.setFrameSize();    // Private method that sets up minimum and initial window size.
        this.playingView = view;
        this.getContentPane().add(this.playingView);       // Add main game panel to this frame.
        this.windowClosing();   // Private method that manages the window closing by showing a confirm dialog.
        this.setVisible(true);
    }

    private void setFrameSize() {
        final double width = this.screenSize.getWidth();
        final double height = this.screenSize.getHeight();
        final Dimension minimumSize = new Dimension((int) (width * MIN_SIZE_PERC), (int) (height * MIN_SIZE_PERC));
        this.setMinimumSize(minimumSize);
        this.setSize((int) (this.screenSize.getWidth() * INITIAL_SIZE_PERC), (int) (this.screenSize.getHeight() * INITIAL_SIZE_PERC));
    }

    // Methods that shows a JDialog when the user tries to close the game.
    private void windowClosing() {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(final WindowEvent e) {
                final int choise = JOptionPane.showConfirmDialog(new JFrame(), 
                        "Do you really want to quit?", "QUITTING", JOptionPane.YES_NO_OPTION);
                if (choise == JOptionPane.YES_OPTION) {
                    System.exit(0);     // TODO find an alternative to avoid warning.
                }
            }
        });
    }
}
