package sgf.helpers;

import java.io.File;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import sgf.model.level.Level;

/**
 * Implementation of interface LevelLoader.
 */
public class LevelLoaderImpl implements LevelLoader {

    private final int levelsNumber;

    /**
     * Constructor that calculates how many levels are available.
     */
    public LevelLoaderImpl() {
        this.levelsNumber = this.countMapFiles();
    }

    @Override
    public Level loadLevel(final int levelID) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getLevelsNumber() {
        return this.levelsNumber;
    }
    private int countMapFiles() {
        long result = Stream.of(new File("res" + File.separator).listFiles())
                .filter(file -> !file.isDirectory())
                .count();
        System.out.println(result);
        return (int) result;
    }
}
