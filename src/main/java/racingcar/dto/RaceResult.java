package racingcar.dto;

import racingcar.domain.Car;

import java.util.List;

public class RaceResult {
    private final List<List<Car>> roundResults;
    private final List<String> winners;

    public RaceResult(List<List<Car>> roundResults, List<String> winners) {
        this.roundResults = List.copyOf(roundResults);
        this.winners = List.copyOf(winners);
    }

    public List<List<Car>> getRoundResults() {
        return roundResults;
    }

    public List<String> getWinners() {
        return winners;
    }
}
