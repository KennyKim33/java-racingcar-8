package racingcar.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RacingCarFactoryTest {

    @Test
    void 자동차_배열이_정상적으로_생성된다() {

        MovingStrategy movingStrategy = () -> true;

        List<Car> expected = List.of(
                new Car("pobi",movingStrategy),
                new Car("woni",movingStrategy),
                new Car("jun",movingStrategy)
        );

        String input = "pobi,woni,jun";
        List<Car> actual = RacingCarFactory.from(input, movingStrategy);

        assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }
}