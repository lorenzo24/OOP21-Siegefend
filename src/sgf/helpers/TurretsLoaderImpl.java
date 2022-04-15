package sgf.helpers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import sgf.model.map.Position;
import sgf.model.turret.Turret;
import sgf.model.turret.TurretImpl;

/**
 * Loads turrets from JSON file.
 */
public class TurretsLoaderImpl implements TurretsLoader {

    private final Map<Integer, Turret> turrets;

    /**
     * Constructor to loads the turrets.
     */
    public TurretsLoaderImpl() {
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
}
