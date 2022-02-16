package sgf.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import sgf.model.Tile;
import sgf.model.TileImpl;
import sgf.model.TileType;
/**
 * This class is responsible for the creation of all tile types.
 *
 */
public class TileController {
    private final List<Tile> tilesList;

    /**
     * Constructor that fill the array field with all types of tiles.
     */
    public TileController() {
        this.tilesList = new ArrayList<>();
        this.fillList();
    }

    /**
     * This method return the image of a given tile type.
     * @param type Represents the type of the image we want the sprite.
     * @return the BufferedImage of the specific type of tile.
     */
    public BufferedImage getImage(final int type) {
        return this.tilesList.get(type).getTileSprite();
    }

    private void fillList() {
        // To improve this method, instead of String paramether we could add in TileType the
        // types Grass and water, and pass a TileType as paramether and check in the method
        // loadRightImage using a switch, which png has to be loaded.

        // Other TODO, add paramethers Position and Direction for every Tile.
        final Tile grass = new TileImpl(TileType.OTHER, null, null, loadRightImage("grass.png"));
        final Tile sand = new TileImpl(TileType.OTHER, null, null, loadRightImage("sand.png"));
        final Tile water = new TileImpl(TileType.OTHER, null, null, loadRightImage("water.png"));
        this.tilesList.add(grass);
        this.tilesList.add(sand);
        this.tilesList.add(water);
    }

    // TODO Find a way to remove the return null.
    private BufferedImage loadRightImage(final String pngFile) {
        try {
            return ImageIO.read(new FileInputStream("res" + File.separator + pngFile));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }
}
