package racingcar.dto;

import racingcar.domain.Car;

import java.util.List;

public class RaceResult {
    private final List<List<Car>> roundHistory;
    private final List<String> winners;

    public RaceResult(List<List<Car>> roundHistory, List<String> winners) {
        this.roundHistory = List.copyOf(roundHistory);
        this.winners = List.copyOf(winners);
    }

    public List<List<Car>> getRoundHistory() {
        return roundHistory;
    }

    public List<String> getWinners() {
        return winners;
    }
}
