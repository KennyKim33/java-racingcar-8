package racingcar.controller;

import racingcar.domain.*;
import racingcar.dto.RaceResult;
import racingcar.service.RaceManager;
import racingcar.service.RaceService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.List;

public class RaceGameController {
    private final InputView inputView;
    private final OutputView outputView;
    private final MovingStrategy movingStrategy;

    public RaceGameController(InputView inputView, OutputView outputView, MovingStrategy movingStrategy) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.movingStrategy = movingStrategy;
    }

    public static RaceGameController create() {
        return new RaceGameController(
                new InputView(),
                new OutputView(),
                new RandomMovingStrategy(new RandomNumberGenerator())
        );
    }

    public void run() {
        List<Car> cars = createCars();
        int attemptCount = getAttemptCount();

        RaceResult result = executeRace(cars, attemptCount);

        displayResult(result);
    }

    private List<Car> createCars() {
        String input = inputView.inputCarNames();
        return RacingCarFactory.from(input, movingStrategy);
    }

    private int getAttemptCount() {
        String input = inputView.inputAttemptCount();
        return Integer.parseInt(input);
    }

    private RaceResult executeRace(List<Car> cars, int count) {
        RaceService raceService = new RaceService(cars);
        RaceManager raceManager = new RaceManager(raceService, count);
        return raceManager.runRace();
    }

    private void displayResult(RaceResult result) {
        outputView.printRoundHistory(result.getRoundHistory());
        outputView.printWinners(result.getWinners());
    }

}
