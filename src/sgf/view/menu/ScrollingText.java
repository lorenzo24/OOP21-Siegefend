package sgf.view.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * 
 * 
 *
 */
public class ScrollingText extends JPanel implements ActionListener {

    private static final long serialVersionUID = -4619185403316178271L;
    private static final String BACKGROUND_COLOR = "#293132", TEXT_COLOR = "#F7F9F9";
    private static final Font TEXT_FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 50); // OG: 25
    private static final int TEXT_Y = 400;
    private final Timer creditTimer;
    private final String text;
    private int textY;

    public ScrollingText(final String text) {
        this.setBackground(Color.decode(BACKGROUND_COLOR));
        creditTimer = new Timer(5, this);
        textY = TEXT_Y;

        this.text = text;

        creditTimer.start();

    }

    public void paintComponent(final Graphics g) {
        super.paintComponent(g);

        final Graphics2D g2d = (Graphics2D)g;

        g2d.setFont(TEXT_FONT);
        g2d.setColor(Color.decode(TEXT_COLOR));
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        int y = textY;

        for (final String line : text.split("\n")) {
            final int stringLength = (int) g2d.getFontMetrics().getStringBounds(line, g2d).getWidth();
            final int x = getWidth() / 2 - stringLength / 2;
            g2d.drawString(line, x, y += 28);
        }
    }

    @Override
    public void actionPerformed(final ActionEvent e) {

        textY--;
        if (textY < -660) {
            restart();
        }

        repaint();
    }

    private void restart() {
        textY = TEXT_Y;
    }

}
