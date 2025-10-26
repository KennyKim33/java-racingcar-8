package racingcar.domain;

public class RandomMovingStrategy implements MovingStrategy{
    private final static int MOVE_THRESHOLD = 4;
    private final NumberGenerator numberGenerator;

    public RandomMovingStrategy(NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }

    @Override
    public boolean shouldMove() {
        return numberGenerator.generate() >= MOVE_THRESHOLD;
    }
}
