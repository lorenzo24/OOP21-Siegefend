package sgf.view.menu;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import sgf.controller.menu.MenuController;
import sgf.view.game.AbstractPlayingView;

public class MenuViewImpl extends AbstractMenuView {

    private boolean isControllerSet;
    private MenuController enemyController;
    private final JButton button;

    @Override
    public void setController(final MenuController controller) {
        if (!isControllerSet) {
            this.isControllerSet = true;
            this.enemyController = controller;
            this.button.addActionListener(e -> {
                final AbstractPlayingView view = this.enemyController.loadPlayingView(1);
                System.out.println("testing...");
                MenuViewImpl.this.remove(button);
                MenuViewImpl.this.add(view, BorderLayout.CENTER);
                this.revalidate();
            });
        }
    }

    public MenuViewImpl() {
        super();
        this.setLayout(new BorderLayout());
        this.button = new JButton("Start Game");
        this.add(button, BorderLayout.CENTER);
    }
    
}
