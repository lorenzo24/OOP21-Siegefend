package sgf.utilities;

import java.util.ArrayList;
import java.util.List;

import sgf.controller.map.MapController;
import sgf.model.Enemy;
import sgf.model.EnemyImpl;
import sgf.model.EnemyType;
import sgf.model.Wave;
import sgf.model.WaveImpl;

/**
 * Loads waves from file.
 */
public class WavesLoaderImpl implements WavesLoader {

    private final MapController mapController;
    private final List<Wave> waves = new ArrayList<>();

    /**
     * 
     * @param mapController
     */
    public WavesLoaderImpl(final MapController mapController) {
        this.mapController = mapController;
        this.generateWave();
    }

    private void generateWave() {
        List<Enemy> list = new ArrayList<>();
        list.add(new EnemyImpl(1, mapController.convertToPosition(mapController.getMap().getStartTile()), 100, 1, EnemyType.TANK));
        list.add(new EnemyImpl(2, mapController.convertToPosition(mapController.getMap().getStartTile()), 100, 1, EnemyType.HELICOPTER));
        list.add(new EnemyImpl(3, mapController.convertToPosition(mapController.getMap().getStartTile()), 100, 1, EnemyType.TANK));
        waves.add(new WaveImpl(list));
        list = new ArrayList<>();
        list.add(new EnemyImpl(1, mapController.convertToPosition(mapController.getMap().getStartTile()), 100, 1, EnemyType.TANK));
        list.add(new EnemyImpl(2, mapController.convertToPosition(mapController.getMap().getStartTile()), 100, 1, EnemyType.HELICOPTER));
        list.add(new EnemyImpl(3, mapController.convertToPosition(mapController.getMap().getStartTile()), 100, 1, EnemyType.TANK));
        waves.add(new WaveImpl(list));
    }

    @Override
    public List<Wave> getWaves() {
        return waves;
    }
}
