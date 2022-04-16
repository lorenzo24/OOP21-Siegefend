package sgf.view.menu;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.SwingConstants;

/**
 * Class that extends JButton to change
 * the default look of buttons.
 */
public class MenuButton extends JButton {

    private static final long serialVersionUID = 2197380384531705367L;
    private static final String BUTTON_COLOR = "#00A676", TEXT_COLOR = "#F7F9F9"; // 293132, 00A676, F7F9F9, (E0D0C1, A76D60). https://coolors.co/293132-00a676-f7f9f9-e0d0c1-a76d60
    private static final Font TEXT_FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 50);

    /**
     * Creates a button with text.
     * @param text the text of the button
     */
    public MenuButton(final String text) {
        super(text);
        this.setVerticalAlignment(SwingConstants.CENTER);
        this.setFont(TEXT_FONT);
        this.setForeground(Color.decode(TEXT_COLOR));
        this.setBackground(Color.decode(BUTTON_COLOR));
    }
 
}
