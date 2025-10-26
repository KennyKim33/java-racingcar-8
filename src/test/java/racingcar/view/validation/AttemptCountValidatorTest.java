package racingcar.view.validation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AttemptCountValidatorTest {

    @Test
    void 정상_시도_횟수는_예외가_발생_하지_않는다() {
        String input = "5";

        assertThatCode(() -> AttemptCountValidator.validateAttemptCount(input))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   ", "\t", "\n"})
    void null_또는_공백_입력시_예외가_발생한다(String input) {

        assertThatThrownBy(() -> AttemptCountValidator.validateAttemptCount(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("입력값은 null 이거나 공백일 수 없습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"abc", "1.5", "1a", "a1", "!", "@#$", "1 2", "-1"})
    void 올바른_숫자_형식이_아닌_입력시_예외가_발생한다(String input) {

        assertThatThrownBy(() -> AttemptCountValidator.validateAttemptCount(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("시도 횟수는 숫자 형식이어야 합니다.");
    }

    @Test
    void 양의_정수가_아닌_값을_입력시_예외가_발생한다() {

        String input = "0";

        assertThatThrownBy(() -> AttemptCountValidator.validateAttemptCount(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("시도 횟수는 1이상의 양의 정수여야 합니다.");
    }
}