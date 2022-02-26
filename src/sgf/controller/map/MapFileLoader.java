package sgf.controller.map;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import sgf.model.GridPosition;
import sgf.model.Map;
import sgf.model.MapImpl;
import sgf.model.TileImpl;
import sgf.model.TileType;

/**
 * This is a class of utility that reads a matrix from file which represents the map structure. 
 * After that, it can fill correctly the map implementation field that links every grid position to the correspondent tile..
 */
public class MapFileLoader {
    private final Map map;

    /**
     * Simple constructor.
     * @param levelId Denotes, according to the level, which map file has to be loaded.
     */
    public MapFileLoader(final int levelId) {
        this.map = new MapImpl();
        this.readMapStructureFromFile(levelId); // Methods that reads map structure from file and create the map.
    }

    /**
     * Simple getter for the field map.
     * @return the field of type map.
     */
    public Map getMap() {
        return this.map;
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
                        this.addNewElement(Integer.parseInt(splitted[column]), row, column);
                    }
                }
            }
        } catch (IOException e1) {
                e1.printStackTrace();
        }
    }

    // This method checks the parameter in input and puts the correct tile type into the map.
    private void addNewElement(final int tileType, final int row, final int column) {
        switch (tileType) {
            case 0:     // If reads a 0, the tile is a grass type tile..
                this.map.getTiles().put(new GridPosition(column, row), new TileImpl(TileType.GRASS, null));
                break;
            case 2:     // If reads a 2, the tile is a water type tile.
                this.map.getTiles().put(new GridPosition(column, row), new TileImpl(TileType.WATER, null));
                break;
            case 3:     // If reads a 3, the tile is the start of enemies movement.
                this.map.getTiles().put(new GridPosition(column, row), new TileImpl(TileType.START_PATH, null));
                this.map.setStartTile(row, column);
                break;
            case 4:     // If reads a 4, the tile is the end of enemies movement.
                this.map.getTiles().put(new GridPosition(column, row), new TileImpl(TileType.END_PATH, null));
                this.map.setEndTile(row, column);     // If there, the tile is the start of enemy's movement.
                break;
            default:    // If there, the case is 1 and the tile type is path.
                this.map.getTiles().put(new GridPosition(column, row), new TileImpl(TileType.PATH, null));
                break;
        }
    }
}
