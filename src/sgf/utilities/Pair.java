package sgf.utilities;

/**
 * A standard generic Pair<X,Y>. 
 * @param <X> type of x. 
 * @param <Y> type of y.
 */
public class Pair<X, Y> {

    private final X x;
    private final Y y;

    /**
     * Sets the x and y field.
     * @param x number.
     * @param y number.
     */
    public Pair(final X x, final Y y) {
        super();
        this.x = x;
        this.y = y;
    }

    /**
     * @return value of x.
     */
    public X getX() {
        return x;
    }

    /**
     * @return value of y.
     */
    public Y getY() {
        return y;
    }

    /**
     * Returns a new instance of Pair.
     * @param <X> type of x.
     * @param <Y> type of y.
     * @param x value of x.
     * @param y value of y.
     * @return a new instance of pair.
     */
    public static <X, Y> Pair<X, Y> from(final X x, final Y y) {
        return new Pair<>(x, y);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((x == null) ? 0 : x.hashCode());
        result = prime * result + ((y == null) ? 0 : y.hashCode());
        return result;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true; 
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pair other = (Pair) obj;
        if (x == null) {
            if (other.x != null) {
                return false;
            }
        } else if (!x.equals(other.x)) {
            return false;
        }
        if (y == null) {
            if (other.y != null) {
                return false;
            }
        } else if (!y.equals(other.y)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
            return "Pair [x=" + x + ", y=" + y + "]";
    }
}
