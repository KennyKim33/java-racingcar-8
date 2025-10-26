package racingcar.domain;

import java.util.regex.Pattern;

public class Car {
    private static final Pattern CAR_NAME_REGEX = Pattern.compile("^[a-zA-Z0-9가-힣]+$");
    private static final int MAX_CAR_NAME_LENGTH = 5;

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
        validateNotBlank(name);

        validateNameLength(name);

        validateNamePattern(name);
    }

    private void validateNotBlank(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("자동차 이름은 빈칸이 될 수 없습니다.");
        }
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
