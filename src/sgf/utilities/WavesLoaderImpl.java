package sgf.utilities;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import sgf.controller.map.MapController;
import sgf.model.Position;
import sgf.model.Wave;
import sgf.model.WaveImpl;
import sgf.model.enemies.Enemy;
import sgf.model.enemies.Helicopter;
import sgf.model.enemies.Tank;

/**
 * Loads waves from file.
 */
public class WavesLoaderImpl implements WavesLoader {

    private final MapController mapController;
    private final Position startPosition;
    private final List<Wave> waves = new ArrayList<>();
    private List<Enemy> waveEnemies;

    /**
     * ciao.
     * @param mapController
     * @param levelId
     */
    public WavesLoaderImpl(final MapController mapController, final int levelId) {
        this.waveEnemies = new ArrayList<>();
        this.mapController = mapController;
        this.startPosition = this.mapController.convertToPosition(mapController.getMap().getStartTile());
        this.generateWave(levelId);
    }

    private void generateWave(final int levelId) {
        final String file = "res" + File.separator + "level" + levelId + ".txt";
        final Path p = FileSystems.getDefault().getPath(file);
        try {
            Files.lines(p).forEach(x -> read(x));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void read(final String text) {
        final List<String> splitted = Arrays.asList(text.split("\\s+"));
        splitted.forEach(e -> this.addEnemyToWave(e));
        this.waves.add(new WaveImpl(this.waveEnemies));
        this.waveEnemies = new ArrayList<>();
    }

    private void addEnemyToWave(final String enemy) {
        // Pensare se al posto dello switch usare mappa <Integer, EnemyType>.
        switch (enemy) {
        case "1":
            this.waveEnemies.add(new Tank(startPosition));
            break;
        case "2":
            this.waveEnemies.add(new Helicopter(startPosition));
            break;
        default:
            break;
        }
    }

    @Override
    public List<Wave> getWaves() {
        return waves;
    }
}
