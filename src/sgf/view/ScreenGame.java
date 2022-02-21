package sgf.view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import sgf.model.Map;
import java.awt.*;
import java.awt.event.*;

/**
 * This class represents a simple screen game.
 */
public class ScreenGame extends JFrame {
    private static final long serialVersionUID = 8030357690780926273L;
    private static final double SIZE_PERC = 0.5;
    private static final double MIN_SIZE_PERC = 0.3;
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final MapCreator mapCreator;

    /**
     * Window constructor.
     * @param map
     */
    public ScreenGame(final Map map) {
        this.setTitle("SIEGEFEND");
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.mapCreator = new MapCreator(map);
        this.setFrameSize();    // Private method that sets up minimum and initial window size.
        this.getContentPane().add(mapCreator);
        this.windowClosing();   // Private method that manage the window closing by showing a confirm dialog.
        this.setVisible(true);
    }

    /**
     * Getter for the field mapCreator.
     * @return mapCreator field.
     */
    public MapCreator getMapCreator() {
        return this.mapCreator;
    }

    private void setFrameSize() {
        final double width = this.screenSize.getWidth();
        final double height = this.screenSize.getHeight();
        final Dimension minimumSize = new Dimension((int) (width * MIN_SIZE_PERC), (int) (height * MIN_SIZE_PERC));
        this.setMinimumSize(minimumSize);
        this.setSize((int) (this.screenSize.getWidth() * SIZE_PERC), (int) (this.screenSize.getHeight() * SIZE_PERC));
    }

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
