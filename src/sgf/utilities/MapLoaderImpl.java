package sgf.utilities;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import sgf.model.Direction;
import sgf.model.Map;
import sgf.model.MapImpl;
import sgf.model.GridPosition;
import sgf.model.TileImpl;
import sgf.model.TileType;

/**
 * This is a class of utility that reads a matrix from file which represents the map structure. 
 * After that, it can fill correctly the map implementation field that links every grid position to the correspondent tile.
 */
public class MapLoaderImpl implements MapLoader {
    private final Map map;
    private final java.util.Map<Integer, TileType> numbersToTypes;
    private int mapRows;        // At the end of the reading it will be the map size.
    // Two boolean variable to check if the given map has a start and a end.
    private boolean isSetStart;
    private boolean isSetEnd;

    /**
     * Simple constructor.
     * @param levelId Denotes, according to the level, which map file has to be loaded.
     */
    public MapLoaderImpl(final int levelId) {
        this.map = new MapImpl();
        numbersToTypes = new HashMap<>();
        this.mapRows = 0;
        this.createLinks();
        this.readMapStructureFromFile(levelId); // Methods that reads map structure from file and create the correspondent map.
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
        final String file = "res" + File.separator + "mapLevel" + levelId + ".txt";
        final Path p = FileSystems.getDefault().getPath(file);
        try {
            Files.lines(p).forEach(s -> read(s));
            this.map.setMapSize(this.mapRows);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!this.isSetStart || !this.isSetEnd) {       // If the file .txt has no 3 or 4 (start or end tile).
            throw new IllegalStateException("Given matrix has no start or end tile!");
        }
    }

    // Method that works on every row read from file to obtain the effective values and convert them into tiles.
    private void read(final String text) {
        final List<String> lineRead = Arrays.asList(text.split("\\s+"));
        int column = 0;
        for (final String element : lineRead) {
            final int value = Integer.parseInt(element);
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
        GridPosition currentTile = this.map.getStartTile();     // This algorithms starts from the start of the path.
        final Set<GridPosition> tilesAlreadyChecked = new HashSet<>();    // Contains the tiles already checked, so that we can ignore them.
        Direction lastDirection = null; // It saves the last direction set, so we can have the direction also for the very last tile of the path.

        // For every tile of the path we check its neighbors to find which one is path. Then, by a simple compare we can set up the field direction of ebery path tile.
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

    // Method that checks if a given grid position is acceptable (in matrix size limits) and if it corresponds to a path tile.
    private boolean isPath(final GridPosition neighbour) {
        final int row = neighbour.getRow();
        final int column = neighbour.getColumn();

        // If given grid position isn't into the map's size limits.
        if (row < 0 || column < 0 || row >= this.map.getMapSize() || column >= this.map.getMapSize()) {
            return false;
        } else {
            // Checks if its type is path.
            final TileType type = this.map.getTiles().get(neighbour).getTileType();
            return type != TileType.WATER && type != TileType.GRASS;
        }
    }
}
