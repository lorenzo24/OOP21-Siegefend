package sgf.utilities;

import java.util.List;
import sgf.model.Wave;

/**
 * Loads the waves from file.
 */
public interface WavesLoader {

    /**
     * Getter for the list of waves.
     * @return the list of waves.
     */
    List<Wave> getWaves();

}
