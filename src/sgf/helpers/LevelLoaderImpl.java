package sgf.helpers;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import sgf.model.level.Level;
import sgf.model.level.LevelImpl;
import sgf.model.level.Wave;
import sgf.model.map.Map;

/**
 * Implementation of interface LevelLoader.
 */
public class LevelLoaderImpl implements LevelLoader {
    private int levelsNumber;

    /**
     * Constructor that calculates how many levels are available.
     */
    public LevelLoaderImpl() {
        try {
            this.levelsNumber = this.countMapFiles();
        } catch (IOException e) {
            this.levelsNumber = -1;
            e.printStackTrace();
        }
        if (this.levelsNumber == -1) {
            throw new IllegalStateException("Couldn't count levels.");
        }
    }

    @Override
    public Level loadLevel(final int levelID) {
        // Creates the chosen level by loading the corresponding map and waves.
        final Map map = new MapLoaderImpl(levelID).getMap();
        final List<Wave> waves = new WavesLoaderImpl(map, levelID).getWaves();
        return new LevelImpl(waves, map, levelID);
    }

    @Override
    public int getLevelsNumber() {
        return this.levelsNumber;
    }

    // This method counts how many files are there into the folder res/levels.
    private int countMapFiles() throws IOException {
        //        return (int) Stream.of(new File("res" + File.separator + "levels").listFiles())
        //                .filter(file -> !file.isDirectory())
        //                .count();

        //        final URL folderUrl = ClassLoader.getSystemResource("levels");
        //        File f;
        //        try {
        //            f = new File(folderUrl.toURI());
        //            return (int) Stream.of(f.listFiles())
        //                               .filter(file -> !file.isDirectory())
        //                               .count();
        //        } catch (URISyntaxException e) {
        //            e.printStackTrace();
        //        }
        int count = 0;

        URI uri = null;
        try {
            uri = ClassLoader.getSystemResource("levels").toURI();
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return -1;
        }
        Path myPath;
        if ("jar".equals(uri.getScheme())) {
            final FileSystem fileSystem = FileSystems.newFileSystem(uri, Collections.<String, Object>emptyMap());
            myPath = fileSystem.getPath("levels");
        } else {
            myPath = Paths.get(uri);
        }
        try (Stream<Path> walk = Files.walk(myPath, 1)) {
            for (final Iterator<Path> it = walk.iterator(); it.hasNext();) {
                if (Files.isRegularFile(it.next(), new LinkOption[0])) {
                    count++;
                }
            }
        }
        return count;
    }
}
