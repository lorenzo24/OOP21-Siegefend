package sgf.view.menu;

import java.awt.event.ActionListener;

/**
 * Creates a button that takes the user to the home screen of the menu.
 */
public class GoBackButton extends MenuButton {
    private static final long serialVersionUID = 8800508343137052300L;

    /**
     * Creates a button with text.
     * @param text the text of the button
     * @param actionListener the action listener we want to add to the button
     */
    public GoBackButton(final String text, final ActionListener actionListener) {
        super(text);
        this.addActionListener(actionListener);
    }

}
