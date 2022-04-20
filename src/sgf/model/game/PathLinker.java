package sgf.model.game;

import java.util.Map;
import sgf.model.enemies.EnemyType;
import sgf.model.map.TileType;

/**
 * This interface links every type (both of {@link Enemy} and {@link Tile}) to the correspondent image file in the res folder.
 */
public interface PathLinker {

    /**
     * Method that links every {@link Enemy} type with the correspondent image path name.
     * @return a map that links every {@link Enemy} type with the correct image path.
     */
     Map<EnemyType, String> getEnemyMap();

     /**
      * Method that links every {@link TileType} with the correspondent image path name.
      * @return a map that links every {@link TileType} with the correct image path.
      */
     Map<TileType, String> getTileMap();

     /**
      * Method that links every {@link Turret} id with the correspondent image path name.
      * @return a map that links every {@link Turret} id with the correct image path.
      */
     Map<Integer, String> getTurretMap();

     /**
      * Method that links every {@link Bullet} id with the correspondent image path name.
      * @return a map that links every {@link Bullet} id with the correct image path.
      */
     Map<Integer, String> getBulletMap();

     /**
      * Method that links lifeBar with the correspondent image path name.
      * @return a map that links the lifeBar with the correct image path.
      */
     Map<Integer, String> getLifeBarMap();
}
