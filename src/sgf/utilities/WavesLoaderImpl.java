package sgf.utilities;

import java.util.ArrayList;
import java.util.List;
import sgf.controller.map.MapController;
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
        
        list.add(new Tank(mapController.convertToPosition(mapController.getMap().getStartTile())));
        list.add(new Helicopter(mapController.convertToPosition(mapController.getMap().getStartTile())));
        list.add(new Tank(mapController.convertToPosition(mapController.getMap().getStartTile())));
        waves.add(new WaveImpl(list));
        list = new ArrayList<>();
        list.add(new Helicopter(mapController.convertToPosition(mapController.getMap().getStartTile())));
        list.add(new Tank(mapController.convertToPosition(mapController.getMap().getStartTile())));
        list.add(new Tank(mapController.convertToPosition(mapController.getMap().getStartTile())));
        waves.add(new WaveImpl(list));
    }

    @Override
    public List<Wave> getWaves() {
        return waves;
    }
}
