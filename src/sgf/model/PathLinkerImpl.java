package sgf.model;

import java.util.HashMap;
import java.util.Map;

import sgf.model.enemies.EnemyType;

/**
 * This class is the implementation of the interface PathLinker, which links some entities with images path name.
 */
public class PathLinkerImpl implements PathLinker {
    private final Map<EnemyType, String> mapEnemy = new HashMap<>();
    private final Map<TileType, String> mapTile = new HashMap<>();

    /**
     * Simple constructor that class methods to fill its fields.
     */
    public PathLinkerImpl() {
        this.createEnemyMap();
        this.createTileMap();
    }

    private void createTileMap() {
        // Links every tile type with the correct image that will be showed.
        this.mapTile.put(TileType.GRASS, "grass.png");
        this.mapTile.put(TileType.PATH, "sand.png");
        this.mapTile.put(TileType.START_PATH, "sand.png");
        this.mapTile.put(TileType.END_PATH, "sand.png");
        this.mapTile.put(TileType.WATER, "water.png");
    }

    private void createEnemyMap() {
        this.mapEnemy.put(EnemyType.TANK, "tank.png");
        this.mapEnemy.put(EnemyType.PLANE, "plane.png");
        this.mapEnemy.put(EnemyType.HELICOPTER, "helicopter.png");

    }

    @Override
    public Map<EnemyType, String> getEnemyMap() {
        return Map.copyOf(this.mapEnemy);
    }

    @Override
    public Map<TileType, String> getTileMap() {
        return Map.copyOf(this.mapTile);
    }

}
