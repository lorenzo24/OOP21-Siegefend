package sgf.view;

import java.awt.Dimension;
//import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.awt.Toolkit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import sgf.model.Enemy;
import sgf.model.Position;
import sgf.model.Turret;

/**
 *
 */
public class ShopView extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static final double WIDTH_PERC = 0.4;
    private static final double HEIGHT_PERC = 0.23;
    private List<ShopTurretInfo> turretInfo;
    private ShopTurretInfo selected;

    /**
     * 
     * @param turrets
     */
    public ShopView(final List<Turret> turrets) {
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize((int) (screenSize.getWidth() * WIDTH_PERC), (int) (screenSize.getHeight() * HEIGHT_PERC));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.turretInfo = turrets.stream().map(t -> ShopTurretInfo.from(t))
                                          .peek(v -> v.addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(final ActionEvent e) {
                                                if (selected == null || !selected.isSelected()) {
                                                    v.setSelected(true);
                                                    v.setCancelMode();
                                                    selected = v;
                                                    disableAll();
                                                } else if (selected == v) {
                                                    v.setSelected(false);
                                                    v.setBuyMode();
                                                    enableAll();
                                                    selected = null;
                                                }
                                            }
                                        })).collect(Collectors.toList());
        final JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        this.turretInfo.forEach((t) -> panel.add(t));
        final JScrollPane scrollpane = new JScrollPane(panel);
        scrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        this.getContentPane().add(scrollpane);

        this.setVisible(true);
    }

    /**
     * 
     * @param args
     */
    public static void main(final String... args) {
        final List<Turret> turrets = Stream.iterate(0, i -> i + 1)
                            .limit(10)
                            .map(i -> new ShopView.SimpleTurretImpl(i))
                            .collect(Collectors.toList());
        new ShopView(turrets);
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

    /**
     * 
     */
    private static final class SimpleTurretImpl implements Turret, Cloneable {
        private final int v;

        SimpleTurretImpl(final int v) {
            this.v = v;
        }

        @Override
        public boolean isAttacking() {
            return false;
        }

        @Override
        public void idle() {
        }

        @Override
        public Optional<Enemy> getTarget() {
            return Optional.empty();
        }

        @Override
        public double getRange() {
            return 0;
        }

        @Override
        public int getPrice() {
            return v * 100;
        }

        @Override
        public Position getPosition() {
            return null;
        }

        @Override
        public int getID() {
            return 0;
        }

        @Override
        public double getFireRate() {
            return 0;
        }

        @Override
        public void fireAt(final Position target) {
        }

        @Override
        public Optional<Enemy> findTarget() {
            return Optional.empty();
        }

        @Override
        public void attack() {
        }

        @Override
        public SimpleTurretImpl clone() throws CloneNotSupportedException {
            return (SimpleTurretImpl) super.clone();
        }
    }
}
