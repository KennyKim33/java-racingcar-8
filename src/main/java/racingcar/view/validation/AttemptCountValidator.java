package racingcar.view.validation;

import racingcar.util.StringValidator;

public class AttemptCountValidator {
    private static final String NUMBER_FORMAT = "\\d+";

    private AttemptCountValidator() {
    }

    public static void validateAttemptCount(String input) {
        StringValidator.validateNotBlank(input);
        validateNumberFormat(input);
        validatePositiveNumber(input);
    }

    private static void validateNumberFormat(String input) {
        if (!input.matches(NUMBER_FORMAT)) {
            throw new IllegalArgumentException("시도 횟수는 숫자 형식이어야 합니다.");
        }
    }

    private static void validatePositiveNumber(String input) {
        int number = Integer.parseInt(input);

        if (number <= 0) {
            throw new IllegalArgumentException("시도 횟수는 1이상의 양의 정수여야 합니다.");
        }
    }


}
