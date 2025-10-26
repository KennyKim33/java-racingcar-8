package racingcar.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class RandomMovingStrategyTest {
    private MovingStrategy randomMovingStrategy;

    @Test
    void 생성된_숫자가_기준값보다_작으면_false를_반환한다() {
        NumberGenerator numberGenerator = () -> 3;
        randomMovingStrategy = new RandomMovingStrategy(numberGenerator);

        boolean result = randomMovingStrategy.shouldMove();

        assertThat(result).isFalse();
    }

    @Test
    void 생성된_숫자가_기준값과_크거나_같으면_true를_반환한다() {
        NumberGenerator numberGenerator = () -> 4;
        randomMovingStrategy = new RandomMovingStrategy(numberGenerator);

        boolean result = randomMovingStrategy.shouldMove();

        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {
            "2, false",
            "4, true",
            "7, true"
    })
    void 생성된_숫자에_따라_이동_여부를_반환한다(int number, boolean expected) {
        NumberGenerator numberGenerator = () -> number;
        randomMovingStrategy = new RandomMovingStrategy(numberGenerator);

        boolean result = randomMovingStrategy.shouldMove();

        assertThat(result).isEqualTo(expected);
    }

}