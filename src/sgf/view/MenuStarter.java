package sgf.view;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Initial menu.
 */
public class MenuStarter extends JFrame {

    /**
     * Serial id of GUI.
     */
    private static final long serialVersionUID = 7062076675925478162L;
    private static final double WIDTH_PERC = 0.2;
    private static final double HEIGHT_PERC = 0.1;
    private final JLabel display = new JLabel();


    /**
     * Menu constructor.
     */
    public MenuStarter() {
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize((int) (screenSize.getWidth() * WIDTH_PERC), (int) (screenSize.getHeight() * HEIGHT_PERC));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final JPanel panel = new JPanel();
        panel.add(display);
        final JButton stop = new JButton("stop");
        final JButton up = new JButton("up");
        final JButton down = new JButton("down");
        panel.add(up);
        panel.add(down);
        panel.add(stop);
        this.getContentPane().add(panel);
        this.setVisible(true);
    }
}
