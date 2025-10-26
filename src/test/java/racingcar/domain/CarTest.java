package racingcar.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

class CarTest {

    @Test
    void 정상적인_이름으로_자동차를_생성한다() {
        MovingStrategy movingStrategy = () -> true;

        assertThatCode(() -> new Car("pobi", movingStrategy))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "  ", "\t", "\n"})
    void 빈칸이거나_null이면_예외가_발생한다(String name) {
        MovingStrategy movingStrategy = () -> true;

        assertThatThrownBy(() -> new Car(name, movingStrategy))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("자동차 이름은 null 이거나 공백일 수 없습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"carname", "carrrname"})
    void 자동차_이름이_6자_이상이면_예외가_발생한다(String name) {
        MovingStrategy movingStrategy = () -> true;

        assertThatThrownBy(() -> new Car(name, movingStrategy))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("자동차 이름은 5자 이하여야 합니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"po-bi", "car!", "이름@", "내 차", "car#1"})
    void 특수문자나_공백이_포함된_이름이면_예외가_발생한다(String invalidName) {
        MovingStrategy mockStrategy = () -> true;

        assertThatThrownBy(() -> new Car(invalidName, mockStrategy))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("자동차 이름은 영어와 숫자 한글만 가능합니다");
    }

    @Test
    void 전진조건이_true면_전진한다() {
        MovingStrategy movingStrategy = () -> true;
        Car car = new Car("test", movingStrategy);

        car.move();

        assertThat(car.getPosition()).isEqualTo(1);
    }

    @Test
    void 전진조건이_false면_전진하지_않는다() {
        MovingStrategy movingStrategy = () -> false;
        Car car = new Car("test", movingStrategy);

        car.move();

        assertThat(car.getPosition()).isEqualTo(0);
    }

    @Test
    void 새로운_Car_객체를_반환한다() {
        MovingStrategy strategy = () -> true;
        Car original = new Car("pobi", strategy);
        original.move();

        Car copied = original.copy();

        assertThat(copied).isNotSameAs(original);
        assertThat(copied.getName()).isEqualTo(original.getName());
        assertThat(copied.getPosition()).isEqualTo(original.getPosition());
    }

    @Test
    void 복사본이_원본에_영향을_받지_않는다() {
        MovingStrategy strategy = () -> true;
        Car original = new Car("pobi", strategy);
        original.move();

        Car copied = original.copy();
        original.move();

        assertThat(original.getPosition()).isEqualTo(2);
        assertThat(copied.getPosition()).isEqualTo(1);
    }
}