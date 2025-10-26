package racingcar.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RandomNumberGeneratorTest {
    private NumberGenerator numberGenerator;

    @BeforeEach
    void setUp() {
        numberGenerator = new RandomNumberGenerator();
    }

    @Test
    void 숫자_생성의_범위는_0부터_9까지다() {

        for(int i = 0; i < 100; i++) {
            int result = numberGenerator.generate();
            assertThat(result).isBetween(0,9);
        }
    }
}