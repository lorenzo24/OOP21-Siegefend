package sgf.view;

//import java.awt.Dimension;
import java.awt.FlowLayout;
//import java.awt.Toolkit;
import java.util.List;
//import java.util.Optional;
import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//import javax.swing.JFrame;
import javax.swing.JPanel;

//import sgf.model.Enemy;
//import sgf.model.Position;
import sgf.model.Turret;

/**
 *
 */
public class ShopView extends JPanel /* JFrame */ {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
//    private static final double WIDTH_PERC = 0.4;
//    private static final double HEIGHT_PERC = 0.4;
    private List<ShopTurretInfo> turretInfo;

    /**
     * 
     * @param turrets
     */
    public ShopView(final List<Turret> turrets) {
//        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//        this.setSize((int) (screenSize.getWidth() * WIDTH_PERC), (int) (screenSize.getHeight() * HEIGHT_PERC));
//        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.turretInfo = turrets.stream().map(t -> ShopTurretInfo.from(t)).collect(Collectors.toList());
        final JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        this.turretInfo.forEach((t) -> panel.add(t));
        this/*.getContentPane()*/.add(panel);

//        this.setVisible(true);
    }

/*
    public static void main(final String... args) {
        List<Turret> turrets = Stream.iterate(0, i -> i + 1)
                            .limit(10)
                            .map(i -> new Turret() {
                                @Override
                                public boolean isAttacking() {
                                    return false;
                                }

                                @Override
                                public void idle() {
                                }

                                @Override
                                public Optional<Enemy> getTarget() {
                                    return null;
                                }

                                @Override
                                public double getRange() {
                                    return 0;
                                }

                                @Override
                                public int getPrice() {
                                    return i * 100;
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
                                public void fireAt(Position target) {
                                }

                                @Override
                                public Optional<Enemy> findTarget() {
                                    return null;
                                }

                                @Override
                                public void attack() {
                                }

                                @Override
                                public Turret clone() throws CloneNotSupportedException {
                                    return (Turret) super.clone();
                                }
                            }).collect(Collectors.toList());
        new ShopView(turrets);
    } 
*/
}
