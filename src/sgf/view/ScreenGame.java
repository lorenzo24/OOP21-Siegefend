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
    private static final double SIZE_PERC = 0.5;
    private static final double MIN_SIZE_PERC = 0.3;
    private final JFrame frame = new JFrame();
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    /**
     * Constructor.
     */
    public ScreenGame() {
        this.frame.setTitle("SIEGEFEND");
        this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setFrameSize();
        this.frame.getContentPane().add(new MapCreator());
        this.windowClosing();
        this.frame.setVisible(true);
    }

    private void setFrameSize() {
        final double width = this.screenSize.getWidth();
        final double height = this.screenSize.getHeight();
        final Dimension minimumSize = new Dimension((int) (width * MIN_SIZE_PERC), (int) (height * MIN_SIZE_PERC));
        this.frame.setMinimumSize(minimumSize);
        this.frame.setSize((int) (this.screenSize.getWidth() * SIZE_PERC), (int) (this.screenSize.getHeight() * SIZE_PERC));

    }

    private void windowClosing() {
        this.frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(final WindowEvent e) {
                final int choise = JOptionPane.showConfirmDialog(frame, 
                        "Do you really want to quit?", "QUITTING", JOptionPane.YES_NO_OPTION);
                if (choise == JOptionPane.YES_OPTION) {
                    frame.setVisible(false);
                    frame.dispose();
                }
            }
        });
    }
}
