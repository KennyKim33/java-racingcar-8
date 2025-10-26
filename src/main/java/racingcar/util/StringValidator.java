package racingcar.util;

public class StringValidator {
    private StringValidator() {}

    public static void validateNotBlank(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("입력값은 null 이거나 공백일 수 없습니다.");
        }
    }

    public static void validateNotBlank(String input, String fieldName) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(String.format("%s은 null 이거나 공백일 수 없습니다.", fieldName));
        }
    }
}
