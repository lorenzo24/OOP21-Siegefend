package sgf.model.game;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import sgf.model.enemies.EnemyType;
import sgf.model.map.TileType;

/**
 * This class is the implementation of the interface PathLinker, which links some entities with their image path names.
 */
public class PathLinkerImpl implements PathLinker {
    private final Map<EnemyType, String> mapEnemy;
    private final Map<TileType, String> mapTile;
    private final Map<Integer, String> mapTurret;
    private final Map<Integer, String> mapBarLife;

    /**
     * Simple constructor that class methods to fill its fields.
     */
    public PathLinkerImpl() {
        this.mapEnemy = new HashMap<>();
        this.mapTile = new HashMap<>();
        this.mapTurret = new HashMap<>();
        this.mapBarLife = new HashMap<>();
        this.createEnemyMap();
        this.createTileMap();
        this.createTurretMap();
        this.createLifeBarMap();
    }

    @Override
    public Map<EnemyType, String> getEnemyMap() {
        return Map.copyOf(this.mapEnemy);
    }

    @Override
    public Map<TileType, String> getTileMap() {
        return Map.copyOf(this.mapTile);
    }

    private void createEnemyMap() {
        // Links every enemy type with the correct image that will be shown.
        final String folder = "enemies" + File.separator;
        this.mapEnemy.put(EnemyType.TANK, folder + "tank.png");
        this.mapEnemy.put(EnemyType.PLANE, folder + "plane.png");
        this.mapEnemy.put(EnemyType.HELICOPTER, folder + "helicopter.png");
    }

    private void createTileMap() {
        // Links every tile type with the correct image that will be shown.
        final String folder = "mapTiles" + File.separator;
        this.mapTile.put(TileType.GRASS, folder + "grass.png");
        this.mapTile.put(TileType.PATH, folder + "sand.png");
        this.mapTile.put(TileType.START_PATH, folder + "start.png");
        this.mapTile.put(TileType.END_PATH, folder + "end.png");
        this.mapTile.put(TileType.WATER, folder + "water.png");
    }

    private void createTurretMap() {
        // Links every turret number with the correct image that will be shown.
        final String turretPath = "turret" + File.separator;
        this.mapTurret.put(0, turretPath + "turret.png");
        this.mapTurret.put(1, turretPath + "turret1.png");
        this.mapTurret.put(2, turretPath + "turret2.png");
    }

    private void createLifeBarMap() {
        this.mapBarLife.put(0, "enemies" + File.separator + "lifeBar.png");
    }

    @Override
    public Map<Integer, String> getTurretMap() {
        return Map.copyOf(this.mapTurret);
    }

    @Override
    public Map<Integer, String> getLifeBarMap() {
        return Map.copyOf(this.mapBarLife);
    }
}
