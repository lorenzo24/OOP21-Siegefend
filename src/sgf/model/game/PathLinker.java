package sgf.model.game;

import java.util.Map;
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
     Map<EnemyType, String> getEnemyMap();

     /**
      * Method that links every tile type with the correspondent image path name.
      * @return a map that links every tile type with the correct image path.
      */
     Map<TileType, String> getTileMap();

     /**
      * Method that links every turret id with the correspondent image path name.
      * @return a map that links every turret id with the correct image path.
      */
     Map<Integer, String> getTurretMap();

     /**
      * Method that links every bullet id with the correspondent image path name.
      * @return a map that links every bullet id with the correct image path.
      */
     Map<Integer, String> getBulletMap();

     /**
      * Method that links lifeBar with the correspondent image path name.
      * @return a map that links the lifeBar with the correct image path.
      */
     Map<Integer, String> getLifeBarMap();
}
