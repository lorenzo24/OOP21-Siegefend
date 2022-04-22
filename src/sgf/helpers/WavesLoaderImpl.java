package sgf.helpers;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import sgf.model.enemies.Enemy;
import sgf.model.enemies.EnemyFactory;
import sgf.model.enemies.EnemyFactoryImpl;
import sgf.model.level.Wave;
import sgf.model.level.WaveImpl;
import sgf.model.map.Map;
import sgf.model.map.Position;
import sgf.utilities.PositionConverter;

/**
 * Class that loads waves by reading them from file.
 */
public class WavesLoaderImpl implements WavesLoader {
    private final Position startPosition;
    private final List<Wave> waves = new ArrayList<>();
    private List<Enemy> waveEnemies;
    private final EnemyFactory enemyFactory;

    /**
     * Constructor that sets the initial position and {@link Level} id.
     * @param map Is the {@link Map} of the level.
     * @param levelId Is the levelId.
     */
    public WavesLoaderImpl(final Map map, final int levelId) {
        this.waveEnemies = new ArrayList<>();
        this.enemyFactory = new EnemyFactoryImpl();
        final PositionConverter converter = new PositionConverter(ImgTileSize.getTileSize());
        this.startPosition = converter.convertToPosition(map.getStartTile());
        this.readAllWaves(levelId);
    }

    @Override
    public List<Wave> getWaves() {
        return waves;
    }

    private void readAllWaves(final int levelId) {
        final String file;
        if (levelId > 0) {      // If levelID > 0 it must load an actual level (not a wrong level for testing).
            file = "levels/level" + levelId + ".txt";
        } else {        // If levelId <= 0 it must load a wrong file for testing, so it must use another folder.
            file = "tests/level" + levelId + ".txt";
        }
//        Path p = null;
//        try {
//            p = Path.of(new URI(ClassLoader.getSystemResource(file).getPath()));
//        } catch (URISyntaxException e1) {
//            e1.printStackTrace();
//        }
//        try {
//            if (Files.lines(p).count() == 0L) {
//                throw new IllegalStateException();
//            }
//            Files.lines(p).forEach(x -> read(x));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        try (InputStream is = ClassLoader.getSystemResourceAsStream(file)) {
            try (BufferedReader r = new BufferedReader(new InputStreamReader(is))) {
                r.lines().forEach(s -> read(s));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method that adds all the enemies specified from the read row.
    private void read(final String text) {
        final List<String> splitted = Arrays.asList(text.split("\\s+"));
        splitted.forEach(e -> this.addEnemyToWave(e));  // Each value read must be converted to the corresponding enemy type.
        this.waves.add(new WaveImpl(this.waveEnemies));
        this.waveEnemies = new ArrayList<>();
    }

    // Converts the string read into the correct enemy type.
    private void addEnemyToWave(final String enemy) {
        switch (enemy) {
        case "1":
            this.waveEnemies.add(this.enemyFactory.createTank(startPosition));
            break;
        case "2":
            this.waveEnemies.add(this.enemyFactory.createPlane(startPosition));
            break;
        case "3":
            this.waveEnemies.add(this.enemyFactory.createHelicopter(startPosition));
            break;
        default:
            throw new IllegalArgumentException("Enemy code read in file cannot be encoded");
        }
    }
}
