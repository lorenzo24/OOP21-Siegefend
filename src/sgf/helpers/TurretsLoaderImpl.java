package sgf.helpers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
     * Constructor to load the {@link Turret}.
     */
    public TurretsLoaderImpl() {
        this.turrets = new HashMap<>();
        this.readFile();
    }

    @Override
    public Map<Integer, Turret> getTurrets() {
        return this.turrets;
    }

    @SuppressWarnings("unchecked")
    private void readFile() {
        final InputStream is = ClassLoader.getSystemResourceAsStream("turret/turrets.json");
        final JSONParser parser = new JSONParser();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            final Object obj = parser.parse(reader);
            final JSONArray array = (JSONArray) obj;
            array.forEach(x -> parseTurretObject((JSONObject) x)); // Every record in the file will be converted to one entry of the map.
        } catch (org.json.simple.parser.ParseException |  IOException e) {
            e.printStackTrace();
        }
    }

    // Converts a record to one element of the map.
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
