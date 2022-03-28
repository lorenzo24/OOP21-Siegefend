package sgf.helpers;

/**
 *  Class that has the dimension of a single images (tile image).
 */
public final class ImgTileSize {

    private static final int CELL_SIZE = 80;

    private ImgTileSize() { }

    /**
     * The image size.
     * @return image size.
     */
    public static int getTileSize() {
        return CELL_SIZE;
    }
}
