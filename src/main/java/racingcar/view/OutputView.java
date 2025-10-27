package racingcar.view;

import racingcar.domain.Car;

import java.util.List;

public class OutputView {
    private static final String POSITION_SYMBOL = "-";
    private static final String RESULT_HEADER = "실행 결과";
    private static final String WINNING_PREFIX = "최종 우승자 : ";
    private static final String NAME_DELIMITER = ", ";

    public void printRoundHistory(List<List<Car>> roundHistory) {
        System.out.println(RESULT_HEADER);
        roundHistory.forEach(this::printRound);
    }

    private void printRound(List<Car> roundResult) {
        roundResult.forEach(this::printCarStatus);
        System.out.println();
    }

    private void printCarStatus(Car car) {
        System.out.printf("%s : %s%n",
                car.getName(),
                POSITION_SYMBOL.repeat(car.getPosition())
        );
    }

    public void printWinners(List<String> winners) {
        System.out.println(WINNING_PREFIX + String.join(NAME_DELIMITER, winners));
    }
}
