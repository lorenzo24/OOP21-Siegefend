package sgf.model.game;


/**
 * 
 */
public class PlayerImpl implements Player {
    /**
     * Indicates Player's Max health.
     */
    public static final int MAX_HP = 20;
    /**
     * Indicates the amount of money the player starts with.
     */
    public static final int STARTING_MONEY = 500;       //TODO: set this so that the player can buy at least 1 turret.
    /**
     * How many HP the player looses by default each time he takes damage.
     */
    public static final int HP_REDUCTION_STEP = 1;
    /**
     * The Score the player starts with.
     */
    public static final int STARTING_SCORE = 0;
    private int currentHP;
    private int money;
    private int score;

    /**
     * Default constructor, sets fields at the starting value.
     */
    public PlayerImpl() {
        super();
        this.currentHP = MAX_HP;
        this.money = STARTING_MONEY;
        this.score = STARTING_SCORE;
    }

    @Override
    public int getMaxHP() {
        return MAX_HP;
    }

    @Override
    public int getCurrentHP() {
        return this.currentHP;
    }

    @Override
    public void setCurrentHP(final int hp) {
        if (hp <= MAX_HP && hp >= 0) {
            this.currentHP = hp;
        }
    }

    @Override
    public int getMoney() {
        return this.money;
    }

    @Override
    public void setMoney(final int money) {
       if (money >= 0) {
           this.money = money;
       }
    }

    @Override
    public void decreaseCurrentHP() {                           //These two might be useless.
        decreaseCurrentHP(HP_REDUCTION_STEP);
    }

    @Override
    public void decreaseCurrentHP(final int amount) {           //These two might be useless.
        if (this.currentHP >= amount) {
            this.currentHP -= amount;
        }
    }

    @Override
    public int getScore() {
        return this.score;
    }

    @Override
    public void setScore(final int score) {
        if (score >= 0) {
            this.score = score;
        }
    }
}
