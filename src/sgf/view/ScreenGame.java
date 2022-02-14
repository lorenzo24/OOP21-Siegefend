package sgf.view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.*;
import java.awt.event.*;
/**
 * 
 * This class represents a simple screen game.
 *
 */
public class ScreenGame {
    private static final double WIDTH_PERC = 0.5;
    private static final double HEIGHT_PERC = 0.5;
    
    private final JFrame frame = new JFrame();
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    
    /**
     * Constructor.
     */
    public ScreenGame() {
        frame.setTitle("SIEGEFEND");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setSize((int) (screenSize.getWidth() * WIDTH_PERC), (int) (screenSize.getHeight() * HEIGHT_PERC));
        frame.setVisible(true);
        this.windowClosing();
    }
    
    private void windowClosing() {
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent e) {
                final int choise = JOptionPane.showConfirmDialog(frame, 
                        "Do you really want to quit?", "QUITTING", JOptionPane.YES_NO_OPTION);
                if (choise == JOptionPane.YES_OPTION) {
                    System.exit(0);     // Find an alternative to System.exit.
                }
            }
        });
    }
}
