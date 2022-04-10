package sgf.view.game;

import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.JLabel;

import sgf.controller.game.PlayerController;

/**
 * 
 *
 */
public class PlayerViewImpl extends AbstractPlayerView {

    private boolean isControllerSet = false;
    private PlayerController playerController;
    private boolean ready = false;
    private JLabel labelHP;
    private JLabel labelMoney;
    private JLabel labelScore;

    /**
     * 
     */
    public PlayerViewImpl() {
        super();
        this.setVisible(false);
    }

    @Override
    public void setController(final PlayerController controller) {
        if (!isControllerSet) {
            this.playerController = controller;
            this.isControllerSet = true;
        }
    }

    @Override
    public void start() {
        if (isControllerSet) {
            this.setVisible(true);
            setup();
            this.ready = true;
        } else {
            throw new IllegalStateException("Cannot invoke start() if the controller has not been set.");
        }
    }

    @Override
    public void stop() {
        this.setVisible(false);
    }

    private void setup() {
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.labelHP = new JLabel();
        this.labelMoney = new JLabel();
        this.labelScore = new JLabel();
        this.update();
        this.add(labelHP);
        this.add(labelMoney);
        this.add(labelScore);
    }

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        ((FlowLayout) this.getLayout()).setHgap(this.getParent().getWidth() / 4);               //TODO: boh.
        this.revalidate();
    }

    @Override
    public void update() {
        this.labelHP.setText("HP: " + this.playerController.getPlayer().getCurrentHP() + "/" + this.playerController.getPlayer().getMaxHP());
        this.labelMoney.setText("Money: " + this.playerController.getPlayer().getMoney());
        this.labelScore.setText("Score: " + this.playerController.getPlayer().getScore());
    }
}
