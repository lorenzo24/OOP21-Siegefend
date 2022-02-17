package sgf.controller;

import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import sgf.model.Tile;
import sgf.model.TileImpl;
import sgf.model.TileType;

/**
 * This class is responsible for the creation of all tile types.
 *
 */
public class TileController {
    private final List<Tile> tilesList;
    private final List<Tile> cacheTilesList;    // This is a copy useful for a more performed way to load images.

    /**
     * Constructor that fills the arrays with all types of tiles.
     */
    public TileController() {
        this.tilesList = new ArrayList<>();
        this.cacheTilesList = new ArrayList<>();
        this.fillBothLists();
    }

    /**
     * This method returns the image of a given tile type.
     * @param type Represents the type of the image we want the sprite.
     * @return the image of the specific type of tile.
     */
    public Image getImage(final int type) {
        return this.tilesList.get(type).getTileSprite();
    }

    /**
     * This method is called after a window resizing and manages to the resizing of the tiles.
     * @param newWidth Is the new width that the image must have.
     * @param newHeight Is the new height that the image must have.
     */
    public void correctImagesSize(final int newWidth, final int newHeight) {
        // Every tile in original array updates its field containing the image with the correspondent copy in cache array.
        for (int i = 0; i < this.tilesList.size(); i++) {
            final Image newResizedImage = this.cacheTilesList.get(i).getTileSprite().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH); 
            this.tilesList.get(i).setTileSprite(newResizedImage);
        }
    }

    private void fillBothLists() {
        // TODO improve this method, instead of String paramether we could add in TileType the
        // types Grass and water, and pass a TileType as paramether and check in the method
        // loadRightImage using a switch, which png has to be loaded.
        // Other TODO, add paramethers Position and Direction for every Tile.
        this.fillList(tilesList);
        this.fillList(cacheTilesList);
    }

    // TODO Find a way to remove the return null.
    private Image loadRightImage(final String pngFile) {
        return Toolkit.getDefaultToolkit().createImage("res" + File.separator + pngFile);
    }

    private void fillList(final List<Tile> list) {
        list.add(new TileImpl(TileType.BUILD, null, null, loadRightImage("grass.png")));
        list.add(new TileImpl(TileType.PATH, null, null, loadRightImage("sand.png")));
        list.add(new TileImpl(TileType.NO_BUILD, null, null, loadRightImage("water.png")));
    }
}
