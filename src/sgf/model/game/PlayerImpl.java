package sgf.model.game;


/**
 * 
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
    private int currentHP;
    private int money;
    //private int score;

    /**
     * Default constructor, sets fields at the starting value.
     */
    public PlayerImpl() {
        super();
        this.currentHP = MAX_HP;
        this.money = STARTING_MONEY;
        this.score = 0;
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
        if (hp <= MAX_HP && hp > 0) {
            this.currentHP = hp;
        }
    }

    @Override
    public int getCurrentMoney() {
        return this.money;
    }

    @Override
    public void setCurrentMoney(final int money) {
       if (money >= 0) {
           this.money = money;
       }
    }

}
