package sgf.view.shop;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import sgf.controller.shop.ShopController;
import sgf.managers.GameManager;
import sgf.model.turret.Turret;

/**
 *
 */
public class ShopViewImpl extends AbstractShopView {

    /**
     * 
     */
    private static final long serialVersionUID = 6030584324069338830L;
    private List<ShopItemView> turretInfo;
    private ShopItemView selected;
    private transient ShopController shopController;
    private final GameManager gameManager;
    private boolean isControllerSet;
    private boolean ready;

    /**
     * 
     * @param gm
     */
    public ShopViewImpl(final GameManager gm) {
        this.setLayout(new BorderLayout());
        this.gameManager = gm;

        this.setVisible(false);
    }

    @Override
    protected void update() {
        if (this.ready) {
            this.turretInfo.stream()
                           .forEach(t -> t.setCanvasSize(this.getItemImgSize()));
            this.revalidate();
        }
    }

    private void setup() {
        this.turretInfo = this.shopController.getTurretList()
                .stream().map(this::createShopItemView)
                .collect(Collectors.toList());
        final JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        this.turretInfo.forEach((t) -> panel.add(t));
        final JScrollPane scrollpane = new JScrollPane(panel);
        scrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        this.add(scrollpane, BorderLayout.CENTER);
    }

    private ShopItemView createShopItemView(final Turret t) {
        final ShopItemView item = ShopItemView.from(t);
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                if ((selected == null || !selected.isSelected()) && ShopViewImpl.this.shopController.buy(t)) {
                    item.setSelected(true);
                    item.setCancelMode();
                    selected = item;
                    disableAll();
                } else if (selected == item) {
                    ShopViewImpl.this.shopController.cancel();
                    item.setSelected(false);
                    item.setBuyMode();
                    enableAll();
                    selected = null;
                }
            }
        });
        return item;
    }

    @Override
    public void disableAll() {
        if (this.ready) {
            this.turretInfo.stream()
                           .filter(i -> i != selected)
                           .forEach(i -> i.setButtonEnabled(false));
        }
    }

    @Override
    public void enableAll() {
        if (this.ready) {
            this.turretInfo.stream()
                           .filter(i -> i != selected)
                           .forEach(i -> i.setButtonEnabled(true));
        }
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
        // TODO Auto-generated method stub 
    }
}
