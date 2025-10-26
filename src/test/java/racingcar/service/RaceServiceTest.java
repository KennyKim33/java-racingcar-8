package racingcar.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import racingcar.domain.Car;
import racingcar.domain.MovingStrategy;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RaceServiceTest {
    private RaceService raceService;

    @Test
    void 라운드_진행_후_이동_로직에_따라_움직인다() {
        MovingStrategy movingStrategy = () -> true;
        List<Car> cars = List.of(
                new Car("pobi", movingStrategy),
                new Car("woni", movingStrategy),
                new Car("jun", movingStrategy)
        );

        raceService = new RaceService(cars);

        List<Car> result = raceService.playRound();

        assertThat(result).hasSize(3);
        assertThat(result).allMatch(car -> car.getPosition() == 1);
    }

    @Test
    void 라운드_진행_후_이동_로직에_따라_전진_혹은_멈춘다() {
        List<Car> cars = List.of(
                new Car("pobi", () -> false),
                new Car("woni", () -> true)
        );

        raceService = new RaceService(cars);

        List<Car> result = raceService.playRound();

        assertThat(result).hasSize(2);
        assertThat(result.get(0).getPosition()).isEqualTo(0);
        assertThat(result.get(1).getPosition()).isEqualTo(1);
    }

    @Test
    void 라운드_진행_후_위치를_제외하고선_변하지_않는다() {
        MovingStrategy movingStrategy = () -> true;
        List<Car> cars = List.of(
                new Car("pobi", movingStrategy),
                new Car("woni", movingStrategy),
                new Car("jun", movingStrategy)
        );

        raceService = new RaceService(cars);
        List<Car> actual = raceService.playRound();

        List<Car> expected = List.of(
                new Car("pobi", movingStrategy),
                new Car("woni", movingStrategy),
                new Car("jun", movingStrategy)
        );

        assertThat(actual).usingRecursiveComparison()
                .ignoringFields("position")
                .isEqualTo(expected);
    }

    @Test
    void 불변_리스트를_반환한다() {
        MovingStrategy movingStrategy = () -> true;
        List<Car> cars = List.of(
                new Car("car1", movingStrategy),
                new Car("car2", movingStrategy),
                new Car("car3", movingStrategy)
        );

        raceService = new RaceService(cars);

        List<Car> result = raceService.playRound();

        assertThat(result).isUnmodifiable();
    }
}