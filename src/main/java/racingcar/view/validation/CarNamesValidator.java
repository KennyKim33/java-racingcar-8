package racingcar.view.validation;

import racingcar.util.StringValidator;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;



public class CarNamesValidator {
    private static final String DELIMITER = ",";

    private CarNamesValidator() {}

    public static void validateCarNames(String input) {
        StringValidator.validateNotBlank(input);
        validateNotStartsWithDelimiter(input);
        validateNotEndsWithDelimiter(input);
        validateNoDuplicateCarNames(input);
    }

    private static void validateNotStartsWithDelimiter(String input) {
        if (input.startsWith(DELIMITER)) {
            throw new IllegalArgumentException("입력값은 구분자로 시작할 수 없습니다.");
        }
    }

    private static void validateNotEndsWithDelimiter(String input) {
        if (input.endsWith(DELIMITER)) {
            throw new IllegalArgumentException("입력값은 구분자로 끝날 수 없습니다.");
        }
    }

    private static void validateNoDuplicateCarNames(String input) {
        List<String> names = Arrays.stream(input.split(DELIMITER))
                .map(String::trim).toList();
        Set<String> uniqueNames = new HashSet<>(names);

        if (names.size() != uniqueNames.size()) {
            throw new IllegalArgumentException("자동차 이름은 중복될 수 없습니다.");
        }
    }
}
