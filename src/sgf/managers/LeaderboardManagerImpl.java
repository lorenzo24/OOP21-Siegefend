package sgf.managers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.TimeZone;
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
    private final File f;

    /**
     * Reads from file the {@link Leaderboard}.
     */
    public LeaderboardManagerImpl() {
        this.leaderboard = new LeaderboardImpl();
        final URL fileURL = ClassLoader.getSystemResource("classification.json");
        URI uri;
        if (fileURL != null) {
            try {
                uri = fileURL.toURI();
            } catch (URISyntaxException e) {
                e.printStackTrace();
                uri = null;
            }
        } else {
            try {
                uri = new URI(ClassLoader.getSystemResource("").toURI().toString() + "classification.json");
            } catch (URISyntaxException e) {
                e.printStackTrace();
                uri = null;
            }
        }
        if (uri != null) {
            this.f = new File(uri);
            this.readScore();
        } else {
            throw new IllegalStateException();
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void writeScore() {
        this.clearFile();
        final JSONArray jsonElements = new JSONArray();
        this.leaderboard.getMapScore().entrySet().stream()
            .sorted((x, y) -> y.getValue().getY() - x.getValue().getY())
            .forEach(x -> {
                jsonElements.add(this.createJsonElem(x));
            });
        try (FileWriter writer = new FileWriter(this.f)) {
            writer.write(jsonElements.toJSONString());
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    // Clear the initial file delete and create new.
    private void clearFile() {
        try {
            if (this.f.exists()) {
                if (this.f.delete()) {
                    if (!this.f.createNewFile()) {
                        throw new IOException("Failed to create file.");
                    }
                } else {
                    throw new IOException("Failed to delete file.");
                }
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void readScore() {
        if (this.f.length() == 0) { // If file of leaderboard is empty not do any.
            return;
        }
        final JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader(this.f)) {
            final Object obj = parser.parse(reader);
            final JSONArray array = (JSONArray) obj;
            array.forEach(x -> parsePlayerObject((JSONObject) x)); // Every record in the file will be converted to one entry of the map.
        } catch (IOException e) {
            e.printStackTrace();
        } catch (org.json.simple.parser.ParseException e) {
            System.err.print("Leaderboard not loaded, file corrupt");
        }
    }

    // Convert an record to one element of the map.
    private void parsePlayerObject(final JSONObject player) {
        final String date = player.get("date").toString();
        final String name = player.get("name").toString();
        final int score = Integer.parseInt(player.get("score").toString());
        this.leaderboard.addRecord(date, name, score); // Add the element to the map.*/
    }

    @Override
    public void addScore(final String name, final int score) { // Add a score to the leaderboard.
        final SimpleDateFormat dateFormat = (SimpleDateFormat) SimpleDateFormat.getDateInstance(DateFormat.SHORT, Locale.ITALY);
        dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Rome"));
        dateFormat.applyPattern("yyyy-MM-dd E 'at' HH:mm:ss.SSS z");
        this.leaderboard.addRecord(dateFormat.format(new Date()), name, score);
    }

    @Override
    public Leaderboard getLeaderboard() {
        return this.leaderboard;
    }
}
