package racingcar.view.validation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

class CarNamesValidatorTest {
    @Test
    void 정상적인_입력값은_예외가_발생하지_않는다() {

        String input = "pobi,woni,jun";

        assertThatCode(() -> CarNamesValidator.validateCarNames(input))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   ", "\t", "\n"})
    void null_또는_공백_입력시_예외가_발생한다(String input) {

        assertThatThrownBy(() -> CarNamesValidator.validateCarNames(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("입력값은 null 이거나 공백일 수 없습니다.");
    }

    @Test
    void 입력이_구분자로_시작하면_예외가_발생한다() {

        String input = ",pobi,woni";

        assertThatThrownBy(() -> CarNamesValidator.validateCarNames(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("입력값은 구분자로 시작할 수 없습니다.");
    }

    @Test
    void 입력이_구분자로_끝나면_예외가_발생한다() {

        String input = "pobi,woni,";

        assertThatThrownBy(() -> CarNamesValidator.validateCarNames(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("입력값은 구분자로 끝날 수 없습니다.");
    }

    @Test
    void 중복된_자동차_이름이_있으면_예외가_발생한다() {

        String input = "pobi,woni,pobi";

        assertThatThrownBy(() -> CarNamesValidator.validateCarNames(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("자동차 이름은 중복될 수 없습니다.");
    }

}