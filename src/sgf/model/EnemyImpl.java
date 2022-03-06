package sgf.model;

/**
 * This class represents the implementation of the interface Enemy.
 */
public class EnemyImpl implements Enemy {
    private final int iD;       // Still not used.
    private Position position;
    private double hp;
    private double speed;
    private final EnemyType enemyType;
    private boolean win; // To be implemented soon.

    /**
     * Constructor for an enemy.
     * @param iD Is the identifier for the enemy.
     * @param position Is the position in pixel occupied by the enemy.
     * @param hp Is the enemy's health.
     * @param speed Is the movement speed parameter.
     * @param enemyType Denotes the type of the enemy.
     */
    public EnemyImpl(final int iD, final Position position, final double hp, final double speed, final EnemyType enemyType) {
        this.iD = iD;
        this.position = position;
        this.hp = hp;
        this.speed = speed;
        this.enemyType = enemyType;
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    private void setPosition(final double x, final double y) {
        this.position = new Position(x, y);
    }

    @Override
    public double getHP() {
        return this.hp;
    }

    @Override
    public boolean isWin() {
        return this.win;
    }

    @Override
    public void setWin(final boolean win) {
        this.win = win;
    }

    @Override
    public double getSpeed() {
        return this.speed;
    }

    @Override
    public void move(final double x, final double y) {
        this.setPosition(x, y); 
    }

    @Override
    public EnemyType getEnemyType() {
        return this.enemyType;
    }

    @Override
    public int getID() {
        return this.iD;
    }

    /**
     * Class to create a new Enemy.
     */
    public static class Builder {
        private static final double DEFAULT_NUMBER = 100;
        private final int iD;       // Still not used.
        private Position position;
        private double hp = DEFAULT_NUMBER;
        private double speed = DEFAULT_NUMBER;
        private final EnemyType enemyType;

        /**
         * Set the principal not otional atribute.
         * @param iD Number to identify.
         * @param enemyType Is the type of an enemy.
         */
        public Builder(final int iD, final EnemyType enemyType) {
            this.iD = iD;
            this.enemyType = enemyType;
        }

        /**
         * Set the position of an enemy.
         * @param position Is the position on the map.
         * @return the builder. 
         */
        public Builder position(final Position position) {
            this.position = position;
            return this;
        }

        /**
         * Set the life of an enemy.
         * @param life Is the life of an enemy.
         * @return the builder. 
         */
        public Builder hp(final double life) {
            this.hp = life;
            return this;
        }

        /**
         * Set the speed of an enemy.
         * @param fast Is the speed of one enemy.
         * @return the builder. 
         */
        public Builder speed(final double fast) {
            this.speed = fast;
            return this;
        }

        /**
         * Create the complete enemy.
         * @return The enemy enetity.
         */
        public Enemy build() {
            if (this.position == null || this.enemyType == null) {
                throw new IllegalStateException();
            }
            return new EnemyImpl(this.iD, this.position, this.hp, this.speed, this.enemyType);
        }
    }

}
