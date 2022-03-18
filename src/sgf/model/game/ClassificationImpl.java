package sgf.model.game;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that managed the classification.
 */
public class ClassificationImpl implements Classification {
    private int actualScore = 0;
    private final Map<Date, Integer> listScore = new HashMap<>();
    private final String file = "res" + File.separator + "classification.txt";

    @Override
    public void updateScore(final int score) {
        this.actualScore += score;
    }

    @Override
    public void writeScore() {
        final Path p = FileSystems.getDefault().getPath(file);
        try {
            Files.writeString(p, "ciao", StandardOpenOption.WRITE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void readScore() {
        /*final Path p = FileSystems.getDefault().getPath(file);
        try {
            Files.lines(p).forEach(s -> read(s));
            this.map.setMapSize(this.mapRows);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

}
