package sgf.helpers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import sgf.model.map.Direction;
import sgf.model.map.GridPosition;
import sgf.model.map.Map;
import sgf.model.map.MapImpl;
import sgf.model.map.TileImpl;
import sgf.model.map.TileType;

/**
 * This is a class of utility that reads a matrix from file which represents the {@link Map} structure. 
 * After that, it can fill correctly the map implementation field that links every {@link GridPosition} to the correspondent {@link Tile}.
 */
public class MapLoaderImpl implements MapLoader {
    private final Map map;
    private final java.util.Map<Integer, TileType> numbersToTypes;
    private int mapRows;        // When reading over, it will contain the map size.

    // Boolean variables to check the given map integrity (has start tile and end tile).
    private boolean isSetStart;
    private boolean isSetEnd;

    /**
     * Simple constructor that initializes the fields.
     * @param levelId Denotes, according to the {@link Level}, which map file has to be loaded.
     */
    public MapLoaderImpl(final int levelId) {
        this.map = new MapImpl();
        numbersToTypes = new HashMap<>();
        this.mapRows = 0;
        this.createLinks();
        this.readMapStructureFromFile(levelId); // Method that reads map structure from file and create the correspondent map.
        this.findMovementPath();        // Method that fills the field Direction in every tile.
    }

    @Override
    public Map getMap() {
        return this.map;
    }

    // Method that links every integer number with the correspondent tile type.
    private void createLinks() {
        this.numbersToTypes.put(0, TileType.GRASS);
        this.numbersToTypes.put(1, TileType.PATH);
        this.numbersToTypes.put(2, TileType.WATER);
        this.numbersToTypes.put(3, TileType.START_PATH);
        this.numbersToTypes.put(4, TileType.END_PATH);
    }

    // This method is the file effective reader.
    private void readMapStructureFromFile(final int levelId) {
        final String file;
        if (levelId > 0) {      // If parameter > 0 it loads an actual level, not a test (a map with error).
            file = "maps/mapLevel" + levelId + ".txt";
        } else {        // If parameter <= 0 it loads a map with error to test it, so it must change folder.
            file = "tests/mapLevel" + levelId + ".txt";
        }
        //      final Path p = FileSystems.getDefault().getPath(file);
        try (InputStream is = ClassLoader.getSystemResourceAsStream(file)) {
            try (BufferedReader r = new BufferedReader(new InputStreamReader(is))) {
                r.lines().forEach(s -> read(s));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.map.setMapSize(this.mapRows);
        if (!this.isSetStart || !this.isSetEnd) {       // If the file .txt has no 3 or 4 (start or end tile).
            throw new IllegalStateException("Given matrix has no start or end tile!");
        }
        final long numberOfPathTiles = this.map.getTiles().values().stream().filter(x -> x.getTileType().equals(TileType.PATH)).count();
        if (numberOfPathTiles == 0) {
            throw new IllegalStateException("Given matrix has no path!");
        }
    }

    // Method that works on every row read from file to obtain the effective values and convert them into tiles.
    private void read(final String text) {
        final List<String> lineRead = Arrays.asList(text.split("\\s+"));
        int column = 0;
        for (final String element : lineRead) {
            final int value = Integer.parseInt(element);
            if (value > 4) {
                throw new IllegalArgumentException("Incorrect number read in map file!");   // Check number read consistency.
            }
            if (value == 3) {
                this.map.setStartTile(mapRows, column);
                this.isSetStart = true;
            } else if (value == 4) {
                this.map.setEndTile(mapRows, column);
                this.isSetEnd = true;
            }
            this.map.getTiles().put(new GridPosition(mapRows, column), new TileImpl(this.numbersToTypes.get(value)));
            column++;
        }
        this.mapRows++;
    }

    // Method that, by observing the path, fill the field Direction of every tiles in the path.
    private void findMovementPath() {
        GridPosition currentTile = this.map.getStartTile();     // This algorithm starts from the start of the path.
        final Set<GridPosition> tilesAlreadyChecked = new HashSet<>();    // Contains the tiles already checked, so that we can ignore them.
        Direction lastDirection = null; // It saves the last direction set, so we can have the direction also for the very last tile of the path.

        // For every tile of the path we check its neighbors to find which one is path. Then, by a simple compare we can set up the field direction of every path tile.
        while (!currentTile.equals(this.map.getEndTile())) {
            for (final var neighbor : this.findNeighbors(currentTile).entrySet()) {
                // For every neighbor tile that has not been already checked we check if its type is Path.
                if (!tilesAlreadyChecked.contains(neighbor.getKey()) && this.isPath(neighbor.getKey())) {
                    // We found the next tile. So we have to set the direction of the current tile and update the list of already checked tiles.
                    this.map.getTiles().get(currentTile).setDirection(neighbor.getValue());
                    tilesAlreadyChecked.add(currentTile);
                    currentTile = neighbor.getKey();
                    lastDirection = neighbor.getValue();
                    break;
                }
            }
        }
        this.map.getTiles().get(this.map.getEndTile()).setDirection(lastDirection);     // Also the very last tile direction is set.
    }

    // Method that calculates the neighbors of a given grid position and fill a map with the corresponding direction.
    private java.util.Map<GridPosition, Direction> findNeighbors(final GridPosition actualTile) {
        final int row = actualTile.getRow();
        final int column = actualTile.getColumn();
        final java.util.Map<GridPosition, Direction> result = new HashMap<>();
        result.put(new GridPosition(row - 1, column), Direction.UP);
        result.put(new GridPosition(row, column + 1), Direction.RIGHT);
        result.put(new GridPosition(row + 1, column), Direction.DOWN);
        result.put(new GridPosition(row, column - 1), Direction.LEFT);
        return result;
    }

    // Method that checks if a given grid position is acceptable (if is into matrix size limits) and if it corresponds to a path tile.
    private boolean isPath(final GridPosition neighbour) {
        final int row = neighbour.getRow();
        final int column = neighbour.getColumn();

        // If given grid position isn't into the map's size limits.
        if (row < 0 || column < 0 || row >= this.map.getSize() || column >= this.map.getSize()) {
            return false;
        } else {
            // Checks if its type is path.
            final TileType type = this.map.getTiles().get(neighbour).getTileType();
            return type != TileType.WATER && type != TileType.GRASS;
        }
    }
}
