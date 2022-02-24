package sgf.model;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class UrlImageImpl implements UrlImage {

    private final Map<EnemyType, String> mapEnemy = new HashMap<>();
    private final Map<TileType, String> mapTile = new HashMap<>();

    /**
     * 
     */
    public UrlImageImpl() {
        this.createEnemyMap();
        this.createTileMap();
    }

    private void createTileMap() {
        this.mapTile.put(TileType.GRASS, "grass.png");
        this.mapTile.put(TileType.PATH, "path.png");
        this.mapTile.put(TileType.WATER, "water.png");
    }

    private void createEnemyMap() {
        this.mapEnemy.put(EnemyType.TANK, "tank.png"); // TODO Togliere png???

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
