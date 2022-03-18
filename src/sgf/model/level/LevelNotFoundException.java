package sgf.model.level;

/**
 * Thrown to indicate that a certain level was not found and could not be loaded.
 */
public class LevelNotFoundException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Creates an empty new instance of {@code LevelNotFoundException}.
     */
    public LevelNotFoundException() {
        super();
    }

    /**
     * Creates a new instance of {@code LevelNotFoundException} with the given message.
     * @param message The message of the exception.
     */
    public LevelNotFoundException(final String message) {
        super(message);
    }
}
