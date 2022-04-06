package sgf.managers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Map.Entry;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import sgf.model.game.Leaderboard;
import sgf.model.game.LeaderboardImpl;
import sgf.utilities.Pair;

/**
 * Class that managed the leaderboard.
 */
public class LeaderboardManagerImpl implements LeaderboardManager {

    private final Leaderboard leaderboard;

    /**
     * Reads from file the loaderboard.
     */
    public LeaderboardManagerImpl() {
        this.leaderboard = new LeaderboardImpl();
        this.readScore();
    }

    @Override
    public void writeScore() {
        this.clearFile();
        final JSONArray jsonElements = new JSONArray();
        this.leaderboard.getMapScore().entrySet().stream()
            .sorted((x, y) -> y.getValue().getY() - x.getValue().getY())
            .forEach(x -> {
                jsonElements.add(this.createRecord(this.createJsonElem(x)));
                try (FileWriter file = new FileWriter(this.leaderboard.getP().toString())) {
                    file.write(jsonElements.toJSONString()); 
                    file.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        });
    }

    // Create one element of one record.
    @SuppressWarnings("unchecked")
    private JSONObject createJsonElem(final Entry<String, Pair<String, Integer>> x) {
        final JSONObject elem = new JSONObject();
        elem.put("date", x.getKey());
        elem.put("name", x.getValue().getX());
        elem.put("score", x.getValue().getY());
        return elem;
    }

    // Create one record of json.
    @SuppressWarnings("unchecked")
    private JSONObject createRecord(final JSONObject elem) {
        final JSONObject record = new JSONObject(); 
        record.put("line", elem);
        return record;
    }

    // Clear the initial file delete and create new.
    private void clearFile() {
        try {
            final File f = this.leaderboard.getP().toFile();
            if (f.delete()) {
                if (!f.createNewFile()) {
                    throw new IOException("Failed to create file.");
                }
            } else {
                throw new IOException("Failed to delete file.");
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void readScore() {
        final JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader(this.leaderboard.getP().toString())) {
            final Object obj = parser.parse(reader);
            final JSONArray array = (JSONArray) obj;
            array.forEach(x -> parsePlayerObject((JSONObject) x)); // Every record in the file will be converted to one entry of the map.
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
    }

    // Convert an record to one element of the map.
    private void parsePlayerObject(final JSONObject record) {
        final JSONObject player = (JSONObject) record.get("line");
        final String date = (String) player.get("date");
        final String name = (String) player.get("name");
        final int score = Integer.parseInt(player.get("score").toString());
        this.leaderboard.addRecord(date, name, score); // Add the element to the map.
    }

    @Override
    public void addScore(final String name, final int score) { // Add a score to the leaderboard.
        this.leaderboard.addRecord(new Date().toString(), name, score);
    }
}
