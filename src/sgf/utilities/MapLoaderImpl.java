package sgf.utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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
 * After that, it can fill correctly the map implementation field that links every grid position to the correspondent tile..
 */
public class MapLoaderImpl implements MapLoader {
    private final Map map;
    private final java.util.Map<Integer, TileType> numersToTypes;
    // Two boolean variable to check if the given map has a start and a end.
    private boolean isSetStart;
    private boolean isSetEnd;

    /**
     * Simple constructor.
     * @param levelId Denotes, according to the level, which map file has to be loaded.
     */
    public MapLoaderImpl(final int levelId) {
        this.map = new MapImpl();
        numersToTypes = new HashMap<>();
        this.createLinks();
        this.readMapStructureFromFile(levelId); // Methods that reads map structure from file and create the map.
        this.findMovementPath();        // Method that fills the field Direction in every tile.
    }

    @Override
    public Map getMap() {
        return this.map;
    }

    // Method that links every integer number with a correspondent tile type.
    private void createLinks() {
        this.numersToTypes.put(0, TileType.GRASS);
        this.numersToTypes.put(1, TileType.PATH);
        this.numersToTypes.put(2, TileType.WATER);
        this.numersToTypes.put(3, TileType.START_PATH);
        this.numersToTypes.put(4, TileType.END_PATH);
    }

    // This method is the file effective reader.
    private void readMapStructureFromFile(final int levelId) {
        String lineRead;
        // The method start trying to open the map file with the structure.
        try (BufferedReader reader = new BufferedReader(new FileReader("res" + File.separator + "mapLevel" + levelId + ".txt"))) {
            this.map.setMapSize(Integer.parseInt(reader.readLine()));   // The first line of the file denotes the grid size.

            // Read all the next lines.
            for (int row = 0; row < this.map.getMapSize(); row++) {
                lineRead = reader.readLine();
                if (lineRead != null) {
                    // Each line has to be splitted by space in order to obtain all the single values.
                    final String[] splitted = lineRead.split("\\s+");
                    for (int column = 0; column < splitted.length; column++) {
                        // At the end, the method adds a new element (GridPosition and correspondent Tile) into the effective map.
                        final int valueRead = Integer.parseInt(splitted[column]);
                        if (valueRead == 3) {
                            this.map.setStartTile(row, column);
                            this.isSetStart = true;
                        } else if (valueRead == 4) {
                            this.map.setEndTile(row, column);
                            this.isSetEnd = true;
                        }
                        this.map.getTiles().put(new GridPosition(row, column), new TileImpl(this.numersToTypes.get(valueRead)));
                    }
                }
            }
        } catch (IOException e1) {
                e1.printStackTrace();
        }
        if (!this.isSetStart || !this.isSetEnd) {
            throw new IllegalStateException("Given matrix has no start or end tile!");
        }
    }

    // Method that, by observing the path, fill the field Direction of the path tiles.
    private void findMovementPath() {
        GridPosition currentTile = this.map.getStartTile();
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

    // Method that calculate the neighbors of a given grid position and fill a map with the corresponding direction.
    private java.util.Map<GridPosition, Direction> findNeighbors(final GridPosition actualTile) {
        final java.util.Map<GridPosition, Direction> result = new HashMap<>();
        result.put(new GridPosition(actualTile.getRow() - 1, actualTile.getColumn()), Direction.UP);
        result.put(new GridPosition(actualTile.getRow(), actualTile.getColumn() + 1), Direction.RIGHT);
        result.put(new GridPosition(actualTile.getRow() + 1, actualTile.getColumn()), Direction.DOWN);
        result.put(new GridPosition(actualTile.getRow(), actualTile.getColumn() - 1), Direction.LEFT);
        return result;
    }

    // Method that check if a given grid position is acceptable (in matrix size limits) and if it corresponds to a path tile.
    private boolean isPath(final GridPosition neighbour) {
        if (neighbour.getRow() < 0 || neighbour.getColumn() < 0 || neighbour.getRow() >= this.map.getMapSize() 
                || neighbour.getColumn() >= this.map.getMapSize()) {    // If given grid position not into the map's size limits.
            return false;
        } else {
            // We check if its type is path.
            return this.map.getTiles().get(neighbour).getTileType() != TileType.WATER
                    && this.map.getTiles().get(neighbour).getTileType() != TileType.GRASS;
        }
    }
}
