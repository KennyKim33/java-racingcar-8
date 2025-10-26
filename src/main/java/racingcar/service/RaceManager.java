package racingcar.service;

import racingcar.domain.Car;
import racingcar.dto.RaceResult;

import java.util.ArrayList;
import java.util.List;

public class RaceManager {
    private final RaceService raceService;
    private final int attemptCount;

    public RaceManager(RaceService raceService, int attemptCount) {
        this.raceService = raceService;
        this.attemptCount = attemptCount;
    }

    public RaceResult runRace() {
        List<List<Car>> roundHistory = new ArrayList<>();

        for(int i = 0; i < attemptCount; i++){
            roundHistory.add(raceService.playRound());
        }

        List<String> winners = findWinners(roundHistory);

        return new RaceResult(roundHistory, winners);
    }

    private List<String> findWinners(List<List<Car>> raceHistory) {
        List<Car> finalRound = raceHistory.getLast();
        int maxPosition = getMaxPosition(finalRound);
        return finalRound.stream()
                .filter(car -> car.getPosition() == maxPosition)
                .map(Car::getName).toList();
    }

    private int getMaxPosition(List<Car> finalRound) {
        return finalRound.stream().mapToInt(Car::getPosition).max().orElse(0);
    }
}
