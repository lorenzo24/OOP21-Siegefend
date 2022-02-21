package sgf.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * This class represents a simple map logic with its structure.
 */
public class MapImpl implements Map {
    private final java.util.Map<GridPosition, Tile> tiles;
    private final int[][] mapStructure;
    private final int matrixSize;

    /**
     * Constructor that reads int matrix from file and converts it into a tile matrix.
     * @param levelId Is the level whose map has to be loaded.
     */
    public MapImpl(final int levelId) {
        this.tiles = new HashMap<>();
        this.mapStructure = this.readMapStructureFromFile(levelId);     // This methods reads the int matrix from file.
        this.matrixSize = this.mapStructure.length;     // Once read from file, the matrix size is known.
        this.convertMatrix();   // This method converts the field matrix into a matrix of type Tile .
    }

    @Override
    public int getMatrixSize() {
        return this.matrixSize;
    }

    @Override
    public Tile getTileFromGridPosition(final GridPosition position) {
        return this.tiles.get(position);
    }

    @Override
    public Tile getTileFromPosition(final Position position) {  // TODO Maybe to be deleted?
        // TODO Auto-generated method stub
        return null;
    }

    private int[][] readMapStructureFromFile(final int levelId) {
        int[][] resultedMatrix;
        String lineRead;
        try (BufferedReader reader = new BufferedReader(new FileReader("res" + File.separator + "mapLevel" + levelId + ".txt"))) {
            final int matrixSize = Integer.valueOf(reader.readLine());  // The first line of the file contains the matrix size.
            resultedMatrix = new int [matrixSize][matrixSize];
            // Read all the lines and the extract by splitting all values and insert them into the resulted matrix.
            for (int row = 0; row < matrixSize; row++) {
                lineRead = reader.readLine();
                final String[] splited = lineRead.split("\\s+");
                for (int column = 0; column < matrixSize; column++) {
                    resultedMatrix[row][column] = Integer.valueOf(splited[column]);
                }
            }
            return resultedMatrix;
        } catch (IOException e1) {
                e1.printStackTrace();
                return null;
        }
    }

    private void convertMatrix() {
        for (int row = 0; row < this.mapStructure.length; row++) {
            for (int column = 0; column < this.mapStructure.length; column++) {
                switch (this.mapStructure[row][column]) {
                    case 0:
                        // TODO Ask Giacomo how to fill the Position argument. Maybe through row and column?
                        this.tiles.put(new GridPosition(column, row), new TileImpl(TileType.GRASS, null));
                        break;
                    case 2:
                        this.tiles.put(new GridPosition(column, row), new TileImpl(TileType.WATER, null));
                        break;
                    default:    // If there, the case is 1 and the tile is path.
                        this.tiles.put(new GridPosition(column, row), new TileImpl(TileType.PATH, null));
                        break;
                }
            }
        }
    }



}
