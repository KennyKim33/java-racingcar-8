package racingcar.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class StringValidatorTest {
    @Test
    void 정상적인_문자열은_예외가_발생하지_않는다() {
        String input = "pobi";

        assertThatCode(() -> StringValidator.validateNotBlank(input))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   ", "\t", "\n"})
    void null_또는_공백_입력시_예외가_발생한다(String input) {

        assertThatThrownBy(() -> StringValidator.validateNotBlank(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("입력값은 null 이거나 공백일 수 없습니다.");
    }

    @Test
    void fieldName을_포함한_정상적인_문자열은_예외가_발생하지_않는다() {
        String input = "pobi";
        String fieldName = "자동차 이름";

        assertThatCode(() -> StringValidator.validateNotBlank(input, fieldName))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   ", "\t", "\n"})
    void fieldName을_포함한_null_또는_공백_입력시_예외가_발생한다(String input) {
        String fieldName = "자동차 이름";

        // when & then
        assertThatThrownBy(() -> StringValidator.validateNotBlank(input, fieldName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("자동차 이름은 null 이거나 공백일 수 없습니다.");
    }

    @Test
    void fieldName이_에러_메시지에_포함된다() {
        String input = "";
        String fieldName = "시도 횟수";

        assertThatThrownBy(() -> StringValidator.validateNotBlank(input, fieldName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("시도 횟수은 null 이거나 공백일 수 없습니다.");
    }
}