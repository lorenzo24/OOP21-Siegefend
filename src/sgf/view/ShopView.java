package sgf.view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import sgf.controller.ShopController;
import sgf.controller.ShopControllerImpl;
import sgf.model.Turret;
import sgf.utilities.GameManager;

/**
 *
 */
public class ShopView extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = 2452674161344433337L;
    private final List<ShopItemView> turretInfo;
    private ShopItemView selected;
    private final transient ShopController shopController;

    /**
     * 
     * @param gm
     */
    public ShopView(final GameManager gm) {
        this.shopController = new ShopControllerImpl(gm);
        this.turretInfo = this.shopController.getTurretList()
                                          .stream().map(this::createShopItemView)
                                          .collect(Collectors.toList());
        final JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        this.turretInfo.forEach((t) -> panel.add(t));
        final JScrollPane scrollpane = new JScrollPane(panel);
        scrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        this.add(scrollpane);

        this.setVisible(true);
    }

    private ShopItemView createShopItemView(final Turret t) {
        final ShopItemView item = ShopItemView.from(t);
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                if ((selected == null || !selected.isSelected()) && ShopView.this.shopController.buy(t)) {
                    item.setSelected(true);
                    item.setCancelMode();
                    selected = item;
                    disableAll();
                } else if (selected == item) {
                    ShopView.this.shopController.cancel();
                    item.setSelected(false);
                    item.setBuyMode();
                    enableAll();
                    selected = null;
                }
            }
        });
        return item;
    }

    private void disableAll() {
        this.turretInfo.stream()
                       .filter(i -> i != selected)
                       .forEach(i -> i.setButtonEnabled(false));
    }

    private void enableAll() {
        this.turretInfo.stream()
                       .filter(i -> i != selected)
                       .forEach(i -> i.setButtonEnabled(true));
    }
}
