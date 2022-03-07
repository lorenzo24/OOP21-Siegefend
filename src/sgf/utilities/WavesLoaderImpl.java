package sgf.utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import sgf.controller.map.MapController;
import sgf.model.GridPosition;
import sgf.model.TileImpl;
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
    private final List<Wave> waves = new ArrayList<>();
    private final List<Enemy> waveEnemies;
    

    /**
     * 
     * @param mapController
     */
    public WavesLoaderImpl(final MapController mapController, final int levelId) {
        this.waveEnemies = new ArrayList<>();
        this.mapController = mapController;
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
        this.waves.get(0).getEnemies().forEach(x -> System.out.println(x.getEnemyType()));
        this.waveEnemies.clear();
        
    }

    private void addEnemyToWave(final String enemy) {
        
        // Pensare se al posto dello switch usare mappa <Integer, EnemyType>.
        switch (enemy) {
        case "1":
            
            this.waveEnemies.add(new Tank(mapController.convertToPosition(mapController.getMap().getStartTile())));
            break;
        case "2":
            this.waveEnemies.add(new Helicopter(mapController.convertToPosition(mapController.getMap().getStartTile())));
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
