package sgf.model;

/**
 *
 */
public class EnemyImpl implements Enemy {

    private Position position;
    private double hp;

    @Override
    public Position getPosition() {
        return this.position;
    }

    @Override
    public double getHP() {
        return this.hp;
    }

    @Override
    public double getCompletionPercentage() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double getSpeed() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void move() {
    }

}
