package racingcar.service;

import racingcar.domain.Car;

import java.util.List;

public class RaceService {
    private final List<Car> racingCars;

    public RaceService(List<Car> racingCars) {
        this.racingCars = racingCars;
    }

    public List<Car> playRound() {
        racingCars.forEach(Car::move);
        return racingCars.stream()
                .map(Car::copy)
                .toList();
    }
}
