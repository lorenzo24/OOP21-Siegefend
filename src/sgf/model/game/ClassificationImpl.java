package sgf.model.game;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;


/**
 * Class that managed the classification.
 */
public class ClassificationImpl implements Classification {
    private final Map<String, String> mapScore = new HashMap<>();
    private final Path p = FileSystems.getDefault().getPath("res" + File.separator + "classification.txt");

    @Override
    public void writeScore() {
        try {
            this.p.toFile().delete();
            this.p.toFile().createNewFile();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        mapScore.entrySet().stream().sorted((x, y) -> Integer.parseInt(y.getValue()) - Integer.parseInt(x.getValue())).forEach(x -> {
            try {
                Files.writeString(this.p, x.getKey() + ":" + x.getValue() + "\n", StandardOpenOption.APPEND);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void readScore() {
        try {
            Files.lines(this.p).forEach(x -> mapScore.put(x.split(":")[0], x.split(":")[1]));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addPlayer(final String name, final String points) {
        this.mapScore.put(name, points);
    }
}
