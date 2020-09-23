package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

public class WinningLottoTest {

    @DisplayName("당첨 로또 번호 예외 확인")
    @Test
    void shouldExceptionWinningLotto() {
        assertThatThrownBy(() -> WinningLotto.of(Arrays.asList(1,2,3,4,5,5), 6))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("당첨 로또 번 예외 확인")
    @Test
    void shouldExceptionWithMessageWinningLotto() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> Lotto.of(Arrays.asList(1,2,3,4,5,5)))
                .withMessage("로또 개수는 6개 입니다.");
    }

    @DisplayName("Rank 추출 확인 테스트")
    @Test
    void getRankByLotto() {
        WinningLotto winningLotto = WinningLotto.of(Arrays.asList(1,2,3,4,5,6), 9);
        assertThat(winningLotto.getRankByLotto(Lotto.of(Arrays.asList(1,2,3,4,5,9)))).isEqualTo(Rank.SECOND);
    }
}