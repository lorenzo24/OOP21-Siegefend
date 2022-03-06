package sgf.utilities;

import java.util.List;

import sgf.model.Wave;

/**
 * Loads the waves from file.
 */
public interface WavesLoader {

    /**
     * The list of waves.
     * @return list of waves
     */
    List<Wave> getWaves();

}
