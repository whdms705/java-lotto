package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Lottos;
import lotto.domain.lotto.WinningLotto;
import lotto.domain.lotto.number.Number;
import lotto.domain.lotto.number.Numbers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LottoRaffleTest {

    WinningLotto winningLotto;

    @BeforeEach
    void setUp() {
        final List<Number> numbersValues = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            numbersValues.add(new Number(i));
        }
        final Numbers numbers = new Numbers(numbersValues);
        final Number bonusNumber = new Number(7);
        this.winningLotto = new WinningLotto(numbers, bonusNumber);
    }

    @Test
    void 일등_당첨_결과_확인() {
        final LottoResult expectedResult = LottoResult.MATCH6;
        final Lotto lotto = new Lotto(winningLotto.getNumbers());
        final LottoRaffle lottoRaffle = new LottoRaffle(winningLotto);
        final MatchResult matchResult = lottoRaffle.compareLottos(new Lottos(Arrays.asList(lotto)));

        for (LottoResult lottoResult : matchResult.getLottoResultSet()) {
            if (lottoResult.equals(expectedResult)) {
                assertThat(matchResult.getCount(lottoResult)).isEqualTo(1);
                continue;
            }
            assertThat(matchResult.getCount(lottoResult)).isZero();
        }
    }

    @Test
    void 이등_당첨_결과_확인() {
        final LottoResult expectedResult = LottoResult.MATCH_BONUS;
        final Lotto lotto = new Lotto(new Numbers("2,3,4,5,6,7"));
        final LottoRaffle lottoRaffle = new LottoRaffle(winningLotto);
        final MatchResult matchResult = lottoRaffle.compareLottos(new Lottos(Arrays.asList(lotto)));

        for (LottoResult lottoResult : matchResult.getLottoResultSet()) {
            if (lottoResult.equals(expectedResult)) {
                assertThat(matchResult.getCount(lottoResult)).isEqualTo(1);
                continue;
            }
            assertThat(matchResult.getCount(lottoResult)).isZero();
        }
    }
}
