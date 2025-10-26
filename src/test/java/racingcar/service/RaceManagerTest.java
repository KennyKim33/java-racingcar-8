package racingcar.service;


import org.junit.jupiter.api.Test;
import racingcar.domain.Car;
import racingcar.domain.MovingStrategy;
import racingcar.dto.RaceResult;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class RaceManagerTest {
    @Test
    void 지정된_횟수만큼_라운드를_진행한다() {

        MovingStrategy alwaysMove = () -> true;
        List<Car> cars = List.of(
                new Car("pobi", alwaysMove),
                new Car("woni", alwaysMove)
        );
        RaceService raceService = new RaceService(cars);
        RaceManager raceManager = new RaceManager(raceService, 5);

        RaceResult result = raceManager.runRace();

        assertThat(result.getRoundHistory()).hasSize(5);
    }

    @Test
    void 각_라운드마다_자동차_상태가_저장된다() {

        MovingStrategy alwaysMove = () -> true;
        List<Car> cars = List.of(new Car("pobi", alwaysMove));
        RaceService raceService = new RaceService(cars);
        RaceManager raceManager = new RaceManager(raceService, 3);

        RaceResult result = raceManager.runRace();

        List<List<Car>> history = result.getRoundHistory();

        assertThat(history.get(0).get(0).getPosition()).isEqualTo(1);
        assertThat(history.get(1).get(0).getPosition()).isEqualTo(2);
        assertThat(history.get(2).get(0).getPosition()).isEqualTo(3);
    }

    @Test
    void 우승자가_1명인_경우_우승자를_반환한다() {
        // given
        MovingStrategy alwaysMove = () -> true;
        MovingStrategy neverMove = () -> false;
        List<Car> cars = List.of(
                new Car("pobi", alwaysMove),
                new Car("crong", neverMove)
        );
        RaceService raceService = new RaceService(cars);
        RaceManager raceManager = new RaceManager(raceService, 3);

        RaceResult result = raceManager.runRace();

        assertThat(result.getWinners()).containsExactly("pobi");
    }

    @Test
    void 우승자가_여러_명인_경우_모든_우승자를_반환한다() {

        MovingStrategy alwaysMove = () -> true;
        List<Car> cars = List.of(
                new Car("pobi", alwaysMove),
                new Car("crong", alwaysMove),
                new Car("honux", alwaysMove)
        );

        RaceService raceService = new RaceService(cars);
        RaceManager raceManager = new RaceManager(raceService, 3);

        RaceResult result = raceManager.runRace();

        assertThat(result.getWinners())
                .hasSize(3)
                .containsExactly("pobi", "crong", "honux");
    }

    @Test
    void 모두_이동하지_않았더라도_우승자는_존재한다() {
        // given
        MovingStrategy neverMove = () -> false;
        List<Car> cars = List.of(
                new Car("pobi", neverMove),
                new Car("crong", neverMove)
        );
        RaceService raceService = new RaceService(cars);
        RaceManager raceManager = new RaceManager(raceService, 3);

        RaceResult result = raceManager.runRace();

        assertThat(result.getWinners())
                .containsExactly("pobi", "crong");

        assertThat(result.getRoundHistory().getLast())
                .allMatch(car -> car.getPosition() == 0);
    }

}