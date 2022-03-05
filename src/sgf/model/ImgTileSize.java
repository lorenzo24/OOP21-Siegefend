package sgf.model;

/**
 *  Class that have the dimension of the single images.
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
