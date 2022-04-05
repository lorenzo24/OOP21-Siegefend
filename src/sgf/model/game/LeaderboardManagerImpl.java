package sgf.model.game;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.text.ParseException;
import java.util.Date;

import javax.swing.text.html.HTMLEditorKit.Parser;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import netscape.javascript.JSObject;

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
        this.writeScore();
    }

    @Override
    public void writeScore() {
        this.clearFile();
        System.out.print(this.leaderboard.getMapScore().size());
        JSONArray array = new JSONArray();
        this.leaderboard.getMapScore().entrySet().stream()
            .sorted((x, y) -> y.getValue().getY() - x.getValue().getY())
            .forEach(x -> {
                final JSONObject jo = new JSONObject();
                jo.put("date", x.getKey());
                jo.put("name", x.getValue().getX());
                jo.put("score", x.getValue().getY());
                
                JSONObject line = new JSONObject(); 
                line.put("line", jo);
                array.add(line);

                try (FileWriter file = new FileWriter(this.leaderboard.getP().toString())) {
                    file.write(array.toJSONString()); 
                    file.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        });
    }

    // Clear the initial file.
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

    private void readScore() {
        final JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader(this.leaderboard.getP().toString()))
        {
            Object obj = parser.parse(reader);
            JSONArray array = (JSONArray) obj;
            //Iterate over employee array
            array.forEach( emp -> parseEmployeeObject( (JSONObject) emp ) );
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
    }
    
    private void parseEmployeeObject(JSONObject employee) 
    {
        //Get employee object within list
        JSONObject employeeObject = (JSONObject) employee.get("line");
         
        //Get employee first name
        String date = (String) employeeObject.get("date");    
         
        //Get employee last name
        String name = (String) employeeObject.get("name");  
         
        //Get employee website name
        int score = Integer.parseInt(employeeObject.get("score").toString());
        this.leaderboard.addRecord(date, name, score);
    }

    @Override
    public void addScore(final String name, final int score) {
        this.leaderboard.addRecord(new Date().toString(), name, score);
    }
}
