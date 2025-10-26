package racingcar.domain;

import java.util.Arrays;
import java.util.List;

public class RacingCarFactory {
    private static final String DELIMITER = ",";

    private RacingCarFactory() {}

    public static List<Car> from(String input, MovingStrategy movingStrategy) {
        return Arrays.stream(input.split(DELIMITER))
                .map(name -> new Car(name, movingStrategy))
                .toList();
    }
}
