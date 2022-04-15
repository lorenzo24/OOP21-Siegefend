package sgf.helpers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import sgf.model.bullet.Bullet;
import sgf.model.enemies.Enemy;
import sgf.model.map.Position;
import sgf.model.turret.Turret;
import sgf.model.turret.TurretImpl;

/**
 *
 */
public class SimpleTurretsLoader implements TurretsLoader {

    private final Map<Integer, Turret> turrets;

    /**
     * Constructor to loads the turrets.
     */
    public SimpleTurretsLoader() {
        this.turrets = new HashMap<>();
        this.readFile();
    }

    @Override
    public Map<Integer, Turret> getTurrets() {
        return this.turrets;
    }

    private void readFile() {
        final Path p = FileSystems.getDefault().getPath("res" + File.separator + "turret" + File.separator + "turrets.json");
        final File f = p.toFile();
        final JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader(f)) {
            final Object obj = parser.parse(reader);
            final JSONArray array = (JSONArray) obj;
            array.forEach(x -> parseTurretObject((JSONObject) x)); // Every record in the file will be converted to one entry of the map.
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
    }

    // Convert an record to one element of the map.
    private void parseTurretObject(final JSONObject record) {
        final JSONObject turret = (JSONObject) record;
        final double fireRate = Double.parseDouble(turret.get("fireRate").toString());
        final double range = Double.parseDouble(turret.get("range").toString());
        final int price = Integer.parseInt(turret.get("price").toString());
        final int iD = Integer.parseInt(turret.get("ID").toString());
        final Position position = (Position) turret.get("position");
        final double bulletSpeed = Double.parseDouble(turret.get("bulletSpeed").toString());
        final double bulletDamage = Double.parseDouble(turret.get("bulletDamage").toString());
        this.turrets.put(iD, new TurretImpl(iD, position, range, price, fireRate, bulletSpeed, bulletDamage));
    }

    /**
     * 
     */
    private static final class SimpleTurretImpl implements Turret, Cloneable {
        private final int v;
        private final int id;

        SimpleTurretImpl(final int id, final int v) {
            this.id = id;
            this.v = v;
        }

        @Override
        public boolean isAttacking() {
            return false;
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
            return this.id;
        }

        @Override
        public double getFireRate() {
            return 0;
        }

        @Override
        public SimpleTurretImpl clone() throws CloneNotSupportedException {
            return (SimpleTurretImpl) super.clone();
        }

        @Override
        public Turret getClone() {
            try {
                return this.clone();
            } catch (CloneNotSupportedException e) { }
            throw new UnsupportedOperationException("Class " + SimpleTurretsLoader.class.getName() + " does not support cloning.");
        }

        @Override
        public void setState(final boolean state) {
            // TODO Auto-generated method stub

        }

        @Override
        public Bullet createBullet() {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public double getAngle() {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public void setAngle(double angle) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void setPosition(Position p) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void setTarget(Enemy target) {
            // TODO Auto-generated method stub
            
        }
    }
}
