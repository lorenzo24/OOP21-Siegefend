package sgf.helpers;

import sgf.model.enemies.EnemyType;
import sgf.model.map.TileType;

/**
 * This interface links every type (both of enemy and tile) to the correspondent image file in the res folder.
 */
public interface PathLinker {

    /**
     * Method that links every enemy type with the correspondent image path name.
     * @return a map that links every enemy type with the correct image path.
     */
     java.util.Map<EnemyType, String> getEnemyMap();

     /**
      * Method that links every tile type with the correspondent image path name.
      * @return a map that links every tile type with the correct image path.
      */
     java.util.Map<TileType, String> getTileMap();
}
