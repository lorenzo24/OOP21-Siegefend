package sgf.model.game;


/**
 * Class that managed the classification.
 */
public class ClassificationImpl implements Classification {
    private int actualScore = 0;
    private final Map<Date, Integer> listScore = new HashMap<>();

    @Override
    public void updateScore(final int score) {
        this.actualScore += score;
    }

    @Override
    public void writeScore() {
        
    }

}
