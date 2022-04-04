package sgf.view.shop;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import sgf.controller.shop.ShopController;
import sgf.model.turret.Turret;

/**
 *
 */
public class ShopViewImpl extends AbstractShopView {

    /**
     * 
     */
    private static final long serialVersionUID = 6030584324069338830L;
    private List<AbstractShopItemView> turretInfo;
    private AbstractShopItemView selected;
    private ShopController shopController;
    private boolean isControllerSet;
    private boolean ready;

    /**
     * Constructor for creating a new instance of {@code ShopViewImpl}.
     */
    public ShopViewImpl() {
        this.setLayout(new BorderLayout());
        this.setVisible(false);
    }

    @Override
    protected void update() {
        if (this.ready) {
            this.turretInfo.stream()
                           .forEach(t -> t.setCanvasSize(this.getItemImgSize()));       // Adapts the dimensions of each image in the shop.
            this.revalidate();
        }
    }

    /**
     * Creates all elements of the view.
     */
    private void setup() {
        this.turretInfo = this.shopController.getTurretList()
                .stream().map(this::createShopItemView)
                .collect(Collectors.toList());  // Creates a list of items for the view.
        final JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        this.turretInfo.forEach((t) -> panel.add(t));
        final JScrollPane scrollpane = new JScrollPane(panel);
        scrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        this.add(scrollpane, BorderLayout.CENTER);
    }

    private AbstractShopItemView createShopItemView(final Turret t) {
        final AbstractShopItemView item = ShopItemViewImpl.from(t);
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                if (selected == null || !selected.isSelected()) {  // The button can be pressed only if no other is pressed.
                    if (shopController.trySetSelectedTurret(item.getTurret())) {        // A turret can only be selected if the player has enough money.
                        item.setSelected(true);
                        item.cancelMode();
                        selected = item;
                    }
                    disableAllNotSelected();       // When the item's button is pressed, all other items become disabled.
                } else if (selected == item) {  // If the button is already pressed, cancellation of the purchase is performed instead.
                    shopController.deselectTurret();
                    turretDeselected();
                    item.setSelected(false);
                    item.buyMode();
                    enableAll();        // When the item is deselected, all the others become enabled.
                }
            }
        });
        return item;
    }

    @Override
    public void disableAllNotSelected() {
        if (this.ready) {
            this.turretInfo.stream()
                           .filter(i -> i != selected)
                           .forEach(i -> i.setButtonEnabled(false));
        }
    }

    @Override
    public void enableAll() {
        if (this.ready) {
            this.turretInfo.stream().forEach(i -> i.setButtonEnabled(true));
        }
    }

    @Override
    public void turretDeselected() {
        this.selected = null;
    }

    @Override
    public void setController(final ShopController controller) {
        if (!isControllerSet) {
            this.isControllerSet = true;
            this.shopController = controller;
        }
    }

    @Override
    public void start() {
        if (this.isControllerSet) {
            this.setup();
            this.ready = true;
            this.setVisible(true);
        } else {
            throw new IllegalStateException("Cannot invoke start() if the controller has not been set.");
        }
    }

    @Override
    public void stop() {
        if (this.ready) {
            this.ready = false;
            this.removeAll();
            this.setVisible(false);
        }
    }
}
