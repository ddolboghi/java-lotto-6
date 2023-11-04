package lotto.unitTest.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import lotto.common.config.UserRule;
import lotto.model.Lotto;
import lotto.model.LottoMaker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoMakerTest {

    @Test
    void LottoMaker는_입력없이_랜덤로또를_생성한다() {
        Lotto autoLotto = LottoMaker.makeAutoLotto();

        assertThat(autoLotto).isInstanceOf(Lotto.class);
    }

    @Test
    void LottoMaker가_생성한_수동로또에는_입력된_숫자들이_들어있다() {
        String inputWinningNumbers = "1,2,3,4,5,6";
        List<Integer> numbers = Arrays.stream(inputWinningNumbers.split(UserRule.WINING_NUMBERS_SEPARATOR.value()))
                .map(Integer::parseInt)
                .toList();

        Lotto manualLotto = LottoMaker.makeManualLotto(numbers);

        numbers.forEach(
                number -> assertTrue(manualLotto.contains(number), String.format("생성된 로또가 %d를 포함하지 않습니다.", number))
        );
    }

    @ValueSource(strings = {"1,", "1,2,3,4,5,6,7"})
    @ParameterizedTest
    void 숫자_개수가_6개가_아니면_예외를_발생시킨다(String inputWinningNumbers) {
        List<Integer> numbers = Arrays.stream(inputWinningNumbers.split(UserRule.WINING_NUMBERS_SEPARATOR.value()))
                .map(Integer::parseInt)
                .toList();

        assertThatThrownBy(() -> LottoMaker.makeManualLotto(numbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 중복된_숫자가_있으면_예외를_발생시킨다() {
        String inputWinningNumbers = "1,1,2,3,4,5";
        List<Integer> numbers = Arrays.stream(inputWinningNumbers.split(UserRule.WINING_NUMBERS_SEPARATOR.value()))
                .map(Integer::parseInt)
                .toList();

        assertThatThrownBy(() -> LottoMaker.makeManualLotto(numbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

}