package racingcar.domain;

import racingcar.util.StringValidator;

import java.util.regex.Pattern;

public class Car {
    private static final Pattern CAR_NAME_REGEX = Pattern.compile("^[a-zA-Z0-9가-힣]+$");
    private static final int MAX_CAR_NAME_LENGTH = 5;
    private static final String CAR_NAME_FIELD = "자동차 이름";

    private final String name;
    private final MovingStrategy movingStrategy;
    private int position;

    public Car(String name, MovingStrategy movingStrategy) {
        validateCarName(name);
        this.name = name;
        this.movingStrategy = movingStrategy;
        this.position = 0;
    }

    private void validateCarName(String name) {
        StringValidator.validateNotBlank(name, CAR_NAME_FIELD);

        validateNameLength(name);

        validateNamePattern(name);
    }

    private void validateNameLength(String name) {
        if (name.length() > MAX_CAR_NAME_LENGTH) {
            throw new IllegalArgumentException(String.format("자동차 이름은 %d자 이하여야 합니다.", MAX_CAR_NAME_LENGTH));
        }
    }

    private void validateNamePattern(String name) {
        if (!CAR_NAME_REGEX.matcher(name).matches()) {
            throw new IllegalArgumentException("자동차 이름은 영어와 숫자 한글만 가능합니다.");
        }
    }

    public void move() {
        if (movingStrategy.shouldMove()) {
            position++;
        }
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
}
